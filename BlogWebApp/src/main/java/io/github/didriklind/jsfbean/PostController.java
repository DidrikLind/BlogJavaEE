package io.github.didriklind.jsfbean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import io.github.didriklind.dao.PostDAO;
import io.github.didriklind.entity.Category;
import io.github.didriklind.entity.Comment;
import io.github.didriklind.entity.Post;
import io.github.didriklind.entity.Tag;
@SessionScoped
@Named
public class PostController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public PostController() {
		thePost = new Post();
	}
	
	@Inject AuthorController authorController;
	
	@EJB
	private PostDAO postDAO;
	private Post thePost;

	public Post getThePost() {
		return thePost;
	}

	public void setThePost(Post thePost) {
		this.thePost = thePost;
	}

	public void persistPost() {
		//postDAO.save(thePost);
		//thePost = new Post();
		authorController.saveThePostToTheAuthor(thePost);
	}
	
	//TODO make return String?
	public void updatePost() {
		postDAO.update(thePost);
	}
	
	public void printAllPosts() {
		List<Post> posts = postDAO.findAll();
		for (Post post : posts) {
			System.out.println(post);
		}
	}
	
	public List<Post> listAllPosts() {
		System.out.println("HELLO from listAllPosts() in PostController class");
		List<Post> posts = postDAO.findAll();
		Collections.sort(posts, Collections.reverseOrder()); //
		return posts;
	}
	
	//TODO just test, delete later
	public String createPost() {
		System.out.println("Create post YARR!!");
//		thePost = new Post("A new day in a new day",
//				"Once I said, hello day, where are you from? -Day answered 'HELLO'");
		thePost.setTimestamp(Calendar.getInstance());
		persistPost();
		thePost = new Post();
		return "author_postinfo_page?faces-redirect=true";
	}

	public void saveTheCommentToThePost(Comment theComment) {
		thePost.addComment(theComment);
		updatePost();
	}
	
	//TODO using! (for admin account)
	public void saveTheCategoryToThePost(Category theCategory) {
		thePost.addCategory(theCategory);
		updatePost();	
	}
	
	//TODO use l8r
	public void saveTheTagToThePost(Tag theTag) {
		thePost.addTag(theTag);
		updatePost();
	}
	

	
	public String readSpecificPost() {
		return "post_page?faces-redirect=true";
	}
	
	public String switchToAuthorPostCreate() {
		thePost = new Post();
		return "author_postcreate_page?faces-redirect=true";
	}
	
	//same as above, but not make new Post()
	public String switchToAuthorUpdate() {
		return "author_postupdate_page?faces-redirect=true";
	}
	
}
