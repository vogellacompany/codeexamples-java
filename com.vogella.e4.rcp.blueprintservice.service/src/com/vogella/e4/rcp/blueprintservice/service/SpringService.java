package com.vogella.e4.rcp.blueprintservice.service;

import com.vogella.e4.rcp.blueprintservice.api.IService;

public class SpringService  implements IService{

	private String config;
	
	
	public void setConfig(String config) {
		this.config = config;
	}
	
	public String getConfig() {
		return config;
	}
	
}
