package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.rave.visiit.dao.ModuleDao;
import com.rave.visiit.dao.UserDao;
import com.rave.visiit.entity.Module;
import com.rave.visiit.entity.User;
import com.rave.visiit.util.ErrorConstants;
 
@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	private ModuleDao moduleDao;
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public void saveUser(User user) {
		this.userDao.saveUser(user);
		
	}

	@Override
	@Transactional
	public void updateUser(User User) {
       this.userDao.updateUser(User);
	}

	@Override
	@Transactional
	public List<User> listUsers() throws Exception {
		List<User> userList = userDao.listUsers();
		if(CollectionUtils.isEmpty(userList)){
			throw new Exception(ErrorConstants.LIST_NOT_FOUND);
		}
		return userList;
	}

	@Override
	@Transactional
	public User getUsersById(int id) throws Exception {
		User usr =  userDao.getUsersById(id);
		if(usr == null){
			throw new Exception(ErrorConstants.USR_NOT_FOUND);
		}
		if(usr.getIsDeleted()){
			throw new Exception(ErrorConstants.USR_DELETED_ALREADY);
		}
		/*List<Module> modules = getUserModules(usr.getUserSeqId());
		usr.setUserModules(modules);
		
		List<Module> subModules = getUserSubModules(usr.getUserSeqId());
		usr.setUserSubModules(subModules);*/
		return usr;
	}

	@Override
	@Transactional
	public void deleteUser(int userSeqId) {
		this.userDao.deleteUser(userSeqId);
	}
	
	@Override
	@Transactional
	public User getUserByUserId(String userId) throws Exception {
		User usr =  userDao.getUserByUserId(userId);
		if(usr == null){
			throw new Exception(ErrorConstants.USR_NOT_FOUND);
		}
		if(usr.getIsDeleted()){
			throw new Exception(ErrorConstants.USR_DELETED_ALREADY);
		}
		/*List<Module> modules = getUserModules(usr.getUserSeqId());
		usr.setUserModules(modules);
		
		List<Module> subModules = getUserSubModules(usr.getUserSeqId());
		usr.setUserSubModules(subModules);*/
		return usr;
	}

	@Override
	@Transactional
	public int getMaxUserSeqId() {
		return this.userDao.getMaxUserSeqId();
	}

	@Override
	@Transactional
	public Long checkExistingUserId(String userId) {
		return this.userDao.checkExistingUserId(userId);
	}
	
	@Override
	@Transactional
	public List<Module> getUserModules(Integer uid) throws Exception {
		List<Module> userModules =  null;
		List<Integer> userModuleIds = userDao.getUserModuleIds(uid);
		if(userModuleIds!=null && userModuleIds.size()>0){
			userModules = moduleDao.getModulesByModuleIds(userModuleIds);
		}
		return userModules;
	}
	
	@Override
	@Transactional
	public List<Module> getUserSubModules(Integer uid) throws Exception {
		List<Module> userSubModules =  null;
		List<Integer> userSubModuleIds = userDao.getUserSubModules(uid);
		if(userSubModuleIds!=null && userSubModuleIds.size()>0){
			userSubModules = moduleDao.getModulesByModuleIds(userSubModuleIds);
		}
		return userSubModules;
	}


	@Override
	@Transactional
	public void deleteUserModule(Integer userSeqId) {
		this.userDao.deleteUserModule(userSeqId);
		
	}

	public ModuleDao getModuleDao() {
		return moduleDao;
	}

	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

}
