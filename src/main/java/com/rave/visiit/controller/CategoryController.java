package com.rave.visiit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rave.visiit.entity.Category;
import com.rave.visiit.entity.Hotel;
import com.rave.visiit.entity.Images;
import com.rave.visiit.entity.Package;
import com.rave.visiit.entity.PackageExclusion;
import com.rave.visiit.entity.PackageHotel;
import com.rave.visiit.entity.PackageInclusion;
import com.rave.visiit.entity.PackageItinerary;
import com.rave.visiit.model.CategoryModel;
import com.rave.visiit.model.ImageData;
import com.rave.visiit.service.CategoryService;
import com.rave.visiit.service.HotelService;
import com.rave.visiit.service.PackageCategoryService;
import com.rave.visiit.service.PackageExclusionService;
import com.rave.visiit.service.PackageHotelService;
import com.rave.visiit.service.PackageInclusionService;
import com.rave.visiit.service.PackageItineraryService;
import com.rave.visiit.service.PackageService;
import com.rave.visiit.util.Constants;
import com.rave.visiit.util.ErrorConstants;
import com.rave.visiit.util.Helper;
import com.rave.visiit.util.VisiitRunTimeException;

@Controller
public class CategoryController {
	
	private static Logger log = LogManager.getLogger(CategoryController.class);
	
	@Autowired(required = true)
	@Qualifier(value="categoryService")
	private CategoryService categoryService;
	
	@Autowired(required = true)
	@Qualifier(value="hotelService")
	private HotelService hotelService;
	
	@Autowired(required = true)
	@Qualifier(value="packageService")
	private PackageService packageService;
	
	@Autowired(required = true)
	@Qualifier(value = "packageItineraryService")
	PackageItineraryService packageItineraryService;
	
	@Autowired(required = true)
	@Qualifier(value = "packageExclusionService")
	PackageExclusionService packageExclusionService;
	
	@Autowired(required = true)
	@Qualifier(value = "packageInclusionService")
	PackageInclusionService packageInclusionService;
	
	@Autowired(required = true)
	@Qualifier(value = "packageHotelService")
	private PackageHotelService packageHotelService;

	@Autowired(required = true)
	@Qualifier(value = "packageCategoryService")
	private PackageCategoryService packageCategoryService;
	
	@RequestMapping("/listCategory")
	public String categoryNavig(){
		return "PackageCategory";
	}
	
	@RequestMapping(value = {"/secure/categoryList","/categoryList"}, method = RequestMethod.GET,headers="Accept=application/x-www-form-urlencoded")
	public @ResponseBody String getCategory(){
		String resultStr = "";
		List<Category> category=null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			category = this.categoryService.getAll();
			if(category == null || category.isEmpty()){throw new Exception("Category Information Not Found");}
			resultStr = mapper.writeValueAsString(Arrays.asList(category));
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultStr;
	}
	
