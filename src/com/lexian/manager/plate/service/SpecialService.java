package com.lexian.manager.plate.service;


import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

public interface SpecialService {
	
	public ResultHelper getSpecial(Page page);
	
	public ResultHelper updateSpecial(int id,String name);
	
	public ResultHelper deleteSpecial(int id);
	
	public ResultHelper addSpecial(String name);
}
