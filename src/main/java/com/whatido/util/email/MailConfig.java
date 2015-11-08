package com.whatido.util.email;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class MailConfig implements Serializable {

	private static final long serialVersionUID = -3040528135216055610L;

	public Email getMailConfig() throws EmailException{
		Email email = new HtmlEmail();
		
		Properties props = new Properties();
		try {
			props.load(getClass().getResourceAsStream("/mail.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		email.setHostName(props.getProperty("mail.server.host"));
		email.setSmtpPort(Integer.parseInt(props.getProperty("mail.server.port")));
		email.setAuthentication(props.getProperty("mail.username"), props.getProperty("mail.password"));
		email.setStartTLSEnabled(Boolean.parseBoolean(props.getProperty("mail.enable.tls")));
		email.setFrom(props.getProperty("mail.from"));
		
		return email;
	}
	
}
