package com.rave.visiit.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.rave.visiit.entity.Designation;
import com.rave.visiit.entity.User;
import com.rave.visiit.util.CommonUtil;

@Repository
public class DesignationDaoImp extends AbstractDao implements DesignationDao {
	
private static final Logger logger = LoggerFactory.getLogger(DesignationDaoImp.class); 
	
	@Override
	public void saveDesignation(Designation desg) throws Exception {
		 save(desg);
		 System.out.println(desg.getDsgCode());
	     logger.info("Designation saved successfully, Designation Details="+desg);
	}

	@Override
	public List<Designation> listDesignation(){
		logger.debug("getting all Country");
		Session session = getSessionFactory().getCurrentSession();
		List<Designation> list = null;
		if (session != null) {
			Criteria critera = session.createCriteria(Designation.class);
			critera.add(Restrictions.eq("isDeleted", false));
			list = critera.list();
		}
		return list;
	}

	@Override
	public Designation getDesignationById(int id) {
		Session session = getSessionFactory().getCurrentSession();      
		Designation desg = (Designation) session.get(Designation.class, new Integer(id));
        logger.info("Designation loaded successfully, Designation details="+desg);
        return desg;
	}

	
	@Override
	public boolean checkDesignationFieldExists(String fieldName, String fieldValue, Integer id) {
		Session session = getSessionFactory().getCurrentSession();      
		List<Designation> list = null;
		boolean isexists = false;
		if (session != null) {
			Criteria c = session.createCriteria(Designation.class);
			c.add(Restrictions.eq(fieldName, fieldValue));
			if(id!=null){
				c.add(Restrictions.ne("dsgId", id));
			}			
			c.add(Restrictions.eq("isDeleted", false));
			list = c.list();
			if(list!=null && list.size()>0){
				isexists = true;
			}
		}
		logger.debug("checkDesignationFieldExists for fieldName:"+fieldName+" value:"+fieldValue);
        return isexists;
	}
	
	@Override
	public void removeDesignation(int id) {
		Session session = getSessionFactory().getCurrentSession();
		Designation desg = (Designation) session.get(Designation.class, new Integer(id));
        if(null != desg){
            session.delete(desg);
        }
        logger.info("Designation deleted successfully, Designation details="+desg);
    }
	
	@Override
	public String getNextDesgCode() throws Exception {
		String prefix = "DSG";
		Integer code = 1;
		Session session = getSessionFactory().getCurrentSession();
		List list = session.createQuery("select max(dsgId)+1 from Designation").list();
		if(list!=null && list.get(0)!=null){
			code = (Integer) list.get(0);
		}
		return CommonUtil.generateNextCode(prefix, code);
	}
	
	@Override
	public Long getCountOfDesignation(int id) throws Exception {
		Long count = 0L;
		Session session = getSessionFactory().getCurrentSession();
		List list = session.createQuery("select count(*) from Designation where dsgId="+id).list();
		if(list!=null && list.get(0)!=null){
			count = (Long) list.get(0);
		}
		return count;
	}
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkFieldRefInAnyWhere(Integer id){
		List list = getTableRefForField("designation", "dsg_id");
		return checkFieldValueReference(id, list);
	}

	

}
