package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.Featuredpackage;

@Service
public class FeaturedpackageServiceImpl extends AbstractService implements
		FeaturedpackageService {

	@Override
	@Transactional
	public Featuredpackage saveorupdate(Featuredpackage pack) throws Exception {
		// TODO Auto-generated method stub
		return getFeaturedpackageDao().saveorupdate(pack);
	}

	@Override
	@Transactional
	public boolean delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getFeaturedpackageDao().delete(id);
	}

	@Override
	@Transactional
	public List<Featuredpackage> getAll(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getFeaturedpackageDao().getAll(id);
	}

	@Override
	@Transactional
	public Featuredpackage get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getFeaturedpackageDao().get(id);
	}

	@Override
	@Transactional
	public Featuredpackage getByPack(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getFeaturedpackageDao().getByPack(id);
	}

}
