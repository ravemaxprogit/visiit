package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.User;

@Repository
public class UserDaoImpl  extends AbstractDao implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);


	@Override
	public void saveUser(User user) {
		
		 Session session = getSessionFactory().getCurrentSession();
	        session.save(user);
	        logger.info("User saved successfully, User Details="+user);
	}

//	@Override
//	public void addUser(User user) {
//		Session session = getSessionFactory().getCurrentSession();
//		session.persist(user);
//		logger.info("User saved successfully, User Details=" + user);
//	}
//
	@Override
	public void updateUser(User user) {
		Session session = getSessionFactory().getCurrentSession();
		session.update(user);
		logger.info("User updated successfully, User Details=" + user);
	}

	@Override
	public List<User> listUsers() {
		Session session = getSessionFactory().getCurrentSession();
		Criteria critera = session.createCriteria(User.class);
		critera.add(Restrictions.eq("isDeleted", false));
		List<User> userList = critera.list();
		 
		 /*List<User> userList = session.createQuery("from User").list();
		for (User u : userList) {
			logger.info("Users List::" + u);
		}*/

		return userList;
	}

	@Override
	public User getUsersById(int id) {
		Session session = getSessionFactory().getCurrentSession();
		User user = (User) session.get(User.class, new Integer(id));
		logger.info("User loaded successfully, User details=" + user);
		return user;
	}

	@Override
	public void deleteUser(int userSeqId) {
		Session session = getSessionFactory().getCurrentSession();
		User user = (User) session.get(User.class, new Integer(userSeqId));
		if (null != user) {
			session.delete(user);
		}
		logger.info("User Deleted successfully, User details=" + user);
	}

	@Override
	public User getUserByUserId(String a_userId) {

		Session session = getSessionFactory().getCurrentSession();
		User user = null;
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userId", a_userId));

		List userList = criteria.list();
		if (userList.size() > 0) {
			user = (User) userList.get(0);
		}

		return user;
	}

	@Override
	public List<Integer> getUserModuleIds(Integer uid) {
		Session session = getSessionFactory().getCurrentSession();
		List<Integer> userModuleIds = session.createSQLQuery("select mod_id from user_module uMod where user_seq_id="+uid).list();
		return userModuleIds;
	}

	@Override
	public List<Integer> getUserSubModules(Integer uid) {
		Session session = getSessionFactory().getCurrentSession();
		List<Integer> userModuleIds = session.createSQLQuery("select mod_id from user_sub_module uMod where user_seq_id="+uid).list();
		return userModuleIds;
	}
	

	@Override
	public int getMaxUserSeqId() {
			int maxId = 1;

			Session session = getSessionFactory().getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Integer> codeList = (List<Integer>) session.createQuery("select max(userSeqId)+1 from User").list();
			if(null!=codeList && null!=codeList.get(0)){
				maxId = codeList.get(0);
			}
			return maxId;
		}

	@SuppressWarnings("unchecked")
	@Override
	public Long checkExistingUserId(String userId) {
		Long existingUserCount = 0L;
		Session session = getSessionFactory().getCurrentSession();
		List<Long> codeList = session.createQuery("select count(*) from User where userId='"+userId+"'").list();

		if(null!=codeList && null!=codeList.get(0)){
			existingUserCount = (Long) codeList.get(0);
		}

		return existingUserCount;
	}

	@Override
	public void deleteUserModule(Integer userSeqId) {
		/*Session session = getSessionFactory().getCurrentSession();
		User user = (User) session.get(User.class, new Integer(userSeqId));
		if (null != user) {
			session.delete(user);
		}*/
		Session session = getSessionFactory().getCurrentSession();
		//session.delete("delete from user_module where user_seq_id="+userSeqId);
		Query query = session.createQuery("delete from user_module where user_seq_id="+userSeqId);
		query.executeUpdate();
		logger.info("UserModule Deleted successfully, User details=" + userSeqId);
	}

}
