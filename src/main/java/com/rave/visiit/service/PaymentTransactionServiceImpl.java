package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.dao.PaymentTransactionDao;
import com.rave.visiit.entity.PaymentTransaction;

@Service
public class PaymentTransactionServiceImpl extends AbstractService implements
		PaymentTransactionService {
	
	private PaymentTransactionDao paymentTransactionDao;
	
	
	public PaymentTransactionDao getPaymentTransactionDao() {
		return paymentTransactionDao;
	}

	public void setPaymentTransactionDao(PaymentTransactionDao paymentTransactionDao) {
		this.paymentTransactionDao = paymentTransactionDao;
	}

	@Override
	@Transactional
	public PaymentTransaction saveorupdate(PaymentTransaction payment)
			throws Exception {
		// TODO Auto-generated method stub
		return getPaymentTransactionDao().saveorupdate(payment);
	}

	@Override
	@Transactional
	public boolean delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPaymentTransactionDao().delete(id);
	}

	@Override
	@Transactional
	public List<PaymentTransaction> getAll(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPaymentTransactionDao().getAll(id);
	}

	@Override
	@Transactional
	public PaymentTransaction get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return getPaymentTransactionDao().get(id);
	}

}
