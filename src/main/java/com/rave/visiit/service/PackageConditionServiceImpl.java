package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.PackageConditions;

@Service
public class PackageConditionServiceImpl extends AbstractService implements PackageConditionService {

	@Override
	@Transactional
	public PackageConditions saveorupdate(PackageConditions pack)
			throws Exception {
		return getPackageConditionDao().saveorupdate(pack);
	}


	@Override
	@Transactional
	public List<PackageConditions> getAll(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageConditionDao().getAll(id);
	}

	@Override
	@Transactional
	public PackageConditions get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPackageConditionDao().get(id);
	}

}
