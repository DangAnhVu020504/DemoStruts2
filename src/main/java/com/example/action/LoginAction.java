package com.example.action;

import com.example.dao.UserDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.example.util.DBConnection;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public class LoginAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private Map<String, Object> session;

    public String execute() {
        UserDAO userDAO = new UserDAO();
        try {
            if (userDAO.authenticate(username, password)) {
                session.put("username", username);
                return SUCCESS;
            } else {
                addActionError("Tên đăng nhập hoặc mật khẩu không đúng.");
                return INPUT;
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Lỗi hệ thống!");
            return ERROR;
        }
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
