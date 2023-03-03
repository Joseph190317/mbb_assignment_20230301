package com.example.myapp.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.myapp.entity.AccountInfo;

public class DBAccUtils{
	
	public static boolean insert_account_info(Connection connection, AccountInfo acc_info) throws Exception {
		boolean isSuccess = false;
		int index = 1;
		String sqlStmt = "INSERT INTO AccountInfo (userName, passWord, name, age, email, phoneNo, updated_date, created_date) "
				+ "VALUES (?, ?, ?, ?, ?, ?, getdate(), getdate()); ";
		try (PreparedStatement ps = connection.prepareStatement(sqlStmt)) {
			ps.setString(index++, acc_info.getUserName());
			ps.setString(index++, EncryptionUtils.sha256(acc_info.getUserName()+ acc_info.getPassWord()));
			ps.setString(index++, acc_info.getName());
			ps.setInt(index++, acc_info.getAge());
			ps.setString(index++, acc_info.getEmail());
			ps.setString(index++, acc_info.getPhoneNo());
			isSuccess = ps.executeUpdate() > 0;
		}
		return isSuccess;
	}
	
	public static boolean update_account_info(Connection connection, AccountInfo acc_info) throws Exception {
		boolean isSuccess = false;
		int index = 1;
		StringBuilder sqlStmt = new StringBuilder();
		sqlStmt.append("UPDATE AccountInfo SET "
				+ "email = ?, "
				+ "phoneNo = ?, "
				+ "name = ?, "
				+ "age = ?, "
				+ "addressLine1 = ?, "
				+ "addressLine2 = ?, "
				+ "city = ?, "
				+ "state = ?, "
				+ "zipCode = ?, "
				+ "country = ?, "
				+ "updated_date = getdate() "
				+ "WHERE userName = ? AND passWord = ?; ");
		try (PreparedStatement ps = connection.prepareStatement(sqlStmt.toString())) {
			ps.setString(index++, acc_info.getEmail());
			ps.setString(index++, acc_info.getPhoneNo());
			ps.setString(index++, acc_info.getName());
			ps.setInt(index++, acc_info.getAge());
			ps.setString(index++, acc_info.getAddressLine1());
			ps.setString(index++, acc_info.getAddressLine2());
			ps.setString(index++, acc_info.getCity());
			ps.setString(index++, acc_info.getState());
			ps.setInt(index++, acc_info.getZipCode());
			ps.setString(index++, acc_info.getCountry());
			ps.setString(index++, acc_info.getUserName());
			ps.setString(index++, EncryptionUtils.sha256(acc_info.getUserName()+ acc_info.getPassWord()));
			isSuccess = ps.executeUpdate() > 0;
		}
		return isSuccess;
	}
	
	public static String get_account_phoneno(Connection connection, AccountInfo acc_info) throws Exception {
		String reponse = "";
		int index = 1;
		String sqlStmt = "SELECT phoneNo FROM AccountInfo "
				+ "WHERE userName = ? AND passWord = ?; ";
		try (PreparedStatement ps = connection.prepareStatement(sqlStmt)) {
			ps.setString(index++, acc_info.getUserName());
			ps.setString(index++, EncryptionUtils.sha256(acc_info.getUserName()+ acc_info.getPassWord()));
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					reponse = rs.getString("phoneNo");
				}
			}
		}
		return reponse;
	}
	
}