package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.Category;


@Service
public class CategoryServiceImpl extends AbstractService implements
		CategoryService {

	@Override
	@Transactional
	public Category saveorupdate(Category category) throws Exception {
		// TODO Auto-generated method stub
		return getCategoryDao().saveorupdate(category);
	}

	@Override
	@Transactional
	public boolean delete(Category category) throws Exception {
		// TODO Auto-generated method stub
		return getCategoryDao().delete(category);
	}

	@Override
	@Transactional
	public List<Category> getAll() throws Exception {
		// TODO Auto-generated method stub
		return getCategoryDao().getAll();
	}

	@Override
	@Transactional
	public Category get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getCategoryDao().get(id);
	}

	@Override
	@Transactional
	public List<Category> getTop() throws Exception {
		// TODO Auto-generated method stub
		return getCategoryDao().getTop();
	}
	
	@Override
	@Transactional
	public String getImageUrlById(Integer id) throws Exception {
		return getCategoryDao().getImageUrlById(id);
	}
	
	@Override
	@Transactional
	public String getIconUrlById(Integer id) throws Exception {
		return getCategoryDao().getIconUrlById(id);
	}
}
