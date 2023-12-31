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
import com.fssa.politifact.model.Affidavit;
import com.fssa.politifact.model.Leader;
import com.fssa.politifact.util.ConnectionUtil;
import com.fssa.politifact.util.Logger;
import com.fssa.politifact.validator.LeaderValidateError;

/*
 * affidavit model object help to store the affidavit url
 * this is one to mani relationship.
 * so this is separate model object.
 */

public class AffidavitDao {

	Logger logger = new Logger();

	private AffidavitDao() {

	}

	public static AffidavitDao getObj() {

		return new AffidavitDao();
	}

	/*
	 * insertAffidavit method is only perform the result set work its help to method
	 * code line decrees this help to add election.
	 */

	private static boolean insertAffidavit(Affidavit affidavit, PreparedStatement pst) throws SQLException {

		pst.setInt(1, affidavit.getElectionId());

		pst.setInt(2, affidavit.getLeaderId());

		pst.setString(3, affidavit.getAffidateUrl());

		int rowsAffected = pst.executeUpdate();

		if (rowsAffected > 0) {

			return true;

		} else {

			return false;
		}
	}

	/*
	 * insertUpdate method is only perform the result set work . its help to method
	 * code line decrees. this help to update election
	 */

	private static boolean insertUpdate(Affidavit affidavit, PreparedStatement pst, int id) throws SQLException {

		pst.setInt(1, affidavit.getElectionId()); 

		pst.setInt(2, affidavit.getLeaderId());

		pst.setString(3, affidavit.getAffidateUrl());

		pst.setInt(4, id);

		int row = pst.executeUpdate();

		return row > 0;

	}

	/*
	 * addAffidavit is performed connection and prepare statement and return this
	 * complete or not through boolean in case any exception happen this code throw
	 * the user defined exception
	 */

