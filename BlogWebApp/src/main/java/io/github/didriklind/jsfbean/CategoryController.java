package io.github.didriklind.jsfbean;


import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import io.github.didriklind.dao.CategoryDAO;
import io.github.didriklind.entity.Category;

@Named
@RequestScoped
public class CategoryController {

	public CategoryController() {
		theCategory = new Category();
	}
	
	@EJB 
	CategoryDAO categoryDAO;
	private Category theCategory;
	
	public Category getTheCategory() {
		return theCategory;
	}
	public void setTheCategory(Category theCategory) {
		this.theCategory = theCategory;
	}
	
	public void persistCategory() {
		categoryDAO.save(theCategory);
	}
	
	public void updateCategory() {
		categoryDAO.update(theCategory);
	}
	
	public void deleteCategory() {
		categoryDAO.delete(theCategory);
	}
	
	public List<Category> listAllCategories() {
		System.out.println("HELLO from listALLCategories()in CategoryController"); 
		return categoryDAO.findAll();
	}
	
	public String createCategory() {
		System.out.println("Creating categoriiie hopefully only an admin!! ARGH");
		persistCategory();
		theCategory = new Category();
		return "admin_categoryinfo_page?faces-redirect=true";
	}
	
	public String changeCategory() {
		System.out.println("chaning category: " + theCategory);
		updateCategory();
		return "respond_page_something";
	}
	
	public String removeCategory() {
		System.out.println("removing category: " + theCategory);
		deleteCategory();
		return "respond_page_something";
	}
	
	public String switchToAdminCategoryCreate() {
		theCategory = new Category();
		return "admin_categorycreate_page?faces-redirect=true";
	}
	
	
	
	
	
	
}
