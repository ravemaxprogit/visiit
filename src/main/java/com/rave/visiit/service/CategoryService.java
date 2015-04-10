package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.Category;

public interface CategoryService {
	
	public Category saveorupdate(Category category) throws Exception;

	public boolean delete(Category category) throws Exception;

	public List<Category> getAll() throws Exception;

	public Category get(Integer id) throws Exception;

	public List<Category> getTop() throws Exception;
	
	public String getImageUrlById(Integer id) throws Exception;
	
	public String getIconUrlById(Integer id) throws Exception;

}