	public boolean addAffidavit(Affidavit affidavit) throws SQLException, DaoException {

		final String query = "INSERT INTO Affidavit (electionId, LeaderId, affidateUrl) VALUES (?, ?, ?)";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

				return insertAffidavit(affidavit, pst);

			} catch (SQLException sqe) {

				throw new DaoException("while insert the affidavit error" + sqe.getMessage());

			}
		}
	}

	/*
	 * updateAffidavit is performed connection and prepare statement and return this
	 * complete or not through boolean. and I will send what id I want to delete
	 * that id given this method delete. in case any exception happen this code
	 * throw the user defined exception
	 */

	public boolean updateAffidavit(Affidavit affidavit, int id) throws DaoException {

		final String query = "UPDATE Affidavit SET electionId=?, LeaderId=?, affidateUrl=? WHERE id=?";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				return insertUpdate(affidavit, pst, id);

			}

		} catch (SQLException sqe) {

			throw new DaoException("While updating the uptade error" + sqe.getMessage());
		}
	}

	/*
	 * add deleteElection is performed connection and prepare statement and return
	 * this complete or not through boolean. this deletes the election table where
	 * id. in case any exception happen this code throw the user defined exception
	 */

	public boolean deleteAffidavit(int id) throws DaoException, SQLException {

		final String query = "DELETE FROM Affidavit WHERE id=?";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, id);

				int rowsDeleted = pst.executeUpdate();

				return rowsDeleted > 0;

			} catch (SQLException sqe) {

				throw new DaoException("Delete the affidavit error occure" + sqe.getMessage());
			}
		}
	}

	public List<Affidavit> readAllAffidavit() throws SQLException, DaoException {

		ArrayList<Affidavit> affidavitList = new ArrayList<>();

		final String query = "SELECT * FROM Affidavit ";

		try (Connection connection = ConnectionUtil.getConnection();

				Statement stmt = connection.createStatement()) {

			try (ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {

					Affidavit affidavit = new Affidavit(0, 0, "");
					affidavit.setId(rs.getInt(1));
					affidavit.setElectionId(rs.getInt(2));
					affidavit.setLeaderId(rs.getInt(3));
					affidavit.setAffidateUrl(rs.getString(4));

					affidavitList.add(affidavit);
				}

				return affidavitList;

			} catch (SQLException sqe) {

				throw new DaoException("Read all afffidavit error occure" + sqe.getMessage());
			}
		}
	}

	
	/**
	 * send a leader id get affidavit values
	 * @param id
	 * @return
	 * @throws DaoException
	 * @throws SQLException
	 */
	public List<Affidavit> readAllLeaderWithAffidavit(int id) throws DaoException, SQLException {

		List<Affidavit> leaderAffidavitList = new ArrayList<>();

		String sql = "SELECT L.name, L.id, L.position, L.experience, L.occupation, L.descriptionOfBirth, "
				+ "L.descriptionOfEducation, L.descriptionOfPastWorkExperience, L.descritionOfpolitics, "
				+ "L.descriptionOfFamily, L.descriptionOfIncome, L.imageUrl, A.affidateUrl, L.counstuencyId, L.partyId "
				+ "FROM Leader L " + "INNER JOIN Affidavit A ON L.id = A.leaderId " + "WHERE L.id = ?";

		
		
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);
			try (ResultSet rs = preparedStatement.executeQuery()) {

				if (rs.next()) {

					Leader leader = new Leader();
					String constituencyName = LeaderDao.findConstituencyName(rs.getInt("counstuencyId"));
					String partyName = LeaderDao.findPartyName(rs.getInt("partyId"));

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

					String affidavitUrl = rs.getString("affidateUrl");
					Affidavit affidavit = new Affidavit(0, 0, affidavitUrl);

					leaderAffidavitList.add(affidavit);

				}

				return leaderAffidavitList;

			} catch (SQLException e) {
				logger.info(e.getMessage());
				throw new DaoException("affidavit and leader table join dao error occure " + e.getMessage());
			}

		}

	}
 
	public List<Leader> readAllLeaderPartyId(int id, int electionId) throws DaoException, SQLException {

		List<Leader> leaderAffidavitList = new ArrayList<>();

		String sql = "SELECT "
				+ "L.name, L.id, L.position, L.experience, L.occupation, L.descriptionOfBirth, "
				+ "L.descriptionOfEducation, L.descriptionOfPastWorkExperience, L.descritionOfpolitics, "
				+ "L.descriptionOfFamily, L.descriptionOfIncome, L.imageUrl, A.affidateUrl, L.counstuencyId, L.partyId, L.verify_status, "
				+ "E.id, E.electionYear, E.electionType "
				+ "FROM Leader L "
				+ "INNER JOIN "
				+ "Affidavit A ON L.id = A.leaderId "
				+ "INNER JOIN "
				+ "Election E ON E.id = A.electionId "
				+ "WHERE "
				+ "L.partyId = ? AND E.id = ? AND L.verify_status='VERIFY'";
 
 
		try (Connection connection = ConnectionUtil.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, electionId);
			try (ResultSet rs = preparedStatement.executeQuery()) {

				while (rs.next()) {

					Leader leader = new Leader();
					String constituencyName = LeaderDao.findConstituencyName(rs.getInt("counstuencyId"));
					String partyName = LeaderDao.findPartyName(rs.getInt("partyId"));

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

					leaderAffidavitList.add(leader);

				}

				return leaderAffidavitList;

			} catch (SQLException e) {
				logger.info(e.getMessage());
				throw new DaoException("affidavit and leader table join dao error occure " + e.getMessage());
			}

		}

	}

	public List<Leader> readAllLeaderPartyId(int id, int electionId , int constituencyId) throws DaoException, SQLException {

		List<Leader> leaderAffidavitList = new ArrayList<>();

		String sql = "SELECT "
				+ "L.name, L.id, L.position, L.experience, L.occupation, L.descriptionOfBirth, "
				+ "L.descriptionOfEducation, L.descriptionOfPastWorkExperience, L.descritionOfpolitics, "
				+ "L.descriptionOfFamily, L.descriptionOfIncome, L.imageUrl, A.affidateUrl, L.counstuencyId, L.partyId, L.verify_status, "
				+ "E.id, E.electionYear, E.electionType "
				+ "FROM "
				+ "Leader L "
				+ "INNER JOIN "
				+ "Affidavit A ON L.id = A.leaderId "
				+ "INNER JOIN "
				+ "Election E ON E.id = A.electionId "
				+ "WHERE "
				+ "L.partyId = ? AND E.id = ? AND L.verify_status='VERIFY' AND L.counstuencyId = ?";
 
 
		try (Connection connection = ConnectionUtil.getConnection(); 
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, electionId);
			preparedStatement.setInt(3, constituencyId);
			try (ResultSet rs = preparedStatement.executeQuery()) {

				while (rs.next()) {

					Leader leader = new Leader();
					String constituencyName = LeaderDao.findConstituencyName(rs.getInt("counstuencyId"));
					String partyName = LeaderDao.findPartyName(rs.getInt("partyId"));

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

					leaderAffidavitList.add(leader);

				}

				return leaderAffidavitList;

			} catch (SQLException e) {
				
				logger.info(e.getMessage());
				
				throw new DaoException("affidavit and leader table join dao error occure " + e.getMessage());
			}

		}

	}
	public List<Leader> readAllLeaderInelection(int electionId) throws DaoException, SQLException {

		List<Leader> leaderAffidavitList = new ArrayList<>();

		String sql = "SELECT "
				+ "L.name, L.id, L.position, L.experience, L.occupation, L.descriptionOfBirth, "
				+ "L.descriptionOfEducation, L.descriptionOfPastWorkExperience, L.descritionOfpolitics, "
				+ "L.descriptionOfFamily, L.descriptionOfIncome, L.imageUrl, A.affidateUrl, L.counstuencyId, L.partyId, L.verify_status, "
				+ "E.id, E.electionYear, E.electionType "
				+ "FROM "
				+ "Leader L "
				+ "INNER JOIN "
				+ "Affidavit A ON L.id = A.leaderId "
				+ "INNER JOIN "
				+ "Election E ON E.id = A.electionId "
				+ "WHERE "
				+ "E.id = ? AND L.verify_status='VERIFY'";
 

		try (Connection connection = ConnectionUtil.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			preparedStatement.setInt(1, electionId);

			try (ResultSet rs = preparedStatement.executeQuery()) {

				while (rs.next()) {

					Leader leader = new Leader();
					String constituencyName = LeaderDao.findConstituencyName(rs.getInt("counstuencyId"));
					String partyName = LeaderDao.findPartyName(rs.getInt("partyId"));

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

					leaderAffidavitList.add(leader);

				}

				return leaderAffidavitList;

			} catch (SQLException e) {
				
				logger.info(e.getMessage());
				
				throw new DaoException("affidavit and leader table join dao error occure " + e.getMessage());
			}

		}

	}
	
	
	
	public List<Leader> readAllLeaderSearch() throws DaoException, SQLException {

		List<Leader> leaderAffidavitList = new ArrayList<>();

		String sql = "SELECT L.name, L.id, L.position, L.experience, L.occupation, L.descriptionOfBirth, "
				+ "L.descriptionOfEducation, L.descriptionOfPastWorkExperience, L.descritionOfpolitics, "
				+ "L.descriptionOfFamily, L.descriptionOfIncome, L.imageUrl, A.affidateUrl, L.counstuencyId, L.partyId "
				+ "FROM Leader L " + "INNER JOIN Affidavit A ON L.id = A.leaderId ";

		
		
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			try (ResultSet rs = preparedStatement.executeQuery()) {

				while (rs.next()) {

					Leader leader = new Leader();
					String constituencyName = LeaderDao.findConstituencyName(rs.getInt("counstuencyId"));
					String partyName = LeaderDao.findPartyName(rs.getInt("partyId"));

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

					String affidavitUrl = rs.getString("affidateUrl");
					Affidavit affidavit = new Affidavit(0, 0, affidavitUrl);

					leaderAffidavitList.add(leader);

				}

				return leaderAffidavitList;

			} catch (SQLException e) {
				logger.info(e.getMessage());
				throw new DaoException("affidavit and leader table join dao error occure " + e.getMessage());
			}

		}

	}
	
	
	
	
	
	
	public List<Leader> readAllLeaderbyconstituency(int constituencyId, int electionId) throws DaoException, SQLException {

		List<Leader> leaderAffidavitList = new ArrayList<>();

		String sql = "SELECT "
				+ "L.name, L.id, L.position, L.experience, L.occupation, L.descriptionOfBirth, "
				+ "L.descriptionOfEducation, L.descriptionOfPastWorkExperience, L.descritionOfpolitics, "
				+ "L.descriptionOfFamily, L.descriptionOfIncome, L.imageUrl, A.affidateUrl, L.counstuencyId, L.partyId, L.verify_status, "
				+ "E.id, E.electionYear, E.electionType "
				+ "FROM "
				+ "Leader L "
				+ "INNER JOIN "
				+ "Affidavit A ON L.id = A.leaderId "
				+ "INNER JOIN "
				+ "Election E ON E.id = A.electionId "
				+ "WHERE "
				+ "L.counstuencyId = ? AND E.id = ? AND L.verify_status='VERIFY'";
 
 
		try (Connection connection = ConnectionUtil.getConnection(); 
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, constituencyId);
			preparedStatement.setInt(2, electionId);
			try (ResultSet rs = preparedStatement.executeQuery()) {

				while (rs.next()) {

					Leader leader = new Leader();
					String constituencyName = LeaderDao.findConstituencyName(rs.getInt("counstuencyId"));
					String partyName = LeaderDao.findPartyName(rs.getInt("partyId"));

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

					leaderAffidavitList.add(leader);

				}

				return leaderAffidavitList;

			} catch (SQLException e) {
				logger.info(e.getMessage());
				throw new DaoException("affidavit and leader table join dao error occure " + e.getMessage());
			}

		}

	}
	
}
