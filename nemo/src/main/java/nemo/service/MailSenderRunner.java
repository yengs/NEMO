package nemo.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import nemo.controller.RestMemberApiController;

@Component
 @RequiredArgsConstructor
public class MailSenderRunner implements ApplicationRunner {
	private final JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from;

	public String run(ApplicationArguments args, String memberEmail) throws Exception {
		
		
		RestMemberApiController code = new RestMemberApiController();
		String joinCode =code.getJoinCode();
		
		MimeMessage m = mailSender.createMimeMessage();
		MimeMessageHelper h = new MimeMessageHelper(m, "UTF-8");
		h.setFrom(from, "내모");
		h.setTo(memberEmail);
		h.setSubject("내모 인증번호");
		h.setText("인증 번호 :" + joinCode + "입니다");
		mailSender.send(m);
		System.out.println("bbbbbbbbb:" + joinCode);
		return joinCode;

	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub

	}


}