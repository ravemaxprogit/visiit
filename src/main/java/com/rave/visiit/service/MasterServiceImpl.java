package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.dao.MasterDao;
import com.rave.visiit.entity.Salutation;
@Service
public class MasterServiceImpl implements MasterService{

	private MasterDao masterDao;
	
	
	@Override
	@Transactional
	public List<Salutation> getAllSalutation() {
		return getMasterDao().getAllSalutation();
	}


	public MasterDao getMasterDao() {
		return masterDao;
	}


	public void setMasterDao(MasterDao masterDao) {
		this.masterDao = masterDao;
	}

}
