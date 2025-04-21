package com.example.action;

import com.example.dao.PostDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.example.model.Post;
import com.example.util.DBConnection;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeAction extends ActionSupport implements SessionAware {
    private static final long serialVersionUID = 1L;
    private List<Post> posts;
    private Map<String, Object> session;
    private int id; // ID bài viết cần xóa

    public String execute() {
        posts = new ArrayList<>();
        PostDAO postDAO = new PostDAO();
        try {
            posts = postDAO.getAllPosts();
            session.put("posts", posts);
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public String deletePost() {
        PostDAO postDAO = new PostDAO();
        try {
            boolean success = postDAO.deletePost(id);
            if (success) {
                session.put("message", "Xóa bài viết thành công!");
            } else {
                session.put("error", "Không tìm thấy bài viết để xóa!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.put("error", "Lỗi khi xóa bài viết!");
        }
        loadPosts();
        return SUCCESS;
    }

    private void loadPosts() {
        PostDAO postDAO = new PostDAO();
        try {
            List<Post> updatedPosts = postDAO.getAllPosts();
            session.put("posts", updatedPosts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getter & Setter
    public List<Post> getPosts() {
        return posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
