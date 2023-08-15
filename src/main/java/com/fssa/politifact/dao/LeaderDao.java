package com.fssa.politifact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Constituency;
import com.fssa.politifact.model.Leader;
import com.fssa.politifact.util.ConnectionUtil;
import com.fssa.politifact.validator.LeaderValidateError;

// this page do crud

public class LeaderDao {

	private LeaderDao() {

	}

	public static LeaderDao getObj() {

		return new LeaderDao();
	}

	public static int findConstituencyId(String constituencyName) {

		final String query = "SELECT constituencyID FROM Constituency WHERE constituencyName = ?";

		int constituencyId = 0;

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setString(1, constituencyName);

			try (ResultSet resultSet = pst.executeQuery()) {

				if (resultSet.next()) {

					constituencyId = resultSet.getInt("constituencyID");

				}
			}

		} catch (SQLException sqe) {

			sqe.printStackTrace();
		}

		return constituencyId;
	}

	public static int findPartyId(String partyName) {

		final String query = "SELECT partyId FROM Party WHERE partyName = ?";

		int partyId = 0;

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setString(1, partyName);

			try (ResultSet resultSet = pst.executeQuery()) {

				if (resultSet.next()) {

					partyId = resultSet.getInt("partyId");

				}
			}

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}

		return partyId;
	}

	public static String findConstituencyName(int constituencyId) {

		final String query = "SELECT constituencyName FROM Constituency WHERE constituencyID = ?";

		String constituencyName = "";

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setInt(1, constituencyId);

			try (ResultSet resultSet = pst.executeQuery()) {

				if (resultSet.next()) {

					constituencyName = resultSet.getString("constituencyName");

				}
			}

		} catch (SQLException sqe) {

			sqe.printStackTrace();
		}

		return constituencyName;
	}

	private static String findPartyName(int partyId) {

		final String query = "SELECT partyName FROM Party WHERE partyId = ?";

		String partyName = "";

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setInt(1, partyId);

			try (ResultSet resultSet = pst.executeQuery()) {

				if (resultSet.next()) {

					partyName = resultSet.getString("partyName");

				}
			}

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}

		return partyName;
	}

	private static int findLeaderId(String leaderName) {

		final String query = "SELECT id FROM Leader WHERE name = ?";

		int leaderId = 0;

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setString(1, leaderName);

			try (ResultSet resultSet = pst.executeQuery()) {

				if (resultSet.next()) {

					leaderId = resultSet.getInt("id");

				}
			}

		} catch (SQLException sqe) {

			sqe.printStackTrace();
		}

		return leaderId;
	}

	private static boolean insertLeader(Leader leader, PreparedStatement pst) throws SQLException {

		int partyId = findPartyId(leader.getPartyName());

		int constituencyId = findConstituencyId(leader.getCounstuencyName());

		pst.setString(1, leader.getName());
		pst.setString(2, leader.getPosition().toString());
		pst.setInt(3, partyId);
		pst.setDouble(4, leader.getExperience());
		pst.setString(5, leader.getOccupation());
		pst.setInt(6, constituencyId);
		pst.setString(7, leader.getDescriptionOfBirth());
		pst.setString(8, leader.getDescriptionOfEducation());
		pst.setString(9, leader.getDescriptionOfPastWorkExperience());
		pst.setString(10, leader.getDescritionOfpolitics());
		pst.setString(11, leader.getDescriptionOffamily());
		pst.setString(12, leader.getDescriptionOfIncome());
		pst.setString(13, leader.getImageUrl());

		int rowsAffected = pst.executeUpdate();

		if (rowsAffected > 0) {

			ResultSet generatedKeys = pst.getGeneratedKeys();

			if (generatedKeys.next()) {

				int generatedId = generatedKeys.getInt(1);

				leader.setId(generatedId);

			}

			return true;

		} else {

			return false;
		}
	}

	private static boolean insertUpdate(Leader leader, PreparedStatement pst, String name) throws SQLException {

		int constituencyId = findConstituencyId(leader.getCounstuencyName());

		int partyId = findPartyId(leader.getPartyName());

		int leaderId = findLeaderId(name);

		pst.setString(1, leader.getName());
		pst.setString(2, leader.getPosition().toString());
		pst.setInt(3, constituencyId);
		pst.setDouble(4, leader.getExperience());
		pst.setString(5, leader.getOccupation());
		pst.setInt(6, partyId);
		pst.setString(7, leader.getDescriptionOfBirth());
		pst.setString(8, leader.getDescriptionOfEducation());
		pst.setString(9, leader.getDescriptionOfPastWorkExperience());
		pst.setString(10, leader.getDescritionOfpolitics());
		pst.setString(11, leader.getDescriptionOffamily());
		pst.setString(12, leader.getDescriptionOfIncome());
		pst.setString(13, leader.getImageUrl());
		pst.setInt(14, leaderId);

		int row = pst.executeUpdate();

		return row > 0;
	}

	private static Leader read(ResultSet rs) throws SQLException {

		Leader leader = new Leader();

		String constituencyName = findConstituencyName(rs.getInt("counstuencyId"));
		String partyName = findPartyName(rs.getInt("partyId"));

		leader.setId(rs.getInt("id"));
		leader.setName(rs.getString("name"));
		leader.setPosition(rs.getString("position"));
		leader.setPartyName(partyName);
		leader.setExperience(rs.getDouble("experience"));
		leader.setOccupation(rs.getString("occupation"));
		leader.setCounstuencyName(constituencyName);
		leader.setDescriptionOfBirth(rs.getString("descriptionOfBirth"));
		leader.setDescriptionOfEducation(rs.getString("descriptionOfEducation"));
		leader.setDescriptionOfPastWorkExperience(rs.getString("descriptionOfPastWorkExperience"));
		leader.setDescritionOfpolitics(rs.getString("descritionOfpolitics"));
		leader.setDescriptionOffamily(rs.getString("descriptionOfFamily"));
		leader.setDescriptionOfIncome(rs.getString("descriptionOfIncome"));
		leader.setImageUrl(rs.getString("imageUrl"));

		return leader;
	}

	public boolean addLeader(Leader leader) throws LeaderValidateException {

		final String query = "INSERT INTO Leader (name, position, partyId, experience, occupation, counstuencyId, descriptionOfBirth, descriptionOfEducation, descriptionOfPastWorkExperience, descritionOfpolitics, descriptionOffamily, descriptionOfIncome, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = ConnectionUtil.getConnection();

				PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			return insertLeader(leader, pst);

		} catch (SQLException sqe) {

			System.out.println(sqe.getMessage());

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

	}

	public boolean updateLeader(Leader leader, String name) throws SQLException, DaoException {

		final String query = "UPDATE Leader SET name=?, position=?, partyId=?, experience=?, occupation=?, counstuencyId=?, descriptionOfBirth=?, descriptionOfEducation=?, descriptionOfPastWorkExperience=?, descritionOfpolitics=?, descriptionOffamily=?, descriptionOfIncome=?, imageUrl=? WHERE id=?";

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			return insertUpdate(leader, pst, name);

		} catch (SQLException sqe) {

			throw new DaoException(LeaderValidateError.INVALID_OBJECT);

		}
	}

	public boolean deleteLeader(String leaderName) throws SQLException, DaoException {

		final String query = "DELETE FROM leaders WHERE id=?";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				int leaderId = findLeaderId(leaderName);

				pst.setInt(1, leaderId);

				int rowsDeleted = pst.executeUpdate();

				return rowsDeleted > 0;

			} catch (SQLException sqe) {

				throw new DaoException(LeaderValidateError.INVALID_OBJECT);
			}
		}
	}

