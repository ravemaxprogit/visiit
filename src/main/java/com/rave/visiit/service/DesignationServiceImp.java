package com.rave.visiit.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.rave.visiit.dao.DesignationDao;
import com.rave.visiit.entity.Designation;
import com.rave.visiit.util.CommonUtil;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.ErrorConstants;


@Service
public class DesignationServiceImp implements DesignationService {
	
	private DesignationDao designationDao;
	
	public void setDesignationDao(DesignationDao designationDao) {
		this.designationDao = designationDao;
	}

	@Override
	@Transactional
	public void saveDesignation(Designation desg) throws Exception {
		if(StringUtils.isNotBlank(desg.getDsgName()) && designationDao.checkDesignationFieldExists("dsgName", desg.getDsgName(), desg.getDsgId())){
			throw new Exception(ErrorConstants.DESG_NAME_ALREADY_EXISTS);
		}
		if(desg.getDsgId() == null){
			desg.setDsgCode(designationDao.getNextDesgCode());
		}
		desg.setDsgCreatedBy(Constants.ADMIN);
		desg.setDsgCreatedOn(new Timestamp(System.currentTimeMillis()));
		if(desg.getDsgId()!=null){
			if(designationDao.getCountOfDesignation(desg.getDsgId())==0){
				throw new Exception(ErrorConstants.DESG_NOT_FOUND);
			}
			desg.setDsgModifiedBy(Constants.ADMIN);
			desg.setDsgModifiedOn(new Timestamp(System.currentTimeMillis()));
		}
		designationDao.saveDesignation(desg);
	}

	@Override
	@Transactional
	public List<Designation> listDesignation() throws Exception{
		List<Designation> designationList = designationDao.listDesignation();
		if(CollectionUtils.isEmpty(designationList)){
			throw new Exception(ErrorConstants.LIST_NOT_FOUND);
		}
		return designationList;
	}

	@Override
	@Transactional
	public Designation getDesignationById(int id) throws Exception{
		Designation desg =  designationDao.getDesignationById(id);
		if(desg == null){
			throw new Exception(ErrorConstants.DESG_NOT_FOUND);
		}
		if(desg.isDeleted()){
			throw new Exception(ErrorConstants.DESG_DELETED_ALREADY);
		}
		return desg;
	}

	@Override
	@Transactional
	public void deleteDesignation(int id) throws Exception {
		if(!designationDao.checkFieldRefInAnyWhere(id)){
			Designation desg = getDesignationById(id);
			desg.setDsgName(Constants.DELETE_TOKEN+CommonUtil.getLastForDigitsOfNowTimeStamp()+desg.getDsgName());
			desg.setDeleted(true);
			desg.setDsgModifiedBy(Constants.ADMIN);
			desg.setDsgModifiedOn(new Timestamp(System.currentTimeMillis()));
			designationDao.saveDesignation(desg);
		}else{
			throw new Exception("Designation not deleted because it refered in somewhere");
		}
	}

	@Override
	@Transactional
	public void removeDesignation(int id) {
		designationDao.removeDesignation(id);
	}

}
