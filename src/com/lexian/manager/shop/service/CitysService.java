package com.lexian.manager.shop.service;

import org.springframework.stereotype.Service;

import com.lexian.web.ResultHelper;

public interface CitysService {

	ResultHelper getCities(Integer parentId);

}
