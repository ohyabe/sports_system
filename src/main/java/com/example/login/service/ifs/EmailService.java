//package com.example.login.service.ifs;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender emailSender;
//
//    public void sendSimpleMessage(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("Testsport9527@gmail.com"); // 設置發件人地址
//        message.setTo(to); // 設置收件人地址
//        message.setSubject(subject); // 設置郵件主題
//        message.setText(text); // 設置郵件內容
//        emailSender.send(message); // 發送郵件
//    }
//}