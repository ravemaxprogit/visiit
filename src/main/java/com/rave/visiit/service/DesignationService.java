package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.Designation;

public interface DesignationService {
	
	public void saveDesignation(Designation desg) throws Exception;
    public List<Designation> listDesignation() throws Exception;
    public Designation getDesignationById(int id) throws Exception;
    public void removeDesignation(int id) throws Exception;
	public void deleteDesignation(int designationId) throws Exception;

}
