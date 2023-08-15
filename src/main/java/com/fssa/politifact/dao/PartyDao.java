package com.fssa.politifact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Party;
import com.fssa.politifact.util.ConnectionUtil;
import com.fssa.politifact.validator.LeaderValidateError;

public class PartyDao {
	
	private PartyDao() {
		
	}
	public static PartyDao getObj() {
		
		return new PartyDao(); 
	} 
	
	private boolean insertParty(Party party, PreparedStatement pst) throws SQLException {

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

	private boolean insertUpdate(Party party, PreparedStatement pst, String partyName) throws SQLException {

		int partyId= LeaderDao.findPartyId(partyName);
		
		pst.setString(1, party.getPartyName());
		pst.setString(2, party.getPartyImageUrl());
		pst.setInt(3,partyId);

		int row = pst.executeUpdate();

		return row > 0;

	}

	public boolean addParty(Party party) throws SQLException, LeaderValidateException {
		
		  final String query = "INSERT INTO Party (PartyName, partyImageUrl) VALUES (?, ?)";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

				return insertParty(party, pst);

			} catch (SQLException sqe) {

				
				throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
			}
		}
	}

	public boolean updateParty(Party party, String partyName) throws SQLException, LeaderValidateException {

		final String query = "UPDATE Party SET PartyName=?, partyImageUrl=? WHERE partyId=?";
		
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			return insertUpdate(party, pst, partyName);

		} catch (SQLException sqe) {
			
			
			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
		}
	}

	public boolean deleteParty(String partyName) throws SQLException, LeaderValidateException {

		final String query = "DELETE FROM Parties WHERE partyId=?";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				int partyId= LeaderDao.findPartyId(partyName);
				
				pst.setInt(1, partyId);

				int rowsDeleted = pst.executeUpdate(); 

				return rowsDeleted > 0;

			} catch (SQLException sqe) {
				

				throw new LeaderValidateException(LeaderValidateError.INVALID_CONSTITUENCY_ID);
			}
		}
	}

	public List<Party> readAllParties() throws SQLException, LeaderValidateException {

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

				throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
			}
		}
	} 


}
