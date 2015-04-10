package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.Module;
import com.rave.visiit.entity.User;

public interface UserService {
	
	public void saveUser(User user);
    public void updateUser(User User);
    public List<User> listUsers() throws Exception;
    public User getUsersById(int id) throws Exception;
    public void deleteUser(int userSeqId);
	public User getUserByUserId(String a_userId) throws Exception;
	int getMaxUserSeqId();
	Long checkExistingUserId(String userId);
	public List<Module> getUserModules(Integer uid) throws Exception;
	List<Module> getUserSubModules(Integer uid) throws Exception;
	public void deleteUserModule(Integer userSeqId);
}
