package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.PaymentTransaction;

public interface PaymentTransactionService {
	
	public PaymentTransaction saveorupdate(PaymentTransaction payment) throws Exception;

	public boolean delete(Integer id) throws Exception;

	public List<PaymentTransaction> getAll(Integer id) throws Exception;

	public PaymentTransaction get(Integer id) throws Exception;

}
