package io.github.didriklind.jsfbean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import io.github.didriklind.dao.AuthorDAO;
import io.github.didriklind.entity.Author;
import io.github.didriklind.entity.Post;


@Named
@SessionScoped //Cuz active the whole time for the user?
public class AuthorController implements Serializable {

	private static final long serialVersionUID = 1L;


	public AuthorController() {
		theAuthor = new Author(); //The registered user or logged-in user.
	}
	
	@EJB
	private AuthorDAO authorDAO;
	private Author theAuthor;
	

	public Author getTheAuthor() {
		return theAuthor;
	}

	public void setTheAuthor(Author theAuthor) {
		this.theAuthor = theAuthor;
	}

	public void persistAuthor() {
		authorDAO.save(theAuthor);
//		commentDAO.addCommentToPost(currentComment.getId(), postDAO.find(tempPostToAddComment.getId()));
//		theAuthor = new Author();
//		tempPostToAddComment = new Post();
	}
	
	public void updateAuthor() {
		authorDAO.update(theAuthor);
	}
	
	public void printAllAuthors() {
		List<Author> authors = authorDAO.findAll();
		for (Author author : authors) {
			System.out.println(author);
		}
	}
	
	//TODO just test, delete later
	public String registerAuthor() {
		System.out.println("Create author YARR!!*** FIRST_NAME: " + theAuthor.getFirstName());
		System.out.println(theAuthor);
		theAuthor.setTimestamp(Calendar.getInstance());
		persistAuthor();
		return "confirmed_register_page";
	}
	
	//TODO just test, delete later.
	public void printRegisteredAuthor() {
		System.out.println(theAuthor);
	}
	
//	public void persistAuthor() {
//		authorDAO()
//	}
	//TODO just test, delte later
	public void printTheAuthor() {
		System.out.println("USERNAME: " + theAuthor.getUsername());
	}
	
	public String authenticateAuthor() {
		String authUsername = theAuthor.getUsername();
		String authPassword = theAuthor.getPassword();
		if(authUsername.equalsIgnoreCase("admin") && authPassword.equalsIgnoreCase("admin"))
			return "admin_home_page?faces-redirect=true";
		Author tempAuthor = authorDAO.findAuthorByUserName(authUsername);
		if(tempAuthor != null) {
			System.out.println(tempAuthor);
			if(tempAuthor.getPassword().equals(authPassword)) {
				setTheAuthor(tempAuthor);
				return "author_home_page?faces-redirect=true";	
			}
		}
		setTheAuthor(new Author());
		return "home_page"; // komer fr�n home_page.xhtml� s� behg�ver ej redirect
	}
	
	
	public String logoutAuthor() {
		setTheAuthor(new Author());
		return "home_page?faces-redirect=true";
	}
	
	public String toHomePage() {
		setTheAuthor(new Author());
		return "home_page?faces-redirect=true";
	}
		
	public String toRegisterPage() {
		setTheAuthor(new Author());
		return "register_page?faces-redirect=true";
	}
	
	//Conditional navigation maybe in the future.
	public String switchPage() {
		return null;
	}
	
	public void saveThePostToTheAuthor(Post post) {
		theAuthor.addPost(post);
		updateAuthor();
	}
	
	//NEW
	public void refreshTheAuthor() {
		theAuthor = authorDAO.findAuthorByUserName(theAuthor.getUsername());
	}
}
