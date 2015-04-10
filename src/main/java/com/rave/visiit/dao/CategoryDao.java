package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.Category;

public interface CategoryDao {
	
	public Category saveorupdate(Category category) throws Exception;

	public boolean delete(Category category) throws Exception;

	public List<Category> getAll() throws Exception;

	public Category get(Integer id) throws Exception;

	public List<Category> getTop() throws Exception;
	
	public String getImageUrlById(Integer id);
	
	public String getIconUrlById(Integer id);

}
