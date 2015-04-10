package com.rave.visiit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.rave.visiit.dao.ModuleDao;
import com.rave.visiit.entity.Module;
import com.rave.visiit.util.ErrorConstants;


@Service
public class ModuleServiceImpl implements ModuleService {
	
	private ModuleDao moduleDao;
	
	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	@Override
	@Transactional
	public List<Module> listModule() throws Exception{
		List<Module> moduleList = moduleDao.listModule();
		if(CollectionUtils.isEmpty(moduleList)){
			throw new Exception(ErrorConstants.LIST_NOT_FOUND);
		}
		return moduleList;
	}

	@Override
	@Transactional
	public List<Module> getSubModulesForModuleId(int parentId) throws Exception {
		List<Module> moduleList = moduleDao.getSubModulesForModuleId(parentId);
		if(CollectionUtils.isEmpty(moduleList)){
			throw new Exception(ErrorConstants.LIST_NOT_FOUND);
		}
		return moduleList;
	}
	
	@Override
	@Transactional
	public List<Module> getSubModulesByModuleIds(List<Integer> parentIds) throws Exception {
		List<Module> moduleList = moduleDao.getSubModulesByModuleIds(parentIds);
		if(CollectionUtils.isEmpty(moduleList)){
			throw new Exception(ErrorConstants.LIST_NOT_FOUND);
		}
		return moduleList;
	}

}
