package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.dao.SecurityQuestionDao;
import com.rave.visiit.entity.SecurityQuestion;

@Service
public class SecurityQuestionServiceImp implements SecurityQuestionService{
	
	private SecurityQuestionDao securityQuestionDao;
	
	public void setSecurityQuestionDao(SecurityQuestionDao securityQuestionDao) {
		this.securityQuestionDao = securityQuestionDao;
	}

	@Override
	@Transactional
	public void addSecurityQuestion(SecurityQuestion secQust) {
		this.securityQuestionDao.addSecurityQuestion(secQust);
	}

	@Override
	@Transactional
	public void updateSecurityQuestion(SecurityQuestion secQust) {
		this.securityQuestionDao.updateSecurityQuestion(secQust);
	}

	@Override
	@Transactional
	public List<SecurityQuestion> listSecurityQuestion() {
		return this.securityQuestionDao.listSecurityQuestion();
	}

	@Override
	@Transactional
	public SecurityQuestion getSecurityQuestionById(int id) {
		return this.securityQuestionDao.getSecurityQuestionById(id);
	}

	@Override
	@Transactional
	public void removeSecurityQuestion(int id) {
		this.securityQuestionDao.removeSecurityQuestion(id);
	}

}
