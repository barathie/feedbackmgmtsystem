package com.cts.fms.eventmgmt.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.fms.eventmgmt.entity.JwtResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	RestTemplate restTemplate;
	
	public JwtResponse getUserDetails(String userId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		JwtResponse jwtResponse = restTemplate.exchange("http://localhost:8080/api/auth/user/{id}",
				HttpMethod.GET, entity, JwtResponse.class,userId).getBody();

		return jwtResponse;		
	}

}
