package com.cts.fms.emailnotification.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cts.fms.emailnotification.exception.ApplicationException;
import com.cts.fms.emailnotification.model.EmailMessage;
import com.cts.fms.emailnotification.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/notification")
public class EmailController {
	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailMessage emailMessage) {
		try {
			emailService.senEmail(emailMessage);
		} catch (AddressException excp) {
			logger.error("Email address exception: {}", excp.getMessage());
			throw new ApplicationException(excp.getMessage());
		} catch (MessagingException excp) {
			logger.error("Email message exception : {}", excp.getMessage());
			throw new ApplicationException(excp.getMessage());
		} catch (IOException excp) {
			logger.error("I/O exception: {}", excp.getMessage());
			throw new ApplicationException(excp.getMessage());
		} catch (Exception excp) {
			logger.error("Exception: {}", excp.getMessage());
			throw new ApplicationException(excp.getMessage());
		}
		return ResponseEntity.ok("Email sent successfully " + emailMessage.getToAddress());
	}
	
	@PostMapping("/sendEmailWithAttachment")
	public ResponseEntity<?> sendEmailWithAttachment(@RequestParam("file") MultipartFile file,String inputmessage) {
		EmailMessage emailMessage = null;
		try {
			emailMessage = new ObjectMapper().readValue(inputmessage, EmailMessage.class);
        }  catch (JsonMappingException excp) {
			logger.error("Email json exception : {}", excp.getMessage());
			throw new ApplicationException(excp.getMessage());
		} catch (JsonProcessingException excp) {
			logger.error("Email json exception: {}", excp.getMessage());
			throw new ApplicationException(excp.getMessage());
		} 
		
		try {
			emailService.senEmailWithAttachement(emailMessage, file);
		} catch (AddressException excp) {
			logger.error("Email address exception: {}", excp.getMessage());
			throw new ApplicationException(excp.getMessage());
		} catch (MessagingException excp) {
			logger.error("Email message exception : {}", excp.getMessage());
			throw new ApplicationException(excp.getMessage());
		} catch (IOException excp) {
			logger.error("I/O exception: {}", excp.getMessage());
			throw new ApplicationException(excp.getMessage());
		} catch (Exception excp) {
			logger.error("Exception: {}", excp.getMessage());
			throw new ApplicationException(excp.getMessage());
		}
		return ResponseEntity.ok("Email sent successfully " + emailMessage.getToAddress());
	}	
}
