package com.fssa.politifact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.politifact.enums.ElectionTypes;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Election;
import com.fssa.politifact.util.ConnectionUtil;
import com.fssa.politifact.validator.LeaderValidateError;

public class ElectionDao {

	private ElectionDao() {
	}

	public static ElectionDao getObj() { 
		
		return new ElectionDao();
	}

	private static boolean insertElection(Election election, PreparedStatement pst) throws SQLException {

		pst.setInt(1, election.getElectionYear());
		
		pst.setString(2, election.getElectionType().toString());

		int rowsAffected = pst.executeUpdate();

		if (rowsAffected > 0) {

			ResultSet generatedKeys = pst.getGeneratedKeys();

			if (generatedKeys.next()) {

				int generatedId = generatedKeys.getInt(1);

				election.setId(generatedId);
			}

			return true;

		} else {

			return false;
		}
	}

	private static boolean insertUpdate(Election election, PreparedStatement pst, String electionName) throws SQLException {

		int electionTypeId= ConstituencyDao.findElectionTypeId(electionName);
		
		pst.setInt(1, election.getElectionYear());

		pst.setString(2, election.getElectionType().toString());

		pst.setInt(3, electionTypeId);

		int row = pst.executeUpdate();

		return row > 0;

	}

	public boolean addElection(Election election) throws SQLException, LeaderValidateException {
		final String query = "INSERT INTO Election (electionYear, electionType) VALUES (?, ?)";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

				return insertElection(election, pst);

			} catch (SQLException sqe) {

				throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
			}
		}
	}

	public boolean updateElection(Election election, String electionName) throws SQLException, LeaderValidateException {

		final String query = "UPDATE Election SET electionYear=?, electionType=? WHERE id=?";

		try (Connection connection = ConnectionUtil.getConnection();

				PreparedStatement pst = connection.prepareStatement(query)) {

			return insertUpdate(election, pst, electionName);

		} catch (SQLException sqe) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
		}
	}

	public boolean deleteElection(int electionId) throws SQLException, LeaderValidateException {

		final String query = "DELETE FROM election WHERE id=?";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, electionId);

				int rowsDeleted = pst.executeUpdate();

				return rowsDeleted > 0;

			} catch (SQLException sqe) {

				throw new LeaderValidateException(LeaderValidateError.INVALID_CONSTITUENCY_ID);
			}
		}
	}

	public List<Election> readAllElection() throws SQLException, LeaderValidateException {

		ArrayList<Election> electionList = new ArrayList<>();

		final String query = "SELECT * FROM Election";

		try (Connection connection = ConnectionUtil.getConnection();

				Statement stmt = connection.createStatement()) {

			try (ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {

					Election election = new Election(1, 2023, ElectionTypes.ASSEMBLY_ELECTION);

					election.setElectionYear(rs.getInt(2));
					election.setElectionType(rs.getString(3));

					electionList.add(election);
				} 

				return electionList;

			} catch (SQLException sqe) {

				throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
			}
		}
	}

}
