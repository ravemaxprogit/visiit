package com.rave.visiit.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rave.visiit.dao.FileUploadDao;
import com.rave.visiit.entity.NewsLetter;
import com.rave.visiit.entity.NewsLetterSubscriber;
import com.rave.visiit.entity.TripVoucher;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	private static Log log = LogFactory.getLog(FileUploadServiceImpl.class);
	
	@Autowired(required = true)
	@Qualifier(value = "fileUploadDao")
	private FileUploadDao fileUploadDao;
	
	

	public FileUploadDao getFileUploadDao() {
		return fileUploadDao;
	}

	public void setFileUploadDao(FileUploadDao fileUploadDao) {
		this.fileUploadDao = fileUploadDao;
	}

	@Override
	@Transactional
	public TripVoucher saveTripVoucher(TripVoucher tripVoucher) {
		log.info("saveOrUpdateTripVoucher  Start");
		return fileUploadDao.saveOrUpdateTripVoucher(tripVoucher);
		
	}

	@Override
	@Transactional
	public TripVoucher getTripVoucher(String tripCode) {
		log.info("getTripVoucher  :: " + tripCode);
		return fileUploadDao.getByTripCode(tripCode);
	}
	
	
	@Override
	@Transactional
	public List<TripVoucher> getAllDeleteTripVoucher() {
		log.info("getAllTripVoucher  ::Start ");
		return fileUploadDao.getAllDeleteTripVoucher();
	}

	@Override
	@Transactional
	public void deleteTripVoucherById(Integer voucherId) throws Exception {
		log.info("deleteTripVoucherById  ::Start with voucherId" +voucherId);
		fileUploadDao.deleteTripVoucherById(voucherId);
	}
	
	@Override
	@Transactional
	public NewsLetter saveNewsLetter(NewsLetter newsLetter) {
		log.info("saveNewsLetter  Start");
		return fileUploadDao.saveNewsLetter(newsLetter);
		
	}
	
	@Override
	@Transactional
	public NewsLetterSubscriber saveSubscriber(NewsLetterSubscriber newsLetterSubscriber) {
		log.info("saveSubscriber  Start");
		return fileUploadDao.saveSubscriber(newsLetterSubscriber);
		
	}
	
	@Override
	@Transactional
	public List<NewsLetterSubscriber> getAllSubscriberByIsSent() {
		log.info("getAllTripVoucher  ::Start ");
		return fileUploadDao.getAllSubscriberByIsSent();
	}

	@Override
	@Transactional
	public Integer bulkUpdateNewsLetterSubscriber() {
		log.info("bulkUpdateNewsLetterSubscriber  ::Start ");
		Integer updateEntities = null;
		try{
			updateEntities =  fileUploadDao.bulkUpdateNewsLetterSubscriber();
		}catch(Exception e){
			e.printStackTrace();
		}
		return updateEntities;
	}
	
	@Override
	@Transactional
	public void deleteNewsLetter() {
		log.info("deleteNewsLetter  Start ");
		fileUploadDao.deleteNewsLetter();
	}

	@Override
	@Transactional
	public NewsLetter getNewsLetter() {
		log.info("deleteNewsLetter  Start ");
		return  fileUploadDao.getNewsLetter();
	}
	
	@Override
	@Transactional
	public Integer updateNewsLetterSubscriber() {
		log.info("updateNewsLetterSubscriber  ::Start ");
		Integer updateEntities = null;
		try{
			updateEntities =  fileUploadDao.updateNewsLetterSubscriber();
		}catch(Exception e){
			e.printStackTrace();
		}
		return updateEntities;
	}
}
