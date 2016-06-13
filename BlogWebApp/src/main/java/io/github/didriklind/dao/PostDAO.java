package io.github.didriklind.dao;



import javax.ejb.Stateless;


import io.github.didriklind.entity.Post;

@Stateless
public class PostDAO extends GenericDAO<Post> {

	public PostDAO() {super(Post.class);}

	
}
