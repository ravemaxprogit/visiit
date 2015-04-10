package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.AppLogin;

@Repository
public class AppLoginDaoImp implements AppLoginDao {

    private static final Logger logger = LoggerFactory.getLogger(AppLoginDaoImp.class); 
 	
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    
	@Override
	public void addAppLogin(AppLogin appLog) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(appLog);
        logger.info("AppLogin saved successfully, AppLogin Details="+appLog);
	}

	@Override
	public void updateAppLogin(AppLogin appLog) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(appLog);
        logger.info("AppLogin updated successfully, AppLogin Details="+appLog);
	}

	@Override
	public List<AppLogin> listAppLogin() {
		Session session = this.sessionFactory.getCurrentSession();
	    List<AppLogin> appLogin = session.createQuery("from AppLogin").list();
        for(AppLogin app : appLogin){
         logger.info("AppLogin List::"+app);
        }
        return appLogin;
    }

	@Override
	public AppLogin getAppLoginById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		AppLogin appLog = (AppLogin) session.load(AppLogin.class, new Integer(id));
        logger.info("AppLogin loaded successfully, AppLogin detail="+appLog);
        return appLog;
	}

	@Override
	public void removeAppLogin(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		AppLogin appLog = (AppLogin) session.load(AppLogin.class, new Integer(id));
		if(null != appLog){
            session.delete(appLog);
        }
        logger.info("AppLogin removed successfully, AppLogin detail="+appLog);
    }

}
