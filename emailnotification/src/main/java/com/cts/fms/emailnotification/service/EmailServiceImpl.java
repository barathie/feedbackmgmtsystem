package com.cts.fms.emailnotification.service;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cts.fms.emailnotification.model.EmailMessage;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${gmail.username}")
	private String username;

	@Value("${gmail.password}")
	private String password;

	@Override
	public void senEmail(EmailMessage emailMessage) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailMessage.getToAddress());
		message.setSubject(emailMessage.getSubject());
		message.setText(emailMessage.getMessageBody());
		javaMailSender.send(message);
	}

	@Override
	public void senEmailWithAttachement(EmailMessage message, MultipartFile file)
			throws IllegalStateException, IOException, MessagingException { 
		MimeMessage mimemessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimemessage, true);

		helper.setFrom(new InternetAddress(username, false));
		helper.setTo(message.getToAddress());
		helper.setSubject(message.getSubject());
		helper.setText(message.getMessageBody());

		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
		file.transferTo(convFile);

		FileSystemResource fileSystemResource = new FileSystemResource(convFile);
		helper.addAttachment(file.getOriginalFilename(), fileSystemResource);

		javaMailSender.send(mimemessage);

	}
}
