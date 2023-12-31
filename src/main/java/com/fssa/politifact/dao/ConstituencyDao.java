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
import com.fssa.politifact.util.ConnectionUtil;
import com.fssa.politifact.util.Logger;
import com.fssa.politifact.validator.LeaderValidateError;

public class ConstituencyDao {

	private ConstituencyDao() {

	}

	public static ConstituencyDao getObj() { // private constructor
 
		return new ConstituencyDao();

	}

	Logger logger = new Logger();

	/*
	 * This insertConstituency method only prepare statement code only run hear.
	 * what reason for that the add leader method modeline I do want to decrees so
	 * that reason. this code add leader prepare statement executable code
	 */

	private boolean insertConstituency(Constituency constituency, PreparedStatement pst) throws SQLException, DaoException {

		

		pst.setString(1, constituency.getConstituencyName());
		pst.setString(2, constituency.getDistrictName());
		pst.setInt(3, constituency.getConstituencyNumber());
		pst.setInt(4, constituency.getElectionTypeName());

		int rowsAffected = pst.executeUpdate();

		if (rowsAffected > 0) {

			ResultSet generatedKeys = pst.getGeneratedKeys();

			if (generatedKeys.next()) {

				int generatedId = generatedKeys.getInt(1);

				constituency.setConstituencyID(generatedId);
			}

			return true;

		} else { 

			return false;
		}
	}

	/*
	 * in this method insert update method only prepare statement code here this
	 * only update the code return a boolean value
	 */
	private boolean insertUpdate(Constituency constituency, PreparedStatement pst, int id)
			throws SQLException, DaoException {


		pst.setString(1, constituency.getConstituencyName());
		pst.setString(2, constituency.getDistrictName());
		pst.setInt(3, constituency.getConstituencyNumber());
		pst.setInt(4, constituency.getElectionTypeName());
		pst.setInt(5, id);

		int row = pst.executeUpdate();

		return row > 0;

	}

	/*
	 * addConstituency add a new constituency in database this method hava a
	 * connection and also prepare statement this call a insert constituency that
	 * place run a prepare statement
	 */

	public boolean addConstituency(Constituency constituency) throws SQLException, LeaderValidateException, DaoException {

		final String query = "INSERT INTO Constituency (constituencyName, districtName, constituencyNumber, electionTypeId) VALUES (?, ?, ?, ?)";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

				return insertConstituency(constituency, pst); // invoke the value in to te insert constituency

			} catch (SQLException sqe) {

				logger.info(sqe.getMessage());

				throw new DaoException("constituency add dao error occure "+ sqe.getMessage());
			}
		}
	}

	/*
	 * update constituency perform update the values in database this also call a
	 * insert update that plays run a prepare Statement
	 */

	public boolean updateConstituency(Constituency constituency, int id)
			throws SQLException, LeaderValidateException, DaoException {

		final String query = "UPDATE Constituency SET constituencyName=?, districtName=?, constituencyNumber=?, electionTypeId=? WHERE constituencyID=?";

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			return insertUpdate(constituency, pst, id); // invoke the value in insert update methode

		} catch (SQLException sqe) {

			throw new DaoException("constituency update dao error occure "+ sqe.getMessage());
		}
	}

	/*
	 * delete-constituency method delete a constituency this performs given id
	 */

	public boolean deleteConstituency(int constituencyId) throws SQLException, DaoException {

		final String query = "DELETE FROM constituency WHERE constituencyID=?"; // query

		try (Connection connection = ConnectionUtil.getConnection()) { // connection

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, constituencyId);

				int rowsDeleted = pst.executeUpdate();

				return rowsDeleted > 0;

			} catch (SQLException sqe) {

				throw new DaoException("constituency delete  dao error occure "+ sqe.getMessage());
			}
		}
	}

	/*
	 * readAllConstituencies this method connect the database read the all
	 * constituency in the table and then the store a List and also return that
	 * return value go to the service layer.
	 */

	public List<Constituency> readAllConstituencies() throws SQLException, LeaderValidateException, DaoException {

		ArrayList<Constituency> constituenciesList = new ArrayList<>();

		final String query = "SELECT * FROM Constituency";

		try (Connection connection = ConnectionUtil.getConnection();

				Statement stmt = connection.createStatement()) {

			try (ResultSet rs = stmt.executeQuery(query)) {
				
				while (rs.next()) {

					Constituency constituency = new Constituency("constituency", "district", 1, 0);

					
					constituency.setConstituencyID(rs.getInt(1));
					constituency.setConstituencyName(rs.getString(2));
					constituency.setDistrictName(rs.getString(3));
					constituency.setConstituencyNumber(rs.getInt(4));
					constituency.setElectionTypeName(rs.getInt(5));

					constituenciesList.add(constituency);
				}

				return constituenciesList;

			} catch (SQLException sqe) {

				throw new DaoException("List of constituency dao error occure "+ sqe.getMessage());
			}
		}
	}

	/*
	 * find election id method I have given the name that name find the database return
	 * the that name row id this help to forenoon key find and fetch this table
	 */

	public static int findElectionTypeId(String electionName) throws DaoException {

		final String query = "SELECT id FROM Election WHERE electionType = ?";

		int electionId = 0;

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setString(1, electionName);

			try (ResultSet resultSet = pst.executeQuery()) {

				if (resultSet.next()) {

					electionId = resultSet.getInt("id");

				}
			}

		} catch (SQLException sqe) {

			throw new DaoException("find the constituency dao error occure "+ sqe.getMessage());
		}

		return electionId;
	}
	

	/*
	 * findElectionTypeName method i given the int id that give String election name
	 * went it want to i read the value that time i give id that search for table
	 * and then return the name this help to read the name throu the id
	 */

	public static String findElectionTypeName(int electionId) throws DaoException { 

		final String query = "SELECT electionType FROM Election WHERE id = ?";

		String electionName = "";

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setInt(1, electionId);

			try (ResultSet resultSet = pst.executeQuery()) {
 
				if (resultSet.next()) {

					electionName = resultSet.getString("electionType");

				}
			}

		} catch (SQLException sqe) {

			throw new DaoException("find the election dao error occure "+ sqe.getMessage());
		}

		return electionName;
	} 
}
