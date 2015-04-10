package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.SecurityQuestion;

public interface SecurityQuestionDao {
	
	public void addSecurityQuestion(SecurityQuestion secQust);
    public void updateSecurityQuestion(SecurityQuestion secQust);
    public List<SecurityQuestion> listSecurityQuestion();
    public SecurityQuestion getSecurityQuestionById(int id);
    public void removeSecurityQuestion(int id);

}
