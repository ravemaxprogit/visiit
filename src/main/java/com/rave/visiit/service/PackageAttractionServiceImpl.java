package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.PackageAttraction;

@Service
public class PackageAttractionServiceImpl extends AbstractService implements PackageAttractionService{

	@Override
	@Transactional
	public PackageAttraction saveorupdate(PackageAttraction pack)
			throws Exception {
		// TODO Auto-generated method stub
		return getPackageAttractionDao().saveorupdate(pack);
	}

	@Override
	@Transactional
	public boolean delete(PackageAttraction pack) throws Exception {
		// TODO Auto-generated method stub
		return getPackageAttractionDao().delete(pack);
	}

	@Override
	@Transactional
	public List<PackageAttraction> getAll() throws Exception {
		// TODO Auto-generated method stub
		return getPackageAttractionDao().getAll();
	}

	@Override
	@Transactional
	public PackageAttraction get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageAttractionDao().get(id);
	}

}
