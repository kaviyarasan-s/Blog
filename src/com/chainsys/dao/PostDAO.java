package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.chainsys.model.Post;
import com.chainsys.util.ConnectionUtil;

public class PostDAO {

	public int newPost(Post post) throws Exception {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "INSERT INTO blog_post(post_id,title,content,img_url,catogery_id) VALUES(blog_postid_seq.nextval,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, post.getTitle());
			preparedStatement.setString(2, post.getContent());
			preparedStatement.setString(3, post.getUrl());
			preparedStatement.setInt(4, post.getCatogery().getId());
			int count = preparedStatement.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
}
