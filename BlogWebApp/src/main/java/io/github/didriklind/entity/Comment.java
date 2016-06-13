package io.github.didriklind.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Comments")
public class Comment implements Serializable, Comparable<Comment> {

	private static final long serialVersionUID = 1L;
	
	public Comment() {
		
	}
	
	public Comment(String name, String comment, String email) {
		super();
		this.name = name;
		this.comment = comment;
		this.email = email;
		this.timestamp = Calendar.getInstance();
	}



	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String comment;
	
	private String email;
	
    @Temporal(TemporalType.TIMESTAMP )
    private Calendar timestamp;
	
	@ManyToOne //(optional = false)
	@JoinColumn(name = "post_id") //, referencedColumnName = "postId")
	private Post post;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}


	@Override
	public int hashCode() {
		return Objects.hash(name, comment, email, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", comment=" + comment + ", email=" + email + ", timestamp="
				+ timestamp + ", post=" + post + "]";
	}
	
	//0 = equality, 
	@Override
	public int compareTo(Comment aComment) {
	    if (getTimestamp() == null || aComment.getTimestamp() == null)
	        throw new NullPointerException();
	    return getTimestamp().compareTo(aComment.getTimestamp());
	}
	
	//to get simpleformat date. It was a hell in JSF otherwise :p
	public String getSimpleFormatDateStr() {
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(timestamp.getTime());
	}

}
