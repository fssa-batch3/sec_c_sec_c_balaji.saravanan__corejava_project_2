package com.fssa.politifact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fssa.politifact.model.Leaders;
import com.fssa.politifact.model.Position;
import com.fssa.politifact.util.ConnectionUtil;
import com.fssa.politifact.validator.LeaderValidateError;
import com.fssa.politifact.validator.LeaderValidateException;

// this page do crud

public class LeaderDao {

	private static boolean insertLeader(Leaders leader, PreparedStatement pst) throws SQLException {

		pst.setString(1, leader.getName());
		pst.setString(2, leader.getPosition().toString());
		pst.setInt(3, leader.getPartyId());
		pst.setDouble(4, leader.getExperience());
		pst.setString(5, leader.getOccupation());
		pst.setInt(6, leader.getCounstuencyId());
		pst.setString(7, leader.getDescriptionOfBirth());
		pst.setString(8, leader.getDescriptionOfEducation());
		pst.setString(9, leader.getDescriptionOfPastWorkExperience());
		pst.setString(10, leader.getDescritionOfpolitics());
		pst.setString(11, leader.getDescriptionOffamily());
		pst.setString(12, leader.getDescriptionOfIncome());
		pst.setString(13, leader.getImageUrl());
		pst.setInt(14, leader.getAffidavitId());

		int rowsAffected = pst.executeUpdate();

		if (rowsAffected > 0) {

			ResultSet generatedKeys = pst.getGeneratedKeys();

			if (generatedKeys.next()) {

				int generatedId = generatedKeys.getInt(1);
         
				leader.setId(generatedId);
				System.out.println(leader.getId());
			}
			return true;
		} else {
			return false;
		}
	}

	private static boolean insertUpdate(Leaders leader, PreparedStatement pst) throws SQLException {

		pst.setString(1, leader.getName());
		pst.setString(2, leader.getPosition().toString());
		pst.setInt(3, leader.getPartyId());
		pst.setDouble(4, leader.getExperience());
		pst.setString(5, leader.getOccupation());
		pst.setInt(6, leader.getCounstuencyId());
		pst.setString(7, leader.getDescriptionOfBirth());
		pst.setString(8, leader.getDescriptionOfEducation());
		pst.setString(9, leader.getDescriptionOfPastWorkExperience());
		pst.setString(10, leader.getDescritionOfpolitics());
		pst.setString(11, leader.getDescriptionOffamily());
		pst.setString(12, leader.getDescriptionOfIncome());
		pst.setString(13, leader.getImageUrl());
		pst.setInt(14, leader.getAffidavitId());
		pst.setInt(15, 3);

		int row = pst.executeUpdate();

		return row > 0;
	}

	private static Leaders read(ResultSet rs) throws SQLException {
		Leaders leader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 1, "Birth Description",
				"Education Description", "Work Experience Description", "Politics Description", "Family Description",
				"Income Description", "https://www.example.com/image.jpg", 2);

		leader.setId(rs.getInt("id"));
		leader.setName(rs.getString("name"));
		leader.setPosition(rs.getString("position"));
		leader.setPartyId(rs.getInt("partyId"));
		leader.setExperience(rs.getDouble("experience"));
		leader.setOccupation(rs.getString("occupation"));
		leader.setCounstuencyId(rs.getInt("counstuencyId"));
		leader.setDescriptionOfBirth(rs.getString("descriptionOfBirth"));
		leader.setDescriptionOfEducation(rs.getString("descriptionOfEducation"));
		leader.setDescriptionOfPastWorkExperience(rs.getString("descriptionOfPastWorkExperience"));
		leader.setDescritionOfpolitics(rs.getString("descritionOfpolitics"));
		leader.setDescriptionOffamily(rs.getString("descriptionOfFamily"));
		leader.setDescriptionOfIncome(rs.getString("descriptionOfIncome"));
		leader.setImageUrl(rs.getString("imageUrl"));
		leader.setAffidavitId(rs.getInt("affidavitId"));

		return leader;
	}

	public static boolean addLeader(Leaders leader) throws SQLException, LeaderValidateException {

		final String query = "INSERT INTO balaji_saravanan_corejava_project.Leaders (name, position, partyId, experience, occupation, counstuencyId, descriptionOfBirth, descriptionOfEducation, descriptionOfPastWorkExperience, descritionOfpolitics, descriptionOffamily, descriptionOfIncome, imageUrl, affidavitId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = ConnectionUtil.getConnection();

				PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			return insertLeader(leader, pst);

		} catch (SQLException sqe) {
			System.out.println(sqe.getMessage());

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

	}

	public static boolean updateLeader(Leaders leader) throws SQLException, LeaderValidateException {

		final String query = "UPDATE Leaders SET name=?, position=?, partyId=?, experience=?, occupation=?, counstuencyId=?, descriptionOfBirth=?, descriptionOfEducation=?, descriptionOfPastWorkExperience=?, descritionOfpolitics=?, descriptionOffamily=?, descriptionOfIncome=?, imageUrl=?, affidavitId=? WHERE id=?";

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			return insertUpdate(leader, pst);

		} catch (SQLException sqe) {
			System.out.println(sqe.getMessage());

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}
	}

	public static boolean deleteLeader(int leaderId) throws SQLException {

		final String query = "DELETE FROM leaders WHERE id=?";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, leaderId);

				int rowsDeleted = pst.executeUpdate();

				return rowsDeleted > 0;

			} catch (SQLException sqe) {

				System.out.println(sqe.getMessage());

				return false;
			}
		}
	}

	public static ArrayList<Leaders> readLeader() throws SQLException, LeaderValidateException {

		ArrayList<Leaders> leadersList = new ArrayList<>();

		final String query = "SELECT * FROM Leaders";

		try (Connection connection = ConnectionUtil.getConnection();

				Statement stmt = connection.createStatement()) {

			try (ResultSet rs = stmt.executeQuery(query)) {

				while (rs.next()) {

					Leaders leader = read(rs);

					leadersList.add(leader);
				}

				return leadersList;

			} catch (SQLException sqe) {
				System.out.println(sqe.getMessage());

				throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

			}
		} 
	}

//	public static ArrayList<Leaders> readLeaderWithConstituency() throws SQLException, LeaderValidateException {
//		ArrayList<Leaders> leadersList = new ArrayList<>();
//		final String query = "SELECT c.constituencyName, l.name as leader_name FROM leaders l JOIN Constituency c ON l.constituency_id = c.constituencyID WHERE l.constituency_id =2";
//
//		try (Connection connection = DataBaseConnection.getConnection();
//				
//				Statement stmt = connection.createStatement()) {
//
//			try (ResultSet rs = stmt.executeQuery(query)) {
//
//				while (rs.next()) {
//					
//					String constituencyName = rs.getString("constituencyName");
//					
//					String leaderName = rs.getString("leader_name");
//
//					Leaders leader = new Leaders(leaderName, null, 0, 0, leaderName, 0, leaderName, leaderName, leaderName, leaderName, leaderName, leaderName, leaderName, 0);
//					
//					leader.setName(leaderName);
//
//					 
//					Constituency constituency = new Constituency(0, leaderName, leaderName, 0, 0);
//					
//					constituency.setConstituencyName(constituencyName);
//    
//					
//					leadersList.add(leader);
//				}
//
//				return leadersList;
//
//			} catch (SQLException sqe) {
//				System.out.println(sqe.getMessage());
//				throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
//			}
//		}
//	}

}
