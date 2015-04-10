package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.RoleMaster;

public interface RoleMasterDao {

	public RoleMaster saveorupdate(RoleMaster role) throws Exception;

	public boolean delete(RoleMaster role) throws Exception;

	public List<RoleMaster> getAll() throws Exception;

	public RoleMaster get(Integer id) throws Exception;

}
