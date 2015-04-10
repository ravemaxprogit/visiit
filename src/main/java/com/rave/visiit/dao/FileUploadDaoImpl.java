package com.rave.visiit.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.entity.NewsLetter;
import com.rave.visiit.entity.NewsLetterSubscriber;
import com.rave.visiit.entity.TripVoucher;
import com.rave.visiit.util.Constants;

@Repository
public class FileUploadDaoImpl extends AbstractDao implements FileUploadDao {
	
	private static Log log = LogFactory.getLog(FileUploadDaoImpl.class);
	
	@Override
	public TripVoucher getByTripCode(String tripCode) {
		log.info("getByTripCode ::" + tripCode);
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(TripVoucher.class);
		c.add(Restrictions.eq("tripCode", tripCode));
		c.add(Restrictions.ne("isDeleted", Boolean.TRUE));
		if(c.list() != null && !c.list().isEmpty()){
			return (TripVoucher) c.list().get(0);
		}
		return null;
	}

	@Override
	public TripVoucher saveOrUpdateTripVoucher(TripVoucher tripVoucher) {
		log.info("saveOrUpdateTripVoucher >>Start TripCode ::" + tripVoucher.getTripCode());
		Session session=getSessionFactory().getCurrentSession();
		session.saveOrUpdate(tripVoucher);
		log.info("saveOrUpdateTripVoucher >>End ");
		return tripVoucher;
	}
	
	
	@Override
	public List<TripVoucher> getAllDeleteTripVoucher() {
		log.info("getAllDeleteTripVoucher :: Start" );
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(TripVoucher.class);
		c.add(Restrictions.eq("isDeleted", Boolean.TRUE));
		if(c.list() != null && !c.list().isEmpty()){
			return c.list();
		}
		return null;
	}

	@Override
	public void deleteTripVoucherById(Integer voucherId) {
		log.info("deleteTripVoucherById :: Start" );
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("delete TripVoucher where voucherId = :voucherId");
		query.setParameter("voucherId", voucherId);
		int result = query.executeUpdate();
		log.info("deleteTripVoucherById :: End " + result);
	}
	
	
	@Override
	public NewsLetter saveNewsLetter(NewsLetter newsLetter) {
		log.info("saveNewsLetter >>Start" );
		Session session=getSessionFactory().getCurrentSession();
		session.saveOrUpdate(newsLetter);
		log.info("saveNewsLetter >>End ");
		return newsLetter;
	}
	
	@Override
	public NewsLetterSubscriber saveSubscriber(NewsLetterSubscriber newsLetterSubscriber) {
		log.info("saveSubscriber >>Start" );
		Session session=getSessionFactory().getCurrentSession();
		session.saveOrUpdate(newsLetterSubscriber);
		log.info("saveSubscriber >>End ");
		return newsLetterSubscriber;
	}
	
	@Override
	@Transactional
	public List<NewsLetterSubscriber> getAllSubscriberByIsSent() {
		log.info("getAllSubscriberByIsSent :: Start" );
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(NewsLetterSubscriber.class);
		c.add(Restrictions.eq("isSent", Boolean.FALSE));
		if(c.list() != null && !c.list().isEmpty()){
			return c.list();
		}
		return null;
	}

	@Override
	public Integer bulkUpdateNewsLetterSubscriber() {
		log.info("bulkUpdateNewsLetter :: Start" );
		Session s = this.getSessionFactory().getCurrentSession();
		String hqlUpdate = "update NewsLetterSubscriber set isSent = 0 where isSent = 1";
		int updatedEntities = s.createQuery( hqlUpdate ).executeUpdate();
		log.info("bulkUpdateNewsLetter :: End" );
		return updatedEntities;
	}
	
	
	@Override
	public void deleteNewsLetter() {
		log.info("deleteNewsLetter Start");
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(NewsLetter.class);
		if(c.list() != null && !c.list().isEmpty()){
			NewsLetter news =  (NewsLetter) c.list().get(0);
			session.delete(news);
		}
	}

	@Override
	@Transactional
	public NewsLetter getNewsLetter() {
		log.info("getNewsLetter Start");
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(NewsLetter.class);
		if(c.list() != null && !c.list().isEmpty()){
			return (NewsLetter) c.list().get(0);
		}
		return null;
	}
	
	@Override
	public Integer updateNewsLetterSubscriber() {
		log.info("updateNewsLetterSubscriber :: Start" );
		Session s = this.getSessionFactory().getCurrentSession();
		
		Query q=s.createQuery("update NewsLetterSubscriber set isSent=:isSent,sentBy=:sendtBy,sentOn=:sentOn");  
		q.setParameter("isSent",1);  
		q.setParameter("sentBy",Constants.SYSTEM);
		q.setParameter("sentOn",new Timestamp(System.currentTimeMillis()));  
		//String hqlUpdate = "update NewsLetterSubscriber set isSent = 1 ,set sentBy=System ,set sentOn ='"+new Timestamp(System.currentTimeMillis())+"'";
		int updatedEntities = q.executeUpdate();
		log.info("updateNewsLetterSubscriber :: End" );
		return updatedEntities;
	}
}
