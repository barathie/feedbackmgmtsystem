package com.cts.fms.emailnotification.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.web.multipart.MultipartFile;

import com.cts.fms.emailnotification.model.EmailMessage;

public interface EmailService {
	
	public void senEmail(EmailMessage message)  throws AddressException, MessagingException,IOException;
	
	public void senEmailWithAttachement(EmailMessage message, MultipartFile file) throws AddressException, MessagingException, IOException;
}
