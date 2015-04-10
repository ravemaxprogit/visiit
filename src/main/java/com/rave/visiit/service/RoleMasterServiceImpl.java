package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.RoleMaster;

@Service
public class RoleMasterServiceImpl extends AbstractService implements
		RoleMasterService {

	@Override
	@Transactional
	public RoleMaster saveorupdate(RoleMaster role) throws Exception {
		// TODO Auto-generated method stub
		return getRoleMasterDao().saveorupdate(role);
	}

	@Override
	@Transactional
	public List<RoleMaster> getAll() throws Exception {
		// TODO Auto-generated method stub
		return getRoleMasterDao().getAll();
	}

	@Override
	@Transactional
	public RoleMaster get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getRoleMasterDao().get(id);
	}

	@Override
	@Transactional
	public boolean delete(RoleMaster role) throws Exception {
		// TODO Auto-generated method stub
		return getRoleMasterDao().delete(role);
	}
}