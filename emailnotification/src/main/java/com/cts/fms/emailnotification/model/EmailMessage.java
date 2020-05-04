package com.cts.fms.emailnotification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessage {
	private String toAddress;
	private String subject;
	private String messageBody;	

}
