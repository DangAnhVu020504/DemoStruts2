package com.example.action;

import com.example.dao.PostDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.example.util.DBConnection;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public class CreatePostAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    private String title;
    private String body;
    private Map<String, Object> session;

    //logic chính khi người dùng submit form tạo bài viết
    public String execute() {
        if (session == null || session.get("username") == null) {
            return "login";
        }

        String username = (String) session.get("username");
        PostDAO postDAO = new PostDAO();
        try {
            boolean success = postDAO.createPost(title, body, username);
            if (success) {
                return "home";
            } else {
                addActionError("Lỗi khi đăng bài!");
                return "input";
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Lỗi hệ thống!");
            return "error";
        }
    }

    // Getter & Setter
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
