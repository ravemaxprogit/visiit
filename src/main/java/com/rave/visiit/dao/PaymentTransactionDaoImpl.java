package com.rave.visiit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.PaymentTransaction;

@Repository
public class PaymentTransactionDaoImpl extends AbstractDao implements
		PaymentTransactionDao {

	@Override
	public PaymentTransaction saveorupdate(PaymentTransaction payment)
			throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		session.saveOrUpdate(payment);
		return payment;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		PaymentTransaction payment = (PaymentTransaction) session.load(PaymentTransaction.class, id);
		if(payment != null) session.delete(payment);
		return true;
	}

	@Override
	public List<PaymentTransaction> getAll(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		Criteria c = session.createCriteria(PaymentTransaction.class);
		return c.list();
	}

	@Override
	public PaymentTransaction get(Integer id) throws Exception {
		Session session=getSessionFactory().getCurrentSession();
		PaymentTransaction payment = (PaymentTransaction) session.load(PaymentTransaction.class, id);
		return payment;
	}

}
