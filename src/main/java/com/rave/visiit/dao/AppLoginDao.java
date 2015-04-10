package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.AppLogin;

public interface AppLoginDao {
	
	public void addAppLogin(AppLogin appLog);
    public void updateAppLogin(AppLogin appLog);
    public List<AppLogin> listAppLogin();
    public AppLogin getAppLoginById(int id);
    public void removeAppLogin(int id);

}
