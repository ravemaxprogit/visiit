package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.NewsLetter;
import com.rave.visiit.entity.NewsLetterSubscriber;
import com.rave.visiit.entity.TripVoucher;

public interface FileUploadService {
	
	public TripVoucher saveTripVoucher(TripVoucher tripVoucher);
	
	public TripVoucher getTripVoucher(String tripCode);
	
	public List<TripVoucher> getAllDeleteTripVoucher();
	
	public void deleteTripVoucherById(Integer voucherId) throws Exception;
	
	public NewsLetter saveNewsLetter(NewsLetter newsLetter);
	
	public NewsLetterSubscriber saveSubscriber(NewsLetterSubscriber newsLetterSubscriber);
	
	public List<NewsLetterSubscriber> getAllSubscriberByIsSent();
	
	public Integer bulkUpdateNewsLetterSubscriber();
	
	public void deleteNewsLetter();
	
	public NewsLetter getNewsLetter();
	
	public Integer updateNewsLetterSubscriber();
}