//	public List<Leader> readLeader() throws SQLException, DaoException {
//
//		ArrayList<Leader> leadersList = new ArrayList<>();
//
//		final String query = "SELECT * FROM Leader";
//
//		try (Connection connection = ConnectionUtil.getConnection();
//
//				Statement stmt = connection.createStatement()) {
//
//			try (ResultSet rs = stmt.executeQuery(query)) {
//
//				while (rs.next()) {
//
//					Leader leader = read(rs);
//
//					leadersList.add(leader);
//				}
//
//				return leadersList;
//
//			} catch (SQLException sqe) {
//
//				throw new DaoException(LeaderValidateError.INVALID_OBJECT);
//
//			}
//		}
//	}

	public List<String> readLeader() throws SQLException, DaoException {
	    ArrayList<String> leadersAndConstituencies = new ArrayList<>();

	    final String query = "SELECT Leader.name, Leader.position, Leader.experience, "
	            + "Leader.occupation, Constituency.constituencyName, Constituency.constituencyNumber, "
	            + "Party.partyName " + "FROM Leader "
	            + "INNER JOIN Constituency ON Leader.counstuencyId = Constituency.constituencyID "
	            + "INNER JOIN Party ON Leader.partyId = Party.partyId";

	    try (Connection connection = ConnectionUtil.getConnection();
	         Statement stmt = connection.createStatement()) {

	        try (ResultSet resultSet = stmt.executeQuery(query)) {
	            while (resultSet.next()) {
	                String name = resultSet.getString("name");
	                String position = resultSet.getString("position");
	                double experience = resultSet.getDouble("experience");
	                String occupation = resultSet.getString("occupation");
	                String constituencyName = resultSet.getString("constituencyName");
	                int constituencyNumber = resultSet.getInt("constituencyNumber");
	                String partyName = resultSet.getString("partyName");

	                Leader leader = new Leader();
	                leader.setName(name);
	                leader.setPosition(position);
	                leader.setExperience(experience);
	                leader.setOccupation(occupation);
	                leader.setCounstuencyName(constituencyName);
	                leader.setPartyName(partyName);

	                // Create a Constituency object and set its attributes
	                Constituency constituency = new Constituency("", "", 1, null);
	                constituency.setConstituencyName(constituencyName);
	                constituency.setConstituencyNumber(constituencyNumber);
	                // Set other attributes of the Constituency object as needed

	                // Generate a string combining leader and constituency details
	                String leaderAndConstituency = leader.toString() + " Constituency: " + constituency.toString();

	                leadersAndConstituencies.add(leaderAndConstituency);
	            }

	            return leadersAndConstituencies;
	        } catch (SQLException sqe) {
	            throw new DaoException(LeaderValidateError.INVALID_OBJECT);
	        }
	    }
	}

}
