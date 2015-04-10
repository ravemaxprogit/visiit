package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.NewsLetter;
import com.rave.visiit.entity.NewsLetterSubscriber;
import com.rave.visiit.entity.TripVoucher;

public interface FileUploadDao {
	
	public TripVoucher getByTripCode(String tripCode);
	
	public TripVoucher saveOrUpdateTripVoucher(TripVoucher tripVoucher);
	
	public List<TripVoucher> getAllDeleteTripVoucher();
	
	public void deleteTripVoucherById(Integer voucherId);
	
	public NewsLetter saveNewsLetter(NewsLetter newsLetter);
	
	public NewsLetterSubscriber saveSubscriber(NewsLetterSubscriber newsLetterSubscriber);
	
	public List<NewsLetterSubscriber> getAllSubscriberByIsSent();
	
	public Integer bulkUpdateNewsLetterSubscriber();
	
	public void deleteNewsLetter();
	
	public NewsLetter getNewsLetter();
	
	public Integer updateNewsLetterSubscriber();
	
}
