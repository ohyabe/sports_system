package com.example.login.controller;

// 引入必要的類別和包
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.dto.ChangePasswordRequest;
import com.example.login.dto.LoginRequest;
import com.example.login.dto.UserResponse;
import com.example.login.entity.Admin;
import com.example.login.entity.PasswordResetToken;
import com.example.login.entity.Student;
import com.example.login.entity.Teacher;
import com.example.login.repository.AdminRepository;
import com.example.login.repository.PasswordResetTokenDao;
import com.example.login.repository.StudentRepository;
import com.example.login.repository.TeacherRepository;

import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

// 允許跨域請求
@CrossOrigin
// 將此類標記為 REST 控制器
@RestController
public class LoginController {

    // 設置日誌記錄器
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    // 自動注入所需的 DAO 和服務
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordResetTokenDao tokenDao;

    @Autowired
    private JavaMailSender mailSender;

    // 處理登入請求
    @PostMapping("/sports_system/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        String role = loginRequest.getRole();

        if (userName == null || userName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is required");
        }

        if (password == null || password.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password is required");
        }

        if (role == null || role.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role is required");
        }

        if ("admin".equalsIgnoreCase(role)) {
            Optional<Admin> adminOpt = adminRepository.findByAdminId(userName);
            if (adminOpt.isPresent() && adminOpt.get().getAdminPassword().equals(password)) {
                Admin admin = adminOpt.get();
                UserResponse userResponse = new UserResponse(admin.getAdminName(), "管理員");
                return ResponseEntity.ok(userResponse);
            }
        } else if ("student".equalsIgnoreCase(role)) {
            Optional<Student> studentOpt = studentRepository.findByStudentId(userName);
            if (studentOpt.isPresent() && studentOpt.get().getStudentPassword().equals(password)) {
                Student student = studentOpt.get();
                UserResponse userResponse = new UserResponse(student.getStudentName(), "學生");
                return ResponseEntity.ok(userResponse);
            }
        } else if ("teacher".equalsIgnoreCase(role)) {
            Optional<Teacher> teacherOpt = teacherRepository.findByTeacherId(userName);
            if (teacherOpt.isPresent() && teacherOpt.get().getTeacherPassword().equals(password)) {
                Teacher teacher = teacherOpt.get();
                UserResponse userResponse = new UserResponse(teacher.getTeacherName(), "老師");
                return ResponseEntity.ok(userResponse);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username, password or role");
    }

    // 處理用戶驗證請求
    @PostMapping("/sports_system/verify_user")
    public ResponseEntity<?> verifyUser(@RequestBody ChangePasswordRequest changePasswordRequest) {
        String userId = changePasswordRequest.getUserId();
        String role = changePasswordRequest.getRole();
        String userEmail = changePasswordRequest.getUserEmail();

        // 驗證用戶 ID 是否有效
        if (userId == null || userId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID is required");
        }

        // 驗證電子郵件是否有效
        if (userEmail == null || userEmail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required");
        }

        // 驗證角色是否有效
        if (role == null || role.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Role is required");
        }

        boolean userVerified = false;

        // 根據不同角色進行用戶驗證
        if ("admin".equalsIgnoreCase(role)) {
            Optional<Admin> adminOpt = adminRepository.findByAdminId(userId);
            if (adminOpt.isPresent() && adminOpt.get().getAdminEmail().equals(userEmail)) {
                userVerified = true;
            }
        } else if ("student".equalsIgnoreCase(role)) {
            Optional<Student> studentOpt = studentRepository.findByStudentId(userId);
            if (studentOpt.isPresent() && studentOpt.get().getStudentEmail().equals(userEmail)) {
                userVerified = true;
            }
        } else if ("teacher".equalsIgnoreCase(role)) {
            Optional<Teacher> teacherOpt = teacherRepository.findByTeacherId(userId);
            if (teacherOpt.isPresent() && teacherOpt.get().getTeacherEmail().equals(userEmail)) {
                userVerified = true;
            }
        }

        // 如果用戶驗證成功，生成重置密碼的 token 並發送電子郵件
        if (userVerified) {
            String token = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now().plusHours(1); // 設置過期時間為1小時
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setUserId(userId);
            resetToken.setRole(role);
            resetToken.setExpiryDate(expiryDate);

            tokenDao.save(resetToken); // 存儲 token 到數據庫
            logger.info("Token generated and saved: {}", token);

            String resetLink = "http://localhost:5173/reset_password?token=" + token;

            sendEmail(userEmail, "重置密碼請求", "您已成功驗證您的身分。請點擊以下連結重設您的密碼： <a href=\"" + resetLink + "\">重置密碼</a>");
            return ResponseEntity.ok("User verified and email sent");
        }

        // 如果用戶驗證失敗，返回未找到的響應
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or email does not match");
    }

    // 處理重置密碼請求
    @Transactional
    @PostMapping("/sports_system/reset_password")
    public ResponseEntity<?> resetPassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        String token = changePasswordRequest.getToken();
        String newPassword = changePasswordRequest.getNewPassword();

        // 驗證 token 和新密碼是否有效
        if (token == null || token.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            logger.warn("Token or new password is missing");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token and new password are required");
        }

        // 驗證 token 是否存在並且未過期
        Optional<PasswordResetToken> tokenOpt = tokenDao.findByToken(token);

        if (!tokenOpt.isPresent() || tokenOpt.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            logger.warn("Invalid or expired token: {}", token);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
        }

        PasswordResetToken resetToken = tokenOpt.get();
        String userId = resetToken.getUserId();
        String role = resetToken.getRole();

        // 根據不同角色進行密碼重置
        if ("admin".equalsIgnoreCase(role)) {
            Optional<Admin> adminOpt = adminRepository.findByAdminId(userId);
            if (adminOpt.isPresent()) {
                Admin admin = adminOpt.get();
                if (admin.getAdminPassword().equals(newPassword)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("New password cannot be the same as the old password");
                }
                admin.setAdminPassword(newPassword);
                adminRepository.save(admin);
                tokenDao.deleteByToken(token); // 重置密碼後刪除 token
                logger.info("Password updated successfully for admin: {}", userId);
                return ResponseEntity.ok("Password updated successfully");
            }
        } else if ("student".equalsIgnoreCase(role)) {
            Optional<Student> studentOpt = studentRepository.findByStudentId(userId);
            if (studentOpt.isPresent()) {
                Student student = studentOpt.get();
                if (student.getStudentPassword().equals(newPassword)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("New password cannot be the same as the old password");
                }
                student.setStudentPassword(newPassword);
                studentRepository.save(student);
                tokenDao.deleteByToken(token); // 重置密碼後刪除 token
                logger.info("Password updated successfully for student: {}", userId);
                return ResponseEntity.ok("Password updated successfully");
            }
        } else if ("teacher".equalsIgnoreCase(role)) {
            Optional<Teacher> teacherOpt = teacherRepository.findByTeacherId(userId);
            if (teacherOpt.isPresent()) {
                Teacher teacher = teacherOpt.get();
                if (teacher.getTeacherPassword().equals(newPassword)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("New password cannot be the same as the old password");
                }
                teacher.setTeacherPassword(newPassword);
                teacherRepository.save(teacher);
                tokenDao.deleteByToken(token); // 重置密碼後刪除 token
                logger.info("Password updated successfully for teacher: {}", userId);
                return ResponseEntity.ok("Password updated successfully");
            }
        }

        // 如果用戶未找到或角色錯誤，返回未找到的響應
        logger.warn("User not found or role is incorrect for token: {}", token);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found or role is incorrect");
    }

    // 發送電子郵件的方法
    private void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("testsport9567@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}