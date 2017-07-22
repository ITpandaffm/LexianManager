package com.lexian.manager.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lexian.manager.shop.bean.Citys;

public interface CitysDao {
	
	List<Citys> getCitiesByParentId(@Param("parentId")Integer parentId);
   
}
