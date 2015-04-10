package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.dao.AppLoginDao;
import com.rave.visiit.entity.AppLogin;

@Service
public class AppLoginServiceImp implements AppLoginService {
	
	private AppLoginDao appLoginDao;
	
	public void setAppLoginDao(AppLoginDao appLoginDao) {
		this.appLoginDao = appLoginDao;
	}

	@Override
	@Transactional
	public void addAppLogin(AppLogin appLog) {
        this.appLoginDao.addAppLogin(appLog);
	}

	@Override
	@Transactional
	public void updateAppLogin(AppLogin appLog) {
		this.appLoginDao.updateAppLogin(appLog);
	}

	@Override
	@Transactional
	public List<AppLogin> listAppLogin() {
		return this.appLoginDao.listAppLogin();
	}

	@Override
	@Transactional
	public AppLogin getAppLoginById(int id) {
		return this.appLoginDao.getAppLoginById(id);
	}

	@Override
	@Transactional
	public void removeAppLogin(int id) {
		this.appLoginDao.removeAppLogin(id);
	}

}
