package io.github.didriklind.dao;



import javax.ejb.Stateless;

import io.github.didriklind.entity.Comment;
import io.github.didriklind.entity.Post;

@Stateless
public class CommentDAO extends GenericDAO<Comment> {

	public CommentDAO() {super(Comment.class);}

//	//TODO testing!
//	public void addCommentToPost(int commentId, Post post) {
//		Comment comment = find(commentId);
//		comment.setPost(post);
//		post.getComments().add(comment);
//	}
//	
//	//TODO testing!
//	public List<Comment> fetchCommentsFromPost(Post post) {
//		return post.getComments();	
//	}
}
