package io.github.didriklind.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name="Posts")
public class Post implements Serializable, Comparable<Post>{

	private static final long serialVersionUID = 1L;

	public Post() {
		
	}
	
	
	public Post(String title, String post) {
		super();
		this.title = title;
		this.post = post;
		this.timestamp = Calendar.getInstance();
	}



	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	
	private String post;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar timestamp;
	
	@ManyToOne //(optional = false)
	@JoinColumn(name = "author_id")
	private Author author;
	
	@OneToMany(mappedBy="post", cascade = CascadeType.ALL, orphanRemoval = true) //cascade = CascadeType.ALL,
	private List<Comment> comments = new ArrayList<>();
	
	/*Whenever a bidirectional association is formed,
	 *  the application developer must make sure both 
	 *  sides are in-sync at all times. 
	 *  The addComment() and removeComment() are utilities methods 
	 *  that synchronize both ends whenever a child element is added or removed.
	 */
	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setPost(this);
	}
	public void removeComment(Comment comment) {
		comments.remove(comment);
		comment.setPost(null);
	}
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="post_tag",
	   joinColumns = @JoinColumn(name="post_id"),
	   inverseJoinColumns = @JoinColumn(name="tag_id")
	)
	private List<Tag> tags = new ArrayList<>();
	
	/*A bidirectional @ManyToMany association has an owning
	 *  and a mappedBy side. To preserve synchronicity between both sides,
	 *   it’s good practice to provide helper methods for adding or removing
	 *    child entities.
	 */
	public void addTag(Tag tag) {
		tags.add(tag);
		tag.getPosts().add(this);
	}
	public void removeTag(Tag tag) {
		tags.remove(tag);
		tag.getPosts().remove(this);
	}
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="post_category",
	   joinColumns = @JoinColumn(name="post_id"),
	   inverseJoinColumns = @JoinColumn(name="category_id")
	)
	private List<Category> categories = new ArrayList<>();

	/*A bidirectional @ManyToMany association has an owning
	 *  and a mappedBy side. To preserve synchronicity between both sides,
	 *   it’s good practice to provide helper methods for adding or removing
	 *    child entities.
	 */
	public void addCategory(Category category) {
		categories.add(category);
		category.getPosts().add(this);
	}
	public void removeCategory(Category category) {
		categories.remove(category);
		category.getPosts().remove(this);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(title, post, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (categories == null) {
			if (other.categories != null)
				return false;
		} else if (!categories.equals(other.categories))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", post=" + post + ", timestamp=" + timestamp + ", author="
				+ author + ", comments=" + comments + ", tags=" + tags + ", categories=" + categories + "]";
	}

	//0 = equality, 
	@Override
	public int compareTo(Post aPost) {
	    if (getTimestamp() == null || aPost.getTimestamp() == null)
	        throw new NullPointerException();
	    return getTimestamp().compareTo(aPost.getTimestamp());
	}
	
	//to get simpleformat date. It was a hell in JSF otherwise :p
	public String getSimpleFormatDateStr() {
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(timestamp.getTime());
	}	

	
}
