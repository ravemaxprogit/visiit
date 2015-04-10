package com.rave.visiit.dao;

import java.util.List;

import com.rave.visiit.entity.Module;

public interface ModuleDao {
	
    List<Module> listModule();
    List<Module> getSubModulesForModuleId(int parentId);
    List<Module> getSubModulesByModuleIds(List<Integer> ids) throws Exception;
	List<Module> getModulesByModuleIds(List<Integer> userModuleIds);
}
