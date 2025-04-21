package com.example.dao;

import com.example.model.Post;
import com.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    public List<Post> getAllPosts() throws Exception {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT p.id, p.title, p.body, u.username, p.created_at FROM posts p JOIN users u ON p.user_id = u.id ORDER BY p.created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setBody(rs.getString("body"));
                post.setUsername(rs.getString("username"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                posts.add(post);
            }
        }
        return posts;
    }

    public boolean createPost(String title, String body, String username) throws Exception {
        String query = "INSERT INTO posts (title, body, user_id, status) VALUES (?, ?, (SELECT id FROM users WHERE username=?), 'published')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, body);
            stmt.setString(3, username);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deletePost(int id) throws Exception {
        String query = "DELETE FROM posts WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}