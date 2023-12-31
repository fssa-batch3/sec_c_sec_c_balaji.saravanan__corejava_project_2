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
import com.fssa.politifact.model.Party;
import com.fssa.politifact.util.ConnectionUtil;
import com.fssa.politifact.validator.LeaderValidateError;

/*
 * partyDao this class doing the party table crud operation.
 */

public class PartyDao {

	private PartyDao() {

	}

	public static PartyDao getObj() {

		return new PartyDao();
	}

	/*
	 * the insert party method doing only the prepare statement. and this help to
	 * reduce the method code. the method execute correctly this return the true
	 * otherwise rturn false.
	 */

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

	/*
	 * the insertUpdate is common method doing only the prepare statement. and this
	 * help to reduce the method code. the method execute correctly this return the
	 * true otherwise rturn false.
	 */

	private boolean insertUpdate(Party party, PreparedStatement pst, int id)
			throws SQLException, DaoException {

		
		pst.setString(1, party.getPartyName());
		pst.setString(2, party.getPartyImageUrl());
		pst.setInt(3, id);

		int row = pst.executeUpdate();

		return row > 0;

	}

	/*
	 * the add party method is add the values in the data base. this not doing in
	 * the statement this send the value insertparty doing the preparestatment.
	 */

	public boolean addParty(Party party) throws SQLException, DaoException {

		final String query = "INSERT INTO Party (PartyName, partyImageUrl) VALUES (?, ?)";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

				return insertParty(party, pst);

			} catch (SQLException sqe) {

				throw new DaoException("party add dao error occure "+ sqe.getMessage());
			}
		}
	}

	/*
	 * the update party method is update the values in the data base. this not doing
	 * in the statement this send the value insertparty doing the preparestatment.
	 */

	public boolean updateParty(Party party, int id)
			throws SQLException, DaoException, LeaderValidateException {

		final String query = "UPDATE Party SET PartyName=?, partyImageUrl=? WHERE partyId=?";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				return insertUpdate(party, pst, id);

			}

		} catch (SQLException sqe) {

			throw new DaoException("update party dao error occure "+ sqe.getMessage());
		}
	}

	/*
	 * the deleteParty method is delete the values in the data base.
	 */

	public boolean deleteParty(int id) throws SQLException, DaoException {

		final String query = "DELETE FROM Party WHERE partyId=?";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, id);

				int rowsDeleted = pst.executeUpdate();

				return rowsDeleted > 0;

			} catch (SQLException sqe) {

				throw new DaoException("delete party dao error occure "+ sqe.getMessage());
			}
		}
	}

	/*
	 * read all party values in the table help this table , this not doing any
	 * operation only doing the reading the all values in the data base.
	 */

	public List<Party> readAllParties() throws SQLException, DaoException {

		ArrayList<Party> partyList = new ArrayList<>(); 

		final String query = "SELECT * FROM Party";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (Statement stmt = connection.createStatement()) {

				try (ResultSet rs = stmt.executeQuery(query)) {
					while (rs.next()) {

						Party party = new Party("dmk", "image//url");

						party.setPartyId(rs.getInt(1));

						party.setPartyName(rs.getString(2));

						party.setPartyImageUrl(rs.getString(3));

						partyList.add(party);
					}

					return partyList;

				} catch (SQLException sqe) {

					throw new DaoException("List of party dao error occure "+ sqe.getMessage());
				}
			}
		}
	}

}
