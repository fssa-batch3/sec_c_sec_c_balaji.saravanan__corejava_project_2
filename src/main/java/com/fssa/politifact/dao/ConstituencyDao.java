package com.fssa.politifact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fssa.politifact.model.Constituency;
import com.fssa.politifact.util.ConnectionUtil;
import com.fssa.politifact.validator.LeaderValidateError;
import com.fssa.politifact.validator.LeaderValidateException;

public class ConstituencyDao {

	private static boolean insertConstituency(Constituency Constituency, PreparedStatement pst) throws SQLException {

		pst.setString(1, Constituency.getConstituencyName());
		pst.setString(2, Constituency.getDistrictName());
		pst.setInt(3, Constituency.getConstituencyNumber());
		pst.setDouble(4, Constituency.getElectionTypeId());

		int rowsAffected = pst.executeUpdate();

		if (rowsAffected > 0) {

			ResultSet generatedKeys = pst.getGeneratedKeys();

			if (generatedKeys.next()) {

				int generatedId = generatedKeys.getInt(1);

				Constituency.setConstituencyID(generatedId);
			}

			return true;

		} else {

			return false;
		}
	}

	private static boolean insertUpdate(Constituency Constituency, PreparedStatement pst) throws SQLException {

		pst.setString(1, Constituency.getConstituencyName());
		pst.setString(2, Constituency.getDistrictName());
		pst.setInt(3, Constituency.getConstituencyNumber());
		pst.setDouble(4, Constituency.getElectionTypeId());
		pst.setInt(5, 2);

		int row = pst.executeUpdate();
		
        System.out.println(row);
        
		return row > 0;

	}

	public static boolean addConstituency(Constituency constituency) throws SQLException, LeaderValidateException {
		  final String query = "INSERT INTO Constituency (constituencyName, districtName, constituencyNumber, electionTypeId) VALUES (?, ?, ?, ?)";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

				return insertConstituency(constituency, pst);

			} catch (SQLException sqe) {

				System.out.println(sqe.getMessage());
				
				throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
			}
		}
	}

	public static boolean updateConstituency(Constituency constituency) throws SQLException, LeaderValidateException {

		final String query = "UPDATE Constituency SET constituencyName=?, districtName=?, constituencyNumber=?, electionTypeId=? WHERE constituencyID=?";
		
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			return insertUpdate(constituency, pst);

		} catch (SQLException sqe) {
			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
		}
	}

	public static boolean deleteConstituency(int constituencyId) throws SQLException, LeaderValidateException {

		final String query = "DELETE FROM constituency WHERE constituencyID=?";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, constituencyId);

				int rowsDeleted = pst.executeUpdate();

				return rowsDeleted > 0;

			} catch (SQLException sqe) {
				System.out.println(sqe.getMessage());

				throw new LeaderValidateException(LeaderValidateError.INVALID_CONSTITUENCY_ID);
			}
		}
	}

	public static ArrayList<Constituency> readAllConstituencies() throws SQLException {

		ArrayList<Constituency> constituenciesList = new ArrayList<>();

		final String query = "SELECT * FROM Constituency";

		try (Connection connection = ConnectionUtil.getConnection();

				Statement stmt = connection.createStatement()) {

			try (ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					Constituency constituency = new Constituency( "constituency", "district", 1, 1);

					constituency.setConstituencyName(rs.getString(2));
					constituency.setDistrictName(rs.getString(3));
					constituency.setConstituencyNumber(rs.getInt(4));
					constituency.setElectionTypeId(rs.getInt(5));

					constituenciesList.add(constituency);
				}

				return constituenciesList;

			} catch (SQLException sqe) {

				System.out.println(sqe.getMessage());
				return null;
			}
		}
	}

}
