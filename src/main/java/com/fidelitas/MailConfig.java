//package com.fidelitas;
//
//import java.util.Properties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.DefaultResourceLoader;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//@Configuration
//public class MailConfig {
//    
//    private Properties getMailProperties(){
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//        return properties;
//    }      
//    
//    @Bean
//    public JavaMailSender javaMailSender(){
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setJavaMailProperties(getMailProperties());
//        mailSender.setUsername("mtenoriobaldi@gmail.com");
//        mailSender.setPassword("bqyq hbjp fzdi ult");
//        return mailSender;
//    }
//    
//    @Bean
//    public ResourceLoader resourceLoader(){
//        return new DefaultResourceLoader();
//    }
//}