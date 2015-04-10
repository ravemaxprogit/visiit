package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.Designation;

public interface DesignationDao {
	
	public void saveDesignation(Designation desg) throws Exception;
    public List<Designation> listDesignation();
    public Designation getDesignationById(int id);
    public void removeDesignation(int id);
	String getNextDesgCode() throws Exception;
	Long getCountOfDesignation(int id) throws Exception;
	boolean checkDesignationFieldExists(String fieldName, String fieldValue,
			Integer id);
	boolean checkFieldRefInAnyWhere(Integer id);
}
