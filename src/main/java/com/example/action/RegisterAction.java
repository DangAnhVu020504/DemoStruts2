package com.example.action;

import com.example.dao.UserDAO;
import com.example.model.User;
import com.example.util.DBConnection;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterAction extends ActionSupport {
    private String username;
    private String password;
    private String confirmPassword;

    public String execute() {
        UserDAO userDAO = new UserDAO();
        try {
            if (userDAO.isUsernameExists(username)) {
                addActionError("Tên người dùng đã tồn tại!");
                return INPUT;
            }

            if (!password.equals(confirmPassword)) {
                addActionError("Mật khẩu xác nhận không khớp!");
                return INPUT;
            }

            User user = new User(username, password, "user");
            if (userDAO.registerUser(user)) {
                return SUCCESS;
            } else {
                addActionError("Đăng ký thất bại, vui lòng thử lại!");
                return INPUT;
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Lỗi hệ thống!");
            return ERROR;
        }
    }

    // Getters & Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
}