package com.ashoikIt.Insurance.utils;

import java.io.File;


import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String sub, String body, String to,File f) throws Exception {
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true);
			msgHelper.setSubject(sub);
			msgHelper.setText(body, true);
			msgHelper.setTo(to);
			msgHelper.addAttachment("Plan-Details",f);
			javaMailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
