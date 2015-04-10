package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.User;

public interface UserDao {
	
	public void saveUser(User user);
	public void updateUser(User User);
    public List<User> listUsers();
    public User getUsersById(int id);
    public void deleteUser(int userSeqId);
	public User getUserByUserId(String a_userId);
	public int getMaxUserSeqId();
	public Long checkExistingUserId(String userId);
	public List<Integer> getUserModuleIds(Integer uid);
	public List<Integer> getUserSubModules(Integer uid);
	void deleteUserModule(Integer userSeqId);

}
