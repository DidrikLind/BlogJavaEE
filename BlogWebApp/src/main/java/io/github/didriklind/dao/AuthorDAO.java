package io.github.didriklind.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import io.github.didriklind.entity.Author;


@Stateless
public class AuthorDAO extends GenericDAO<Author> {

	public AuthorDAO() {super(Author.class);}	
	
	//TODO verkar ok nu :)
	@SuppressWarnings("unchecked")
	public List<Author> findAuthorsByName(String searchStr) {
		Query query = em.createQuery("SELECT a FROM Author a WHERE a.username LIKE :username");
		query.setParameter("username", "%" + searchStr + "%");
        return query.getResultList();
	}
	
	public Author findAuthorByUserName(String searchStr) {
		Query query = em.createQuery("SELECT a FROM Author a WHERE a.username=:username");
		query.setParameter("username", searchStr);
		Author author = null;
		try {
			author = (Author) query.getResultList().get(0);
			return author;
		}catch (Exception e) {
			return null;
		}
		
	}
}
