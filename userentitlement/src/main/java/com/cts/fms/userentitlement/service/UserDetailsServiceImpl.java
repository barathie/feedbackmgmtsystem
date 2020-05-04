package com.cts.fms.userentitlement.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.fms.userentitlement.entity.ApplicationUser;
import com.cts.fms.userentitlement.entity.ERole;
import com.cts.fms.userentitlement.entity.Role;
import com.cts.fms.userentitlement.payload.request.LoginRequest;
import com.cts.fms.userentitlement.payload.request.SignupRequest;
import com.cts.fms.userentitlement.payload.response.JwtResponse;
import com.cts.fms.userentitlement.repository.RoleRepository;
import com.cts.fms.userentitlement.repository.UserRepository;
import com.cts.fms.userentitlement.security.JwtUtil;

import springfox.documentation.schema.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtil jwtUtils;
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser appUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username" + username));
		return UserDetailsImpl.build(appUser);
	}
	
	public UserDetailsImpl loadUserByUserId(Long id) throws UsernameNotFoundException {
		ApplicationUser appUser = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with userid" + id));
		return UserDetailsImpl.build(appUser);
	}
	
	public void addRoleByUserId(Long id, String roleName) throws UsernameNotFoundException {
		ApplicationUser appUser = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with userid" + id));
		Set<Role> roles = appUser.getRoles();
		switch (roleName) {
		case "ROLE_ADMIN":
			Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(adminRole);

			break;
		case "ROLE_POC":
			Role modRole = roleRepository.findByName(ERole.ROLE_POC)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(modRole);

			break;
		case "ROLE_USER":
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);

			break;
		default:
			throw new RuntimeException("Error: Role is not found.");
		}
		
		appUser.setRoles(roles);
		userRepository.save(appUser);	
	}
	
	public void deleteRoleByUserId(Long id, String roleName) throws UsernameNotFoundException {
		ApplicationUser appUser = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with userid" + id));
		Set<Role> roles = appUser.getRoles();
		
		Set<Role> destRoles = roles.stream()
				.filter(role -> !role.getName().name().equals(roleName)).collect(Collectors.toSet());
	
		appUser.setRoles(destRoles);
		userRepository.save(appUser);	
	}
	
	public boolean isUsernameExists(String userName) {
		return userRepository.existsByUsername(userName);
	}
	
	public boolean isUserEmailExists(String emailId) {
		return userRepository.existsByEmail(emailId);
	}
	
	public void createUserAccount(SignupRequest signUpRequest) {
		// Create new user's account
		ApplicationUser user = new ApplicationUser(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null || strRoles.isEmpty()) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ROLE_ADMIN":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "ROLE_POC":
					Role modRole = roleRepository.findByName(ERole.ROLE_POC)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
	}
	
	public JwtResponse validateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles);
	}
}
