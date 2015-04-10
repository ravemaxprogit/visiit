package com.rave.visiit.service;

import java.util.List;

import com.rave.visiit.entity.Module;

public interface ModuleService {
	
    List<Module> listModule() throws Exception;
    List<Module> getSubModulesForModuleId(int id) throws Exception;
    List<Module> getSubModulesByModuleIds(List<Integer> ids) throws Exception;

}
