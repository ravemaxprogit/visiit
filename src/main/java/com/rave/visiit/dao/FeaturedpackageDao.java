package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.Featuredpackage;

public interface FeaturedpackageDao {
	
	public Featuredpackage saveorupdate(Featuredpackage pack) throws Exception;

	public boolean delete(Integer id) throws Exception;

	public List<Featuredpackage> getAll(Integer id) throws Exception;

	public Featuredpackage get(Integer id) throws Exception;

	public Featuredpackage getByPack(Integer id) throws Exception;

}
