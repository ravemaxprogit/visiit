package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.RoleMaster;

public interface RoleMasterService {
	
	public RoleMaster saveorupdate(RoleMaster role) throws Exception;
	
	public boolean delete(RoleMaster role) throws Exception;
	
	public List<RoleMaster> getAll() throws Exception;
	
	public RoleMaster get(Integer id) throws Exception;
}