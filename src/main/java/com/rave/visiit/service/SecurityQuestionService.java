package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.SecurityQuestion;

public interface SecurityQuestionService {
	
	public void addSecurityQuestion(SecurityQuestion secQust);
    public void updateSecurityQuestion(SecurityQuestion secQust);
    public List<SecurityQuestion> listSecurityQuestion();
    public SecurityQuestion getSecurityQuestionById(int id);
    public void removeSecurityQuestion(int id);

}
