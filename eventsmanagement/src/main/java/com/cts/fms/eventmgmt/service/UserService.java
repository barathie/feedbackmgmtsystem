package com.cts.fms.eventmgmt.service;

import com.cts.fms.eventmgmt.entity.JwtResponse;

public interface UserService {
	
	public JwtResponse getUserDetails(String userId);

}
