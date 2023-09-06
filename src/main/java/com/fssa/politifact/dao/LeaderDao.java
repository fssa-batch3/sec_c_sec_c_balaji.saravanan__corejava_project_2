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
import com.fssa.politifact.model.Party;
import com.fssa.politifact.util.ConnectionUtil;
import com.fssa.politifact.util.Logger;
import com.fssa.politifact.validator.LeaderValidateError;

// this page do CRUD

public class LeaderDao {

	static Logger logger = new Logger();

	private LeaderDao() {

	}

	public static LeaderDao getObj() {

		return new LeaderDao();
	}

	/*
	 * findConstituencyId method is only perform the search operation and give the
	 * id. this help to find the constituency id .
	 */

	public static int findConstituencyId(String constituencyName) throws DaoException {

		final String query = "SELECT constituencyID FROM Constituency WHERE constituencyName = ?";

		int constituencyId = 0;

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setString(1, constituencyName);

				try (ResultSet resultSet = pst.executeQuery()) {

					if (resultSet.next()) {

						constituencyId = resultSet.getInt("constituencyID");

					} else {

						throw new DaoException(LeaderValidateError.INVALID_CONSTITUENCY_NAME);
					}
				}

			}

		} catch (SQLException sqe) {

			throw new DaoException(LeaderValidateError.INVALID_OBJECT);
		}

		return constituencyId;
	}

	/*
	 * findPartyId method is only perform the search operation in the database and
	 * then give the id, it also this help to find the party id.
	 */

	public static int findPartyId(String partyName) throws DaoException {

		final String query = "SELECT partyId FROM Party WHERE partyName = ?";

		int partyId = 0;

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setString(1, partyName);

				try (ResultSet resultSet = pst.executeQuery()) {

					if (resultSet.next()) {

						partyId = resultSet.getInt(1);

					} else {

						throw new DaoException(LeaderValidateError.INVALID_PARTYNAME);
					}
				}

			}

		} catch (SQLException sqe) {
			System.out.println(sqe.getMessage());

			throw new DaoException(LeaderValidateError.INVALID_OBJECT);
		}

		return partyId;
	}

	/*
	 * findConstituencyName method is we give the id in database that id what value
	 * have that name return this method. it s help to print the value
	 * 
	 */

	public static String findConstituencyName(int constituencyId) throws DaoException {

		final String query = "SELECT constituencyName FROM Constituency WHERE constituencyID = ?";

		String constituencyName = "";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, constituencyId);

				try (ResultSet resultSet = pst.executeQuery()) {

					if (resultSet.next()) {

						constituencyName = resultSet.getString("constituencyName");

					} else {

						throw new DaoException(LeaderValidateError.INVALID_CONSTITUENCY_ID);
					}
				}

			}
		} catch (SQLException sqe) {

			throw new DaoException(LeaderValidateError.INVALID_OBJECT);
		}

		return constituencyName;
	}

	/*
	 * findPartyName method is we give the id in database that id what value have
	 * that name return this method. it s help to print the value
	 * 
	 */

	public static String findPartyName(int partyId) throws DaoException {

		final String query = "SELECT partyName FROM Party WHERE partyId = ?";

		String partyName = "";

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setInt(1, partyId);

			try (ResultSet resultSet = pst.executeQuery()) {

				if (resultSet.next()) {

					partyName = resultSet.getString("partyName");

				} else {

					throw new DaoException(LeaderValidateError.INVALID_PARTY_ID);
				}
			}

		} catch (SQLException sqe) {

			throw new DaoException(LeaderValidateError.INVALID_OBJECT);
		}

		return partyName;
	}

	/*
	 * findLeaderId method is only perform the search opretion and give the id. this
	 * help to update and delete method user given the name that name find the and
	 * then operation happening .
	 */

	/*
	 * insertLeader method is only perform the result set work its help to method
	 * code line decrees this help to add leader.
	 */

	private static boolean insertLeader(Leader leader, PreparedStatement pst)
			throws SQLException, DaoException {

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

		return rowsAffected > 0;

		
	}

	/*
	 * insertUpdate method is only perform the result set work its help to method
	 * code line decrees this help to update the leader.
	 */

	private static boolean insertUpdate(Leader leader, PreparedStatement pst, int id)
			throws SQLException, DaoException {

		int constituencyId = findConstituencyId(leader.getCounstuencyName());

		int partyId = findPartyId(leader.getPartyName());

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
		pst.setInt(14, id);

		int row = pst.executeUpdate();

		return row > 0;
	}

	/*
	 * read method is only perform the result set work its help to method code line
	 * Decrees this help to read the all value in the table.
	 */

	private static Leader read(ResultSet rs) throws SQLException, DaoException {

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

	/*
	 * addLeader method is add the leader all value in the database. this method
	 * call to insert leader that method do result set operation. this method only
	 * receive the object. any occur happen in this code this throw user define
	 * exception.
	 */

	public boolean addLeader(Leader leader) throws LeaderValidateException, DaoException {

		final String query = "INSERT INTO Leader (name, position, partyId, experience, occupation, counstuencyId, descriptionOfBirth, descriptionOfEducation, descriptionOfPastWorkExperience, descritionOfpolitics, descriptionOffamily, descriptionOfIncome, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = ConnectionUtil.getConnection();

				PreparedStatement pst = connection.prepareStatement(query)) {

			return insertLeader(leader, pst);

		} catch (SQLException sqe) {

			logger.info(sqe.getMessage());

			throw new DaoException(LeaderValidateError.INVALID_OBJECT);

		}

	}

	/*
	 * updateLeader method is update the leader all value in the database. this
	 * method call to insert update that method do result set operation. this method
	 * receive the object and also what name row update that attribute also receive.
	 * that name go to the method find leader id that method search name in database
	 * turn the id. any occur happen in this code this throw user define exception.
	 */

	public boolean updateLeader(Leader leader, int id) throws SQLException, DaoException, LeaderValidateException {

		final String query = "UPDATE Leader SET name=?, position=?, partyId=?, experience=?, occupation=?, counstuencyId=?, descriptionOfBirth=?, descriptionOfEducation=?, descriptionOfPastWorkExperience=?, descritionOfpolitics=?, descriptionOffamily=?, descriptionOfIncome=?, imageUrl=? WHERE id=?";

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			return insertUpdate(leader, pst, id);

		} catch (SQLException sqe) {
			System.err.println(sqe.getMessage());

			throw new DaoException(LeaderValidateError.INVALID_OBJECT);

		}
	}

	/*
	 * deleteLeader method is delete the leader all value in the database. this
	 * method receive the what name row delete that attribute receive. that name go
	 * to the method find leader id that method search name in database turn the id.
	 * any occur happen in this code this throw user define exception.
	 */

	public boolean deleteLeader(int id) throws DaoException, SQLException {

		final String query = "DELETE FROM Leader WHERE id=?";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, id);

				int rowsDeleted = pst.executeUpdate();

				return rowsDeleted > 0;

			} catch (SQLException sqe) {

				System.err.println(sqe.getMessage());

				throw new DaoException(LeaderValidateError.INVALID_OBJECT);
			}
		}
	}

	/*
	 * readLeader method is reade the allLeader all value in the database. any
	 * occure happend in this code this throw userdefine exception.
	 */

	public List<Leader> readLeader() throws SQLException, DaoException, LeaderValidateException {

		ArrayList<Leader> leadersList = new ArrayList<>();

		final String query = "SELECT * FROM Leader";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (Statement stmt = connection.createStatement()) {

				try (ResultSet rs = stmt.executeQuery(query)) {

					while (rs.next()) {

						Leader leader = read(rs);

						leadersList.add(leader);
					}

					return leadersList;

				} catch (SQLException sqe) {

					throw new DaoException(LeaderValidateError.INVALID_OBJECT);

				}
			}
		}
	}

	/*
	 * readLeader with the onstituency and also party method is reade the allleaer
	 * all value in the database. any occure happend in this code this throw
	 * userdefine exception.
	 */

	public List<String> readAllJoin() throws SQLException, DaoException {
		
		ArrayList<String> leadersAndConstituencies = new ArrayList<>();

		final String query = "SELECT Leader.name, Leader.position, Leader.experience,"
				+ "Leader.occupation, Constituency.constituencyName, Constituency.constituencyNumber, "
				+ "Party.partyName " + "FROM Leader "
				+ "INNER JOIN Constituency ON Leader.counstuencyId = Constituency.constituencyID "
				+ "INNER JOIN Party ON Leader.partyId = Party.partyId";

		try (Connection connection = ConnectionUtil.getConnection(); Statement stmt = connection.createStatement()) {

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

					Constituency constituency = new Constituency("", "", 1, 0);

					constituency.setConstituencyName(constituencyName);
					constituency.setConstituencyNumber(constituencyNumber);

					Party party = new Party("", "");

					String leaderAndConstituency = leader.toString() + " Constituency: " + constituency.toString();

					leadersAndConstituencies.add(leaderAndConstituency);
				}

				return leadersAndConstituencies;

			} catch (SQLException sqe) {

				throw new DaoException(LeaderValidateError.INVALID_OBJECT);
			}
		}
	}

	public List<Leader> readSpecificLeader(int id) throws SQLException, DaoException, LeaderValidateException {

		ArrayList<Leader> leadersList = new ArrayList<>();

		final String query = "SELECT * FROM Leader WHERE id=?";

		try (Connection connection = ConnectionUtil.getConnection();
				
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setInt(1, id); 

			try (ResultSet rs = pst.executeQuery()) {

				if (rs.next()) {
					
					Leader leader = read(rs);
					
					leadersList.add(leader);
				}

			} catch (SQLException sqe) {
				System.err.println(sqe.getMessage());
				throw new DaoException(LeaderValidateError.INVALID_OBJECT);
			}
		}

		return leadersList;
	}

}
