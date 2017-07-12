package com.lexian.manager.plate.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexian.manager.order.bean.Orders;
import com.lexian.manager.plate.bean.Special;
import com.lexian.manager.plate.dao.SpecialDao;
import com.lexian.manager.plate.service.SpecialService;
import com.lexian.utils.Constant;
import com.lexian.web.Page;
import com.lexian.web.ResultHelper;

@Service
public class SpecialServiceImpl implements SpecialService{

	@Autowired
	private SpecialDao specialDao;
	
	public SpecialDao getSpecialDao() {
		return specialDao;
	}

	public void setSpecialDao(SpecialDao specialDao) {
		this.specialDao = specialDao;
	}

	@Override
	public ResultHelper getSpecial(Integer pageNo) {
		
		Page page = new Page();

		if (pageNo != null) {
			page.setPageNo(pageNo);
		}
		page.setTotalSize(specialDao.getCountSpecial());
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		List<Special> orderssWithStore = specialDao.getSpecial(params);
		page.setData(orderssWithStore);

		ResultHelper result = new ResultHelper(Constant.code_success, page);

		return result;
	}

	@Override
	public ResultHelper updateSpecial(int id, String name) {
		specialDao.updateSpecial(id, name);
		 return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper deleteSpecial(int id) {
		specialDao.deleteSpecial(id);
		 return new ResultHelper(Constant.code_success);
	}

	@Override
	public ResultHelper addSpecial(String name) {
		specialDao.addSpecial(name);
		return new ResultHelper(Constant.code_success);
	}

}
