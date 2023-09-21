package com.fssa.politifact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.model.User;
import com.fssa.politifact.util.ConnectionUtil;

public class UserDao {

	private UserDao() {

	}

	public static UserDao getObj() {

		return new UserDao();
	}

	public boolean userRegistration(User user) throws DaoException {
		
		final String insertQuery = "INSERT INTO Users (emailId, userName, password, mobileNo, age, occupation,gender) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {

				ps.setString(1, user.getEmailId());
				ps.setString(2, user.getUserName());
				ps.setString(3, user.getPassword());
				ps.setString(4, user.getMobileNo());
				ps.setInt(5, user.getAge());
				ps.setString(6, user.getOccupation());
				ps.setString(7, user.getGender());

				int rowAffected = ps.executeUpdate();
				return rowAffected > 0;
			}
		} catch (SQLException e) {

			throw new DaoException("Error while registering user: " + e.getMessage());
		}
	}
	
	

	public boolean deleteUser(String emailId) throws DaoException, SQLException {

		final String deleteQuery = "DELETE FROM users WHERE emailId = ?";
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			try (PreparedStatement psmt = connection.prepareStatement(deleteQuery)) {

				psmt.setString(1, emailId);

				int rowAffected = psmt.executeUpdate();

				return rowAffected > 0;

			}
		}
	}

	public boolean emailExists(String emailId) throws DaoException, SQLException {
		
		final String selectQuery = "SELECT COUNT(*) FROM Users WHERE emailId = ?";

		try (Connection connection = ConnectionUtil.getConnection()) {


			try (PreparedStatement psmt = connection.prepareStatement(selectQuery)) {

				psmt.setString(1, emailId);

				try (ResultSet rs = psmt.executeQuery()) {

					if (rs.next()) {

						return rs.getInt(1) > 0;

					}
				}
			} catch (SQLException e) {

				throw new DaoException("Error while checking email existence: " + e.getMessage());
			}
		}
		return false;
	}

	public boolean userLogin(String emailId, String password) throws DaoException, SQLException {

		final String selectQuery = "SELECT COUNT(*) FROM Users WHERE emailId = ? AND password = ?";
		
		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement psmt = connection.prepareStatement(selectQuery)) {

				psmt.setString(1, emailId);

				psmt.setString(2, password);

				try (ResultSet rs = psmt.executeQuery()) {

					if (rs.next()) {

						int count = rs.getInt(1);

						return count > 0;
					}
				}
			}
		}
		return false;
	}

	public List<User> getUserByEmail(String emailId) throws DaoException {
		
		ArrayList<User> UserList = new ArrayList<>();
		
		try (Connection connection = ConnectionUtil.getConnection()) {

			String selectQuery = "SELECT id, emailId, userName, password, mobileNo, age, occupation, gender FROM users WHERE emailId = ?";

			try (PreparedStatement psmt = connection.prepareStatement(selectQuery)) {
				
				psmt.setString(1, emailId);

				try (ResultSet rs = psmt.executeQuery()) {
					
					if (rs.next()) {
						User user = new User();
						user.setUserId(rs.getInt("id"));
						user.setEmailId(rs.getString("emailId"));
						user.setUserName(rs.getString("userName"));
						user.setPassword(rs.getString("password"));
						user.setMobileNo(rs.getString("mobileNo"));
						user.setAge(rs.getInt("age"));
						user.setOccupation(rs.getString("occupation"));
						user.setGender(rs.getString("gender"));

						UserList.add(user);
						
					}
					return UserList;
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	public boolean forgotPasswordInDB(String emailId, String newPassword) throws DaoException, SQLException {
		final String updateQuery = "UPDATE users SET password = ? WHERE email_Id = ?";
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			try (PreparedStatement psmt = connection.prepareStatement(updateQuery)) {
				
				psmt.setString(1, newPassword);

				psmt.setString(2, emailId);

				int rowAffected = psmt.executeUpdate();
				
				return rowAffected > 0;
			}
		}
	}

	public boolean updateUserProfile(User user) throws DaoException {

		final String updateQuery = "UPDATE users SET userName = ?, password = ?, mobileNo= ?, age=?, occupation=? , gender=? WHERE emailId = ?";

		try (Connection connection = ConnectionUtil.getConnection();

				PreparedStatement ps = connection.prepareStatement(updateQuery)) {

			ps.setString(7, user.getEmailId());
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getMobileNo());
			ps.setInt(4, user.getAge());
			ps.setString(5, user.getOccupation());
			ps.setString(6, user.getGender());

			int rowsUpdated = ps.executeUpdate();

			return rowsUpdated > 0;

		} catch (SQLException e) {
			throw new DaoException("Error updating user profile: " + e.getMessage());
		}
	}

}
