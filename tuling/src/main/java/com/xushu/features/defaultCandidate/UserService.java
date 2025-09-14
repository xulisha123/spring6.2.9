package com.xushu.features.defaultCandidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

	@Autowired
	private XushuService xushuService1;

	public XushuService getXushuService() {
		return xushuService1;
	}
}
