package com.example.login.service.ifs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.login.entity.ContactMessage;
import com.example.login.repository.ContactMessageDao;

@Service
public class ContactService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ContactMessageDao repository;

    // 檢查並保存資料到資料庫，不發送郵件
    public void saveContactMessage(ContactMessage contactMessage) {
        validateContactMessage(contactMessage);
        repository.save(contactMessage); // 保存到資料庫
    }

    // 發送確認信
    public void sendConfirmationEmail(ContactMessage contactMessage) {
        SimpleMailMessage confirmationMessage = new SimpleMailMessage();
        confirmationMessage.setTo(contactMessage.getEmail());
        confirmationMessage.setSubject("聯絡我們信件回覆");
        confirmationMessage.setText("已收到您的回覆，請耐心等待1~3個工作日，我們會盡快與您聯繫。");

        mailSender.send(confirmationMessage);
    }

    // 驗證聯絡信息
    private void validateContactMessage(ContactMessage contactMessage) {
        if (!StringUtils.hasText(contactMessage.getFullName())) {
            throw new IllegalArgumentException("姓名不能為空");
        }
        if (!StringUtils.hasText(contactMessage.getPhone())) {
            throw new IllegalArgumentException("電話不能為空");
        }
        if (!StringUtils.hasText(contactMessage.getEmail())) {
            throw new IllegalArgumentException("電子郵件不能為空");
        }
        if (!StringUtils.hasText(contactMessage.getSubject())) {
            throw new IllegalArgumentException("主題不能為空");
        }
        if (!StringUtils.hasText(contactMessage.getMessage())) {
            throw new IllegalArgumentException("訊息不能為空");
        }
    }
}
