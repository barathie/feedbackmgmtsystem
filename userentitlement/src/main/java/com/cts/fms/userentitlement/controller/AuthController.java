package com.cts.fms.userentitlement.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fms.userentitlement.entity.ApplicationUser;
import com.cts.fms.userentitlement.entity.ERole;
import com.cts.fms.userentitlement.entity.Role;
import com.cts.fms.userentitlement.payload.request.LoginRequest;
import com.cts.fms.userentitlement.payload.request.SignupRequest;
import com.cts.fms.userentitlement.payload.response.JwtResponse;
import com.cts.fms.userentitlement.payload.response.MessageResponse;
import com.cts.fms.userentitlement.repository.RoleRepository;
import com.cts.fms.userentitlement.repository.UserRepository;
import com.cts.fms.userentitlement.security.JwtUtil;
import com.cts.fms.userentitlement.service.UserDetailsImpl;
import com.cts.fms.userentitlement.service.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;	

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		JwtResponse jwtResponse = userDetailsService.validateUser(loginRequest);		
		return ResponseEntity.ok(jwtResponse);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<JwtResponse> getUserById(@PathVariable Long id) {
		
		UserDetailsImpl userDetails = userDetailsService.loadUserByUserId(id);	
		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setId(userDetails.getId());
		jwtResponse.setUsername(userDetails.getUsername());
		jwtResponse.setEmail(userDetails.getEmail());
		
		List<String> roles = userDetails.getAuthorities().stream().map(e-> e.getAuthority()).collect(Collectors.toList());
		jwtResponse.setRoles(roles);
		
		return ResponseEntity.ok(jwtResponse);				
	}
	
	@PutMapping("/user/{id}/{rolename}")
	public ResponseEntity<String> addRoleByUserId(@PathVariable Long id, @PathVariable String rolename) {
		
		userDetailsService.addRoleByUserId(id, rolename);	
				
		return ResponseEntity.ok("Added role successfully");				
	}
	
	@DeleteMapping("/user/{id}/{rolename}")
	public ResponseEntity<String> deleteRoleByUserId(@PathVariable Long id, @PathVariable String rolename) {
		
		userDetailsService.deleteRoleByUserId(id, rolename);
				
		return ResponseEntity.ok("Deleted role successfully");				
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userDetailsService.isUsernameExists(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userDetailsService.isUserEmailExists(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		userDetailsService.createUserAccount(signUpRequest);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}	
	
}