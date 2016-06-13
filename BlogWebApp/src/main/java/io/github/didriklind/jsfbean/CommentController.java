package io.github.didriklind.jsfbean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import io.github.didriklind.dao.CommentDAO;
import io.github.didriklind.entity.Comment;
import io.github.didriklind.entity.Post;

@SessionScoped
@Named
public class CommentController implements Serializable {

	private static final long serialVersionUID = 1L;


	public CommentController() {
		theComment = new Comment();
	}
	
	@Inject
	PostController postController;
	
	@EJB
	private CommentDAO commentDAO;
	private Comment theComment;
	

	public Comment getTheComment() {
		return theComment;
	}

	public void setTheComment(Comment theComment) {
		this.theComment = theComment;
	}

	public void persistComment() {
//		commentDAO.save(theComment);
////		commentDAO.addCommentToPost(currentComment.getId(), postDAO.find(tempPostToAddComment.getId()));
//		theComment = new Comment();
////		tempPostToAddComment = new Post();
		
		
		postController.saveTheCommentToThePost(theComment);
	}
	
	public void printAllComments() {
		List<Comment> comments = commentDAO.findAll();
		for (Comment Comment : comments) {
			System.out.println(Comment);
		}
	}
	
	//TODO testing!(THIS IS WRONG ;) )
	public void addCommentToPost() {
		//currentComment.setPost(post);
		//post.getComments().add(currentComment);
	}
	
	//TODO testing!(THIS IS WRONG ;) )
	public List<Comment> fetchCommentsFromPost(Post post) {
		return post.getComments();	
	}
	
//	//creating temporary comment just for tryout timestamp! :)
	//IT WORKED :)!
//	public void createCcomment() {
//		System.out.println("Create comment YARR!!");
//		currentComment = new Comment("Didrik", 
//				"FAN VA BRA ARTIKEL!", 
//				"dippe_lind@hotmail.com");
//		persistComment();
//	}
	
	public String createComment() {
		System.out.println("Create comment YARR!!");
//		thePost = new Post("A new day in a new day",
//				"Once I said, hello day, where are you from? -Day answered 'HELLO'");
		
		theComment.setTimestamp(Calendar.getInstance());
		persistComment();
		theComment = new Comment();
		return "?faces-redirect=true";		
	}
	
	
	//TODO take away, wrong thinkling?
	public String actionMethod() {
		
		
		return null;	
	}
}
