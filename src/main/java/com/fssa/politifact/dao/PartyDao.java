package com.fssa.politifact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fssa.politifact.model.Party;
import com.fssa.politifact.util.ConnectionUtil;
import com.fssa.politifact.validator.LeaderValidateError;
import com.fssa.politifact.validator.LeaderValidateException;

public class PartyDao {
	
	private static boolean insertParty(Party party, PreparedStatement pst) throws SQLException {

		pst.setString(1, party.getPartyName());
		pst.setString(2, party.getPartyImageUrl());
		

		int rowsAffected = pst.executeUpdate();

		if (rowsAffected > 0) {

			ResultSet generatedKeys = pst.getGeneratedKeys();

			if (generatedKeys.next()) {

				int generatedId = generatedKeys.getInt(1);

				party.setPartyId(generatedId);
			}

			return true;

		} else {

			return false;
		}
	}

	private static boolean insertUpdate(Party party, PreparedStatement pst) throws SQLException {

		pst.setString(1, party.getPartyName());
		pst.setString(2, party.getPartyImageUrl());
		pst.setInt(3,1);

		int row = pst.executeUpdate();

		return row > 0;

	}

	public static boolean addParty(Party party) throws SQLException, LeaderValidateException {
		
		  final String query = "INSERT INTO Party (PartyName, partyImageUrl) VALUES (?, ?)";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

				return insertParty(party, pst);

			} catch (SQLException sqe) {

				System.out.println(sqe.getMessage());
				
				throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
			}
		}
	}

	public static boolean updateParty(Party party) throws SQLException, LeaderValidateException {

		final String query = "UPDATE Party SET PartyName=?, partyImageUrl=? WHERE partyId=?";
		
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			return insertUpdate(party, pst);

		} catch (SQLException sqe) {
			
			System.out.println(sqe.getMessage());
			
			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
		}
	}

	public static boolean deleteParty(int partyId) throws SQLException, LeaderValidateException {

		final String query = "DELETE FROM Parties WHERE partyId=?";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, partyId);

				int rowsDeleted = pst.executeUpdate();

				return rowsDeleted > 0;

			} catch (SQLException sqe) {
				
				System.out.println(sqe.getMessage());

				throw new LeaderValidateException(LeaderValidateError.INVALID_CONSTITUENCY_ID);
			}
		}
	}

	public static ArrayList<Party> readAllParties() throws SQLException {

		ArrayList<Party> partyList = new ArrayList<>();

		final String query = "SELECT * FROM Party";

		try (Connection connection = ConnectionUtil.getConnection();

				Statement stmt = connection.createStatement()) {

			try (ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					
					Party party = new Party( "dmk", "image//url");
					
					party.setPartyName(rs.getString(2));
					party.setPartyImageUrl(rs.getString(3));
					
					partyList.add(party);
				}

				return partyList;

			} catch (SQLException sqe) {

				System.out.println(sqe.getMessage());
				
				return null;
			}
		}
	} 


}
