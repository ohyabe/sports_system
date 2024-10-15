package com.example.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.entity.ContactMessage;
import com.example.login.service.ifs.ContactService;

@CrossOrigin
@RestController
@RequestMapping("/api/email")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody ContactMessage contactMessage) {
        contactService.saveContactMessage(contactMessage); // 只保存資料
        contactService.sendConfirmationEmail(contactMessage); // 發送確認信
        return "郵件發送成功";
    }
}
