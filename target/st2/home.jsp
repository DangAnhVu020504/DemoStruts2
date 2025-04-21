<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.List, com.example.model.Post" %>
<html>
<head>
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #f4f4f4;
            padding: 10px;
        }
        .post {
            border-bottom: 1px solid #ddd;
            padding: 10px 0;
        }
        .logout-btn {
            background-color: red;
            color: white;
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 5px;
        }
        .logout-btn:hover {
            background-color: darkred;
        }
    </style>
</head>
<body>

<!-- Thanh tiêu đề -->
<div class="header">
    <h2>Welcome, <s:property value="#session.username"/>!</h2>
    <a href="logout.action" class="logout-btn">Đăng xuất</a>
</div>

<!-- Nút tạo bài viết mới -->
<p><a href="createPost.jsp">📢 Đăng bài</a></p>

    <h2>Danh sách bài viết</h2>

    <s:if test="%{#session.posts != null && #session.posts.size() > 0}">
		<s:iterator value="#session.posts">
			<div>
				<h3>
					<s:property value="title" />
				</h3>
				<p>
					<s:property value="body" />
				</p>
				<small>Đăng bởi: <s:property value="username" /> | Ngày: <s:property value="createdAt" /></small>
				<br>
				<a href="deletePost.action?id=<s:property value="id"/>"  onclick="return confirm('Bạn có chắc muốn xóa bài viết này không?')">🗑 Xóa</a>

			</div>
			<hr>
		</s:iterator>

	</s:if>

    <s:else>
        <p>Thêm 1 bài viết để xem thêm bài viết khác!!!</p>
    </s:else>

</body>
</html>
