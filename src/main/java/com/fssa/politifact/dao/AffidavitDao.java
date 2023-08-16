package com.fssa.politifact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Affidavit;
import com.fssa.politifact.util.ConnectionUtil;
import com.fssa.politifact.validator.LeaderValidateError;

/*
 * affidate model object hlep to store the affidate url
 * this is one to mani relation ship.
 * so this is separate model object.
 */

public class AffidavitDao {
	
	private AffidavitDao() {
		
	}
	
	public AffidavitDao getObj() {
		
		return new AffidavitDao();
	}


	/*
	 * inserAffidavit method is only perform the result set work
	 * its help to method code line decrees
	 * this help to add election.
	 */

	private static boolean inserAffidavit(Affidavit affidavit, PreparedStatement pst) throws SQLException {

		pst.setInt(1, affidavit.getElectionId());

		pst.setInt(2, affidavit.getElectionId());
		
		pst.setString(3, affidavit.getAffidateUrl());

		int rowsAffected = pst.executeUpdate();

		if (rowsAffected > 0) {

			ResultSet generatedKeys = pst.getGeneratedKeys();  // if we need to see what is generate id that time it s use

			if (generatedKeys.next()) {

				int generatedId = generatedKeys.getInt(1);

				affidavit.setId(generatedId);
			}

			return true;

		} else {

			return false;
		}
	}


	/*
	 * insertUpdate method is only perform the result set work .
	 * its help to method code line decrees.
	 * this help to update election
	 */

	private static boolean insertUpdate(Affidavit affidavit, PreparedStatement pst, int id) throws SQLException {


		pst.setInt(1, affidavit.getElectionId());

		pst.setInt(2, affidavit.getElectionId());
		
		pst.setString(3, affidavit.getAffidateUrl());
		
		pst.setInt(4, id);
		
		int row = pst.executeUpdate();

		return row > 0;

	}

	/*
	 * addAffidavit is performed connection and prepare statement and return this complete or not through boolean
	 * in case any exception happen this code throw the user defined exception
	 */

	public boolean addAffidavit(Affidavit affidavit) throws SQLException, DaoException {
		
		final String query = "INSERT INTO Affidavit (electionId, LeaderId, affidateUrl) VALUES (?, ?, ?)";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

				return inserAffidavit(affidavit, pst);

			} catch (SQLException sqe) {

				throw new DaoException(LeaderValidateError.INVALID_OBJECT);
			}
		}
	}

	/*
	 * updateAffidavit is performed connection and prepare statement and return this complete or not through boolean.
	 * and i will send what id i want delete that id given this method delete.
	 * in case any exception happen this code throw the user defined exception
	 */

	public boolean updateAffidavit(Affidavit affidavit, int id) throws SQLException, LeaderValidateException {

		final String query = "UPDATE Affidavit SET electionId=?, LeaderId=?, affidateUrl=? WHERE id=?";

		try (Connection connection = ConnectionUtil.getConnection();

			 PreparedStatement pst = connection.prepareStatement(query)) {

			return insertUpdate(affidavit, pst, id);

		} catch (SQLException sqe) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
		}
	}

	/*
	 * add deleteElection is perform connection and prepare statement and return this compleate or not through boolean.
	 * this delete the election table where id.
	 * in case any exception hapend this code thorw the user defind exception
	 */

	public boolean deleteElection(int id) throws SQLException, LeaderValidateException {

		final String query = "DELETE FROM Affidavit WHERE id=?";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				

				pst.setInt(1, id);

				int rowsDeleted = pst.executeUpdate();

				return rowsDeleted > 0;

			} catch (SQLException sqe) {

				throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
			}
		}
	}

}
