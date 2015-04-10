/**
 * 
 */
package com.rave.visiit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;

import sun.misc.BASE64Encoder;

import com.rave.visiit.entity.Images;

/**
 * @author Sandy
 *
 */
public class Helper {
	
	private static Logger log = Logger.getLogger(Helper.class);
	
	public static Date getSqlDate(String date){
		DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date parse , parsed = null;
	        String formattedDate = null;
			try {
				parse = originalFormat.parse(date);
	            formattedDate = targetFormat.format(parse);
	            parsed = targetFormat.parse(formattedDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        Date sql = new Date(parsed.getTime());
			return sql;
	}
	
	public static String codeMaker(String code){
		String codeStr = code.replace(Constants.PK_CODE_PREFIX, "");
		int codeInt = Integer.parseInt(codeStr);
		int newCode = codeInt + 1;
		String newCodeStr = Constants.PK_CODE_PREFIX + Integer.toString(newCode);
		System.out.println(newCodeStr+" ----------> new code");
		/*StringBuilder newCode = new StringBuilder();
		String codePrefix = null;
		String codeSufix = null;
		newCode.append(code);
		int intCode=0;
		try{
		    intCode=Integer.parseInt(newCode.substring(1, newCode.length()));
		    }catch(NumberFormatException ne){
		}
		if(intCode==0){
			try{
			    intCode=Integer.parseInt(newCode.substring(2, newCode.length()));
			    }catch(NumberFormatException ne){
			}
		}
		codeSufix = String.valueOf(++intCode);
	    codePrefix = newCode.substring(0, newCode.length()-codeSufix.length());*/
		return newCodeStr;
	}
	
	public static File getDirectory(){
		// Creating the directory to store file
		Properties prop = new Properties();
		File dir = null;
		try {
				InputStream input1 = Helper.class.getResourceAsStream("config.properties");
				prop.load(input1);
				dir = new File(prop.getProperty("imagedir"));
				log.info("dir =>"+dir);
		} catch (IOException e) {
			e.printStackTrace();
			log.info("Helper.java:: getDirectory => "+e.getMessage());
		}
        
		if (!dir.exists())
        dir.mkdirs();
        return dir;
	}
	
	public static String base64Img(File dir,String name){
		String imageDataString = ":)";
		File file = new File(dir.getAbsolutePath()
                + File.separator + name);
		FileInputStream imageInFile = null;
		try {
			imageInFile = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte imageData[] = new byte[(int) file.length()];
        try {
			imageInFile.read(imageData);
			imageInFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
        // Converting Image byte array into Base64 String      
        BASE64Encoder encoder=new BASE64Encoder();
        imageDataString = encoder.encode(imageData);
//        imageDataString = Base64.encodeBase64URLSafeString(imageData);
       
        return imageDataString;
	}
	
	public static void swapFile(String OldName, String newName, File dir){
		File fileTemp = new File(dir.getAbsolutePath()
                + File.separator + "tempName");
        File fileOld = new File(dir.getAbsolutePath()
                + File.separator + OldName);
        File fileNew= new File(dir.getAbsolutePath()
                + File.separator + newName);
        
        fileNew.renameTo(fileTemp);
        fileOld.renameTo(fileNew);
        fileTemp.renameTo(fileOld);
	}
	
	public static void swapFile(String module,String subModule, String OldName, String newName){
		File fileTemp = new File(Helper.getDirectory()
                + File.separator + "tempName");
        File fileOld = new File(Helper.getDirectory()+ File.separator +module+
        		 File.separator +subModule+ File.separator + OldName);
        File fileNew= new File(Helper.getDirectory()+ File.separator +module+
       		     File.separator +subModule+ File.separator + newName);
        
        fileNew.renameTo(fileTemp);
        fileOld.renameTo(fileNew);
        fileTemp.renameTo(fileOld);
	}
	
	public static void removeFile(String module,String subModule,String fileName){
		try {
		int intfile = Integer.parseInt(fileName);
		File dir =new File(Helper.getDirectory()
                + File.separator + module + File.separator + subModule);

		Path from = null;
        Path to = null;
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
              };
   
       List<Images> list = getImgAsList(module,subModule);
        for (int i = intfile; i < list.size(); i++) {        	
        	from = Paths.get(dir.getAbsolutePath() + File.separator + (i+1));
			to = Paths.get(dir.getAbsolutePath() + File.separator  + i);
	
				Files.copy(from, to, options);
			
       }
		File delateFile = new File(dir+ File.separator +  list.size());
		delateFile.setWritable(true);
		delateFile.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void removeFileByName(String module,String subModule,String fileName){
		try {
		File dir =new File(Helper.getDirectory()
                + File.separator + module + File.separator + subModule);
		File delateFile = new File(dir+ File.separator +  fileName);
		delateFile.setWritable(true);
		delateFile.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String saveImgFile(String oldName, String module, String subModule){	
		File oldfile = new File(Helper.getDirectory()+File.separator+oldName);
		File newfile = Helper.getNewFile(module, subModule, Helper.getDirectory());
		if(oldfile.renameTo(newfile)){
			return newfile.getName();
		}
		return newfile.getName();
	}
	
	public static String saveImgFileCrop(String oldName, String module, String subModule){	
		File oldfile = new File(Helper.getDirectory()+File.separator+module+File.separator+subModule+File.separator+oldName);
		File newfile = Helper.getNewFileCrop(module, subModule, Helper.getDirectory());
		if(oldfile.renameTo(newfile)){
			return newfile.getName();
		}
		return newfile.getName();
	}
	
	public static File getNewFile(String mod,String subMod, File file){
		File dir =new File(file.getAbsolutePath()
                + File.separator + mod + File.separator + subMod);
		File newDir = null;
		String newfile ="1";
		if (!dir.exists()){
            dir.mkdirs();
        }else{
        	newfile = makeNewFileName(getAllFiles(dir));
     
        }
		newDir = new File(dir.getAbsolutePath() + File.separator + newfile);
		return newDir;
	}
	
	public static File getNewFileCrop(String mod,String subMod, File file){
		File dir =new File(file.getAbsolutePath()
                + File.separator + mod + File.separator + subMod);
		File newDir = null;
		String newfile ="1";
		if (!dir.exists()){
            dir.mkdirs();
        }else{
        	newfile = makeNewFileNameForCrop(getAllFiles(dir));
     
        }
		newDir = new File(dir.getAbsolutePath() + File.separator + newfile);
		return newDir;
	}
	
	public static List<String> getAllFiles(File folder){
		List<String> fileList = new ArrayList<String>();
		File[] listOfFiles = folder.listFiles();
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  fileList.add(listOfFiles[i].getName());
		          log.info("File " + listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		    	  log.info("Directory " + listOfFiles[i].getName());
		      }
		    }
		    return fileList;
	}
	
	public static String makeNewFileName(List<String> list){
		if(list.isEmpty()){ 
			return String.valueOf(1);
		}else{
		    return 	String.valueOf(list.size()+1);
		}
	}
	
	
	public static List<Images> getImgAsList(String module,String subModule){
		List<Images> imgList=new ArrayList<Images>();
        int img = 0;
		try{
		String imgHeader = "data:image/jpeg;base64,";
		File dir =new File(getDirectory().getAbsolutePath()
                + File.separator + module + File.separator + subModule);
		File[] listOfFiles = dir.listFiles();
		if(listOfFiles != null && listOfFiles.length > 0){
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory()){				
			continue;
			}
			++img;
			imgList.add(new Images(img+"",imgHeader+base64Img(dir, img+""),subModule));
		   }
	    }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return imgList;
	}
	
	public static String getBaseDirectory(){
		// Creating the directory to store file
		Properties prop = new Properties();
		String baseUrl = null;
		try {
				InputStream inputFile = Helper.class.getResourceAsStream(Constants.CONFIG_PROPERTIES);
				prop.load(inputFile);
				baseUrl = prop.getProperty(Constants.IMAGE_DIR);
		} catch (IOException e) {
			e.printStackTrace();
			log.info("Helper.java:: getBaseDirectory => "+e.getMessage());
		}
        return baseUrl;
	}
	
	public static String sha512(String param){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] bytes = param.getBytes();
		md.update(bytes);			
		byte[] mdbytes = md.digest();
		//convert the byte to hex format method
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mdbytes.length; i++){
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100,16).substring(1));
		}
	    System.out.println("Hex format : " + sb.toString());
		return sb.toString();
	}
	
	public static String makeNewFileNameForCrop(List<String> list){
		if(list.isEmpty()){ 
			return String.valueOf(1);
		}else{
		    return 	String.valueOf(list.size());
		}
	}
	
}