	@RequestMapping(value = {"/secure/getCategoryById","/getCategoryById"}, method = RequestMethod.GET,headers="Accept=application/x-www-form-urlencoded")
	public @ResponseBody ModelMap getCategoryById(@RequestParam("catId") String catId){
		ModelMap model = new ModelMap();
		try {
			
			Category category = categoryService.get(Integer.parseInt(catId));
			if(category == null){throw new VisiitRunTimeException("Category Information Not Found");}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("category", category);
			
		} catch (Exception e) {
			log.info("Error while getting category " +e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
			
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/saveImg","/saveImg"}, method = RequestMethod.POST, headers="Accept=application/x-www-form-urlencoded")
	public @ResponseBody String saveImg(@RequestParam("imgStr") String imgStr,@RequestParam("imgSubStr") String imgSubStr) {
		String resultStr = null;
		try {
			if(imgStr.contains("category") && imgSubStr.contains("icon")){
				removeExistingImages(imgStr, imgSubStr);
			}
			String fileName = Helper.saveImgFile("file", imgStr, imgSubStr);
			String url = "/" + imgStr + "/" + imgSubStr + "/" + fileName + ",";
			if(imgStr != null && imgStr.equalsIgnoreCase("package")){
				String pkId = null;
				String[] urlSplit = imgSubStr.split("/");
				if(urlSplit != null && urlSplit.length > 1){
					pkId = urlSplit[0];
				}else {
					pkId = imgSubStr;
				}
				
				Package pack = packageService.getPackageByCode(pkId);
				if(imgSubStr.contains("tile")){
					if(pack.getTileImageUrl() != null && !pack.getTileImageUrl().isEmpty()){
						url = pack.getTileImageUrl()+url;
					}
					pack.setTileImageUrl(url);
				}else{
					if(pack.getImageUrl() != null && !pack.getImageUrl().isEmpty()){
						url = pack.getImageUrl()+url;
					}
					pack.setImageUrl(url);
				}
				
				packageService.saveorupdate(pack);
			}
			if(imgStr != null && imgStr.equalsIgnoreCase("category")){
				String catId = null;
				String[] urlSplit = imgSubStr.split("/");
				if(urlSplit != null && urlSplit.length > 1){
					catId = urlSplit[0];
				}else {
					catId = imgSubStr;
				}
				
				Category category = categoryService.get(Integer.parseInt(catId));
				if(imgSubStr.contains("icon")){
					if(category.getIconUrl() == null || category.getIconUrl().isEmpty()){
						category.setIconUrl(url);
					}
					//category.setIconUrl(url);
				}else{
					if(category.getImageUrl() != null && !category.getImageUrl().isEmpty()){
						url = category.getImageUrl()+","+url;
					}
					category.setImageUrl(url);
				}
				categoryService.saveorupdate(category);
			}
			resultStr = imgSubStr;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resultStr = "Error Occured while saving Image....\n\n"+e.getMessage();
		}
		return resultStr;
	}

	@RequestMapping(value = {"/secure/getAllImg","/getAllImg"}, method = RequestMethod.POST,headers="Accept=application/x-www-form-urlencoded")
	public @ResponseBody String getAllImg(@RequestParam("imgStr") String imgStr,@RequestParam("imgSubStr") String imgSubStr){
		String resultStr = "";
		List<Images> imgList = null;
		try {
				ObjectMapper mapper = new ObjectMapper();
				imgList = Helper.getImgAsList(imgStr, imgSubStr);
				if(!CollectionUtils.isEmpty(imgList)){
					resultStr = mapper.writeValueAsString(Arrays.asList(imgList));
				}
			}catch (Exception e) {
				log.info("Error occur while getting images  " + e.getMessage());
			}
		return resultStr;

	}
	
	@RequestMapping(value = {"/secure/removeImg","/removeImg"}, method = RequestMethod.POST, headers="Accept=application/x-www-form-urlencoded")
	public @ResponseBody String removeCatImg(@RequestParam("imgStr") String imgStr,@RequestParam("imgSubStr") String imgSubStr, @RequestParam("imgName") String imgName) {
		String resultStr = null;
		String url = "/" + imgStr + "/" + imgSubStr + "/" + imgName;
		url=url+",";
		try {
			Helper.removeFile(imgStr, imgSubStr, imgName);
			
			if(imgStr != null && imgStr.equalsIgnoreCase("package")){
				String pkId = null;
				String[] urlSplit = imgSubStr.split("/");
				if(urlSplit != null && urlSplit.length > 1){
					pkId = urlSplit[0];
				}else {
					pkId = imgSubStr;
				}
				
				Package pack = packageService.getPackageByCode(pkId);
				if(imgSubStr.contains("tile")){
					if(pack.getTileImageUrl() != null && !pack.getTileImageUrl().isEmpty()){
						url = pack.getTileImageUrl().replace(url, "");
					}
					pack.setTileImageUrl(url);
				}else{
					if(pack.getImageUrl() != null && !pack.getImageUrl().isEmpty()){
						int j = 1;
						String urlStr = pack.getImageUrl().replace(url, "");
						String [] urls = null;
						if(urlStr != null && !urlStr.isEmpty()){urls = urlStr.split(",");}
						String newUrl="";
						if(urls != null && urls.length > 0){
							for (int i = 0; i < urls.length; i++) {
							  if(urls[i] != null && !urls[i].isEmpty()){
								newUrl = newUrl+"/" + imgStr + "/" + imgSubStr + "/" + j+",";
								j++;
							 }
							}
						}
						if(newUrl != null && !newUrl.isEmpty()){
							pack.setImageUrl(newUrl);
						}else{pack.setImageUrl("");}
					}
					
				}
				
				packageService.saveorupdate(pack);
			}
			
			if(imgStr != null && imgStr.equalsIgnoreCase("category")){
				String catId = null;
				String[] urlSplit = imgSubStr.split("/");
				if(urlSplit != null && urlSplit.length > 1){
					catId = urlSplit[0];
				}else {
					catId = imgSubStr;
				}
				
				Category category = categoryService.get(Integer.parseInt(catId));
				if(imgSubStr.contains("icon")){
					if(category.getIconUrl() != null && !category.getIconUrl().isEmpty()){
						url = category.getIconUrl().replace(url, "");
					}
					category.setIconUrl(url);
				}else{
					if(category.getImageUrl() != null && !category.getImageUrl().isEmpty()){
						String urlStr = category.getImageUrl().replace(url, "");
						String [] urls = null;
						int j = 1;
						if(urlStr != null && !urlStr.isEmpty()){urls = urlStr.split(",");}
						String newUrl="";
						if(urls != null && urls.length > 0){
							for (int i = 0; i < urls.length; i++) {
								if(urls[i] != null && !urls[i].isEmpty()){
									newUrl = newUrl+"/" + imgStr + "/" + imgSubStr + "/" + j+",";
									j++;
								}
							}
						}
						if(newUrl != null && !newUrl.isEmpty()){
							category.setImageUrl(newUrl);
						}else{category.setImageUrl("");}
					}
				}
				categoryService.saveorupdate(category);
			}
			resultStr = imgSubStr;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resultStr = "Error Occured while removing Image....\n\n"+e.getMessage();
		}
		return resultStr;
	}
	
	@RequestMapping(value = {"/secure/reorderImg","/reorderImg"}, method = RequestMethod.POST, headers="Accept=application/x-www-form-urlencoded")
	public @ResponseBody String reorderImg(@RequestParam("imgStr") String imgStr,@RequestParam("imgSubStr") String imgSubStr) {
		String resultStr = null;
		String[] catImgArr = imgSubStr.split(",");
		try {
			Helper.swapFile(imgStr, catImgArr[0], catImgArr[1], catImgArr[2]);
			resultStr = catImgArr[0];
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resultStr = "Error Occured while reordering Image....\n\n"+e.getMessage();
		}
		return resultStr;
	}
	
	@RequestMapping(value = {"/secure/saveCategory","/saveCategory"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap saveCategory(@RequestParam("catStr") String catStr) {
		ModelMap model = new ModelMap();
		Integer seqId = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<Category> ref = new TypeReference<Category>() {};
			Category category = mapper.readValue(catStr, ref);
			seqId = category.getSeqId();
			category.setCatFilePath("");
			category.setCatLevel(1);
			category.setCatModifiedBy(Constants.ADMIN);
			category.setCatModifiedOn(new Timestamp(new Date().getTime()));
			if(category.getCatId() != null){
				String imageUrl = categoryService.getImageUrlById(category.getCatId());
				if(imageUrl != null && !imageUrl.isEmpty()){
					category.setImageUrl(imageUrl);
				}
				String iconUrl = categoryService.getIconUrlById(category.getCatId());
				if(iconUrl != null && !iconUrl.isEmpty()){
					category.setIconUrl(iconUrl);
				}
			}
			categoryService.saveorupdate(category);
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put(Constants.MESSAGE_STR, "Saved Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while saving category....\n\n "+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			} else if(e instanceof ConstraintViolationException){
				model.put(Constants.MESSAGE_STR, seqId + " : " +ErrorConstants.THIS_SEQUENCE_ALREADY_EXISTS);
			} else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/getTopCategory","/getTopCategory"}, method = RequestMethod.GET)
	public @ResponseBody ModelMap getTopCategory(){
		ModelMap model = new ModelMap();
		List<Category> category=null;
		List<CategoryModel> catList = new ArrayList<CategoryModel>();
		try {
			category = packageCategoryService.getValidCategories();
			String iconUrl = "";
			for (Category cat : category) {
					if(cat.getIconUrl() != null && !cat.getIconUrl().isEmpty()){
						String[] url = cat.getIconUrl().split(",");
						if(url != null && url.length > 0){
							iconUrl = url[0];
						}
					catList.add(new CategoryModel(cat.getCatId(),cat.getCatTitle(),cat.getImageUrl(),iconUrl,cat.getSeqId()));
				}
			}
			model.put(Constants.STATUS_STR, Constants.OK_STR);
			model.put("catsize", category.size());
			model.put("catList", catList);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Error while getting category " + e);
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/saveImage","/saveImage"}, method = RequestMethod.POST, headers="Accept=application/x-www-form-urlencoded")
	public @ResponseBody String saveImage(@RequestParam("imgStr") String imgStr,@RequestParam("imgSubStr") String imgSubStr,@RequestParam("objectId") String objectId) {
		String resultStr = null;
		try {
			if(imgStr.contains("package") && imgSubStr.contains("hotel")){
				removeExistingImages(imgStr, imgSubStr);
			}
			if(imgSubStr.contains("activity") || imgStr.contains("hotel") || 
					imgSubStr.contains("exclusion") || imgSubStr.contains("inclusion")){
				removeExistingImages(imgStr, imgSubStr);				
			}
		
			String fileName = Helper.saveImgFile("file", imgStr, imgSubStr);
			String url = "/" + imgStr + "/" + imgSubStr + "/" + fileName;
			
			if(imgSubStr != null && !imgSubStr.isEmpty()){
				if(imgStr.contains("package") && imgSubStr.contains("activity")){
					saveActivityUrl(objectId, url);
				}
				if(imgStr.contains("hotel")){
					saveHotelUrl(objectId, url);
				}
				if(imgStr.contains("package") && imgSubStr.contains("exclusion")){
					saveExclusionUrl(objectId, url);
				}
				if(imgStr.contains("package") && imgSubStr.contains("inclusion")){
					saveInclusionUrl(objectId, url);
				}
				if(imgStr.contains("package") && imgSubStr.contains("hotel")){
					savePackageHotelUrl(objectId, url);
				}
				
				
			}
			resultStr = imgSubStr;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resultStr = "Error Occured while saving Image....\n\n"+e.getMessage();
		}
		return resultStr;
	}

	private void removeExistingImages(String imgStr, String imgSubStr) {
		List<Images> list = Helper.getImgAsList(imgStr,imgSubStr);
		if(list != null && !list.isEmpty()){
			for (int i = 1; i <= list.size(); i++) {
				Helper.removeFileByName(imgStr, imgSubStr, Integer.toString(i));
			}			
		}
	}

	private void saveActivityUrl(String objectId, String url)
			throws Exception {
			PackageItinerary packIt = packageItineraryService.get(Integer
					.parseInt(objectId));
			if(packIt != null){
				if(packIt.getImageUrl() != null && !packIt.getImageUrl().isEmpty()){
					packIt.setImageUrl("");
				}
				packIt.setImageUrl(url);
				packageItineraryService.saveorupdate(packIt);
			}
	}

	private void saveHotelUrl(String objectId, String url)
			throws Exception {
			Hotel hotel = hotelService.get(Integer.parseInt(objectId));
			if(hotel != null){
				if(hotel.getImageUrl() != null && !hotel.getImageUrl().isEmpty()){
					hotel.setImageUrl("");
				}
				hotel.setImageUrl(url);
				hotelService.saveorupdate(hotel);
		}
	}

	private void saveExclusionUrl(String objectId, String url)
			throws Exception {
			PackageExclusion packageExclusion = packageExclusionService.get(Integer.parseInt(objectId));
			if(packageExclusion != null){
				if(packageExclusion.getImageUrl() != null && !packageExclusion.getImageUrl().isEmpty()){
					packageExclusion.setImageUrl("");
				}
				packageExclusion.setImageUrl(url);
				packageExclusionService.saveorupdate(packageExclusion);
			}
	}

	private void savePackageHotelUrl(String objectId, String url)
			throws Exception {
			PackageHotel packageHotel = packageHotelService.get(Integer.parseInt(objectId));
			if(packageHotel != null){
				if(packageHotel.getImageUrl() != null && !packageHotel.getImageUrl().isEmpty()){
					packageHotel.setImageUrl("");
				}
				packageHotel.setImageUrl(url);
				packageHotelService.saveorupdate(packageHotel);
			}
	}
	
	private void saveInclusionUrl(String objectId, String url)
			throws Exception {
			PackageInclusion packageInclusion = packageInclusionService.get(Integer.parseInt(objectId));
			if(packageInclusion != null){
				if(packageInclusion.getImageUrl() != null && !packageInclusion.getImageUrl().isEmpty()){
					packageInclusion.setImageUrl("");
				}
				packageInclusion.setImageUrl(url);
				packageInclusionService.saveorupdate(packageInclusion);
			}
	}
	
	@RequestMapping(value = {"/secure/deleteCategory","/deleteCategory"}, method = RequestMethod.POST)
	public @ResponseBody ModelMap deleteCategory(@RequestParam("catId") String catId) {
		ModelMap model = new ModelMap();
		try {
			Long count = packageService.getPackageCountByCategory(Integer.parseInt(catId));
			if(count == null || count == 0){
				Category category = categoryService.get(Integer.parseInt(catId));
				if(category != null){
					category.setIsDeleted(Boolean.TRUE);
					categoryService.saveorupdate(category);
				}
				model.put(Constants.STATUS_STR, Constants.STATUS_Ok);
				model.put(Constants.MESSAGE_STR, "Deleted Successfully");
			} else {throw new VisiitRunTimeException(ErrorConstants.CATEGORY_REFERRING_BY_PACKAGE);}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error Occured while deleting category....\n\n "+ e.getMessage());
			model.put(Constants.STATUS_STR, Constants.ERROR_STR);
			if(e instanceof VisiitRunTimeException){
				model.put(Constants.MESSAGE_STR, e.getMessage());
			}else {
				model.put(Constants.MESSAGE_STR, ErrorConstants.SERVICE_NOT_AVAILABLE);
			}
		}
		return model;
	}
	
	@RequestMapping(value = {"/secure/uploadImage","/uploadImage"}, method = RequestMethod.POST, headers="Accept=application/x-www-form-urlencoded")
	public @ResponseBody String saveCropedImage(@RequestParam("data") String imageDataStr,
			@RequestParam("file") MultipartFile file) {
		String resultStr = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<ImageData> ref = new TypeReference<ImageData>() {};
			ImageData imageData = mapper.readValue(imageDataStr, ref);
			String imgSubStr = imageData.getSubModule();
			String objectId = imageData.getObjectId();
			String imgStr = imageData.getModule();
			if(imgStr.contains("package") && imgSubStr.contains("hotel")){
				removeExistingImages(imgStr, imgSubStr);
			}
			if(imgSubStr.contains("activity") || imgStr.contains("hotel") || 
					imgSubStr.contains("exclusion") || imgSubStr.contains("inclusion")){
				removeExistingImages(imgStr, imgSubStr);				
			}
			if(imgStr.contains("category") && imgSubStr.contains("icon")){
				removeExistingImages(imgStr, imgSubStr);
			}
		    // Create the file on server
			File dir =new File(Helper.getDirectory().getAbsolutePath() + File.separator + imgStr + File.separator + imgSubStr);
			
			if (!dir.exists()){
	            dir.mkdirs();
	            }
			List<String> list = Helper.getAllFiles(dir);
			String fileName;
			if(list.isEmpty()){ 
				fileName =  String.valueOf(1);
			}else{
				fileName = String.valueOf(list.size()+1);
			}
			
            File serverFile = new File(Helper.getDirectory().getAbsolutePath()
                    + File.separator + imgStr + File.separator + imgSubStr + File.separator +fileName);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(file.getBytes());
            stream.close();
            
			//String fileName = Helper.saveImgFileCrop("file", imgStr, imgSubStr);
			String url = "/" + imgStr + "/" + imgSubStr + "/" + fileName;
			
			if(imgSubStr != null && !imgSubStr.isEmpty()){
				if(imgStr.contains("package") && imgSubStr.contains("activity")){
					saveActivityUrl(objectId, url);
					return imgSubStr;
				}
				if(imgStr.contains("hotel")){
					saveHotelUrl(objectId, url);
					return imgSubStr;
				}
				if(imgStr.contains("package") && imgSubStr.contains("exclusion")){
					saveExclusionUrl(objectId, url);
					return imgSubStr;
				}
				if(imgStr.contains("package") && imgSubStr.contains("inclusion")){
					saveInclusionUrl(objectId, url);
					return imgSubStr;
				}
				if(imgStr.contains("package") && imgSubStr.contains("hotel")){
					savePackageHotelUrl(objectId, url);
					return imgSubStr;
				}
				if(imgStr != null && imgStr.equalsIgnoreCase("package")){
					Package pack = packageService.get(Integer.parseInt(objectId));
					if(imgSubStr.contains("tile")){
						if(pack.getTileImageUrl() != null && !pack.getTileImageUrl().isEmpty()){
							url = pack.getTileImageUrl()+url;
						}
						pack.setTileImageUrl(url+",");
					}else{
						if(pack.getImageUrl() != null && !pack.getImageUrl().isEmpty()){
							url = pack.getImageUrl()+url;
						}
						pack.setImageUrl(url+",");
					}
					
					packageService.saveorupdate(pack);
					return imgSubStr;
				}
				if(imgStr != null && imgStr.equalsIgnoreCase("category")){
										
					Category category = categoryService.get(Integer.parseInt(objectId));
					if(imgSubStr.contains("icon")){
						if(category.getIconUrl() != null && !category.getIconUrl().isEmpty()){
							category.setIconUrl("");
						}
						category.setIconUrl(url);
					}else{
						if(category.getImageUrl() != null && !category.getImageUrl().isEmpty()){
							url = category.getImageUrl()+url;
						}
						category.setImageUrl(url);
					}
					categoryService.saveorupdate(category);
					return imgSubStr;
				}
			}
			resultStr = imgSubStr;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			resultStr = "Error Occured while saving Image....\n\n"+e.getMessage();
		}
		return resultStr;
	}
}
