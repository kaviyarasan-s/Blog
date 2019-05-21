package com.chainsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.chainsys.model.User;
import com.chainsys.util.ConnectionUtil;

public class AuthetincationDAO {

	public int addUser(User userDetail) throws Exception {
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "INSERT INTO blog_registration(user_id,name,email,password,img_url) VALUES(student_id_seq.nextval,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userDetail.getName());
			preparedStatement.setString(2, userDetail.getEmail());
			preparedStatement.setString(3, userDetail.getPassword());
			preparedStatement.setString(4, userDetail.getUrl());
			int count = preparedStatement.executeUpdate();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	public static boolean success(String username, String password) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		Boolean isValue = false;
		String sql = "select user_id from login where email=? AND password=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (rs.getInt("user_id") > 0) {
				isValue = true;
			}
		}
		return isValue;
	}
}
