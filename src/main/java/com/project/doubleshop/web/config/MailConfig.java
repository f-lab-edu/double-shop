package com.project.doubleshop.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("211.202.241.90");

        // 이메일 전송에 사용되는 프로토콜: SMTP(Simple Mail Transfer Protocol)
        mailSender.setProtocol("smtp");

        // 사용되는 TCP 포트 번호: 25
        mailSender.setPort(25);

        return mailSender;
    }

}
