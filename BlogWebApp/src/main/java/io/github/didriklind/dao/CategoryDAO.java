package io.github.didriklind.dao;

import javax.ejb.Stateless;

import io.github.didriklind.entity.Category;

@Stateless
public class CategoryDAO extends GenericDAO<Category> {
	public CategoryDAO() {super(Category.class);}
}
