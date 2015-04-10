package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.SecurityQuestion;

@Repository
public class SecurityQuestionImp implements SecurityQuestionDao {

    private static final Logger logger = LoggerFactory.getLogger(SecurityQuestionImp.class); 
 	
	private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    
	@Override
	public void addSecurityQuestion(SecurityQuestion secQust) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(secQust);
        logger.info("AppLogin saved successfully, AppLogin Details="+secQust);
	}

	@Override
	public void updateSecurityQuestion(SecurityQuestion secQust) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(secQust);
        logger.info("AppLogin updated successfully, AppLogin Details="+secQust);
	}

	@Override
	public List<SecurityQuestion> listSecurityQuestion() {
		Session session = this.sessionFactory.getCurrentSession();
	    List<SecurityQuestion> secQustList = session.createQuery("from SecurityQuestion").list();
        for(SecurityQuestion secQus : secQustList){
         logger.info("SecurityQuestion List::"+secQus);
        }
        return secQustList;
    }

	@Override
	public SecurityQuestion getSecurityQuestionById(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		SecurityQuestion ques = (SecurityQuestion) session.load(SecurityQuestion.class, new Integer(id));
        logger.info("SecurityQuestion loaded successfully, SecurityQuestion detail="+ques);
        return ques;
	}

	@Override
	public void removeSecurityQuestion(int id) {
		Session session = this.sessionFactory.getCurrentSession();      
		SecurityQuestion ques = (SecurityQuestion) session.load(SecurityQuestion.class, new Integer(id));
		if(null != ques){
            session.delete(ques);
        }
        logger.info("SecurityQuestion deleted successfully, SecurityQuestion detail="+ques);
     }

}
