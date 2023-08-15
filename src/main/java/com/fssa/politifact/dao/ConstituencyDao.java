package com.fssa.politifact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Constituency;
import com.fssa.politifact.util.ConnectionUtil;
import com.fssa.politifact.validator.LeaderValidateError;

public class ConstituencyDao {  
	
	private ConstituencyDao() {
		
	} 
	public static ConstituencyDao getObj() {
		
		return new ConstituencyDao();
		
	}
	
	Logger logger = new Logger();
	 
	/*
	 * This insertConstituency methoed only prepare statement code only run hear.
	 * what reason for that the add leader method codeline i do want to decress so that reson.
	 * this code add leader prepare statement executeupdate code
	 */

	private boolean insertConstituency(Constituency constituency, PreparedStatement pst) throws SQLException {

		int electionTypeId= findElectionTypeId(constituency.getElectionTypeName().toString());
		
		pst.setString(1, constituency.getConstituencyName());
		pst.setString(2, constituency.getDistrictName());
		pst.setInt(3, constituency.getConstituencyNumber());
		pst.setInt(4, electionTypeId);

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
	 * in this method insert update method only prepare statment code heare
	 * this only update the code return a boolean value
	 */
	private boolean insertUpdate(Constituency constituency, PreparedStatement pst, String constituencyUpadateName) throws SQLException {
		
		int electionTypeId= findElectionTypeId(constituency.getElectionTypeName().toString());
		
		int constituncyUpdateId= LeaderDao.findConstituencyId(constituencyUpadateName);
		
		pst.setString(1, constituency.getConstituencyName());
		pst.setString(2, constituency.getDistrictName());
		pst.setInt(3, constituency.getConstituencyNumber());
		pst.setInt(4, electionTypeId);
		pst.setInt(5, constituncyUpdateId);
		
		

		int row = pst.executeUpdate();
        
		return row > 0;

	}
	
	/*
	 * addConstituency add a new constituency in database 
	 * this method hava a connection ans also prepare statement this call a insert constituency
	 * that place run a prepare statement
	 */

	public boolean addConstituency(Constituency constituency) throws SQLException, LeaderValidateException {
		
		  final String query = "INSERT INTO Constituency (constituencyName, districtName, constituencyNumber, electionTypeId) VALUES (?, ?, ?, ?)";

		try (Connection connection = ConnectionUtil.getConnection()) {

			try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

				return insertConstituency(constituency, pst); // invoke the value in to te insert constituency

			} catch (SQLException sqe) {
				
		        logger.info(sqe.getMessage());
				
				throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
			}
		}
	}
	
	/*
	 * update constituency perform update the vlaues in database
	 * this also call a insert update that plass run a prepare Statement
	 */

	public boolean updateConstituency(Constituency constituency, String constituencyName) throws SQLException, LeaderValidateException {

		final String query = "UPDATE Constituency SET constituencyName=?, districtName=?, constituencyNumber=?, electionTypeId=? WHERE constituencyID=?";
		
		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {
			
			return insertUpdate(constituency, pst, constituencyName);  // invoke the value in insert update methode
			

		} catch (SQLException sqe) {
			
			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
		}
	}
	
	/*
	 * deleteconstituency method deleate a constituency 
	 * this perform given id
	 */

	public boolean deleteConstituency(int constituencyId) throws SQLException, LeaderValidateException {

		final String query = "DELETE FROM constituency WHERE constituencyID=?"; //query

		try (Connection connection = ConnectionUtil.getConnection()) {  // connection

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, constituencyId);

				int rowsDeleted = pst.executeUpdate();

				return rowsDeleted > 0;

			} catch (SQLException sqe) {

				throw new LeaderValidateException(LeaderValidateError.INVALID_CONSTITUENCY_ID);
			}
		}
	}
	
	
	/*
	 * readAllConstituencies this method connect the database read the all constituency in the table
	 * and then the store a List and also return
	 * that return value go to the service layer.
	 */

	public List<Constituency> readAllConstituencies() throws SQLException, LeaderValidateException {

		ArrayList<Constituency> constituenciesList = new ArrayList<>();

		final String query = "SELECT * FROM Constituency";

		try (Connection connection = ConnectionUtil.getConnection();

				Statement stmt = connection.createStatement()) {

			try (ResultSet rs = stmt.executeQuery(query)) {
				while (rs.next()) {
					
					Constituency constituency = new Constituency( "constituency", "district", 1, null);
					
					String electionType= findElectionTypeName(rs.getInt("electionTypeId"));
					
					System.out.println(electionType);

					constituency.setConstituencyName(rs.getString(2));
					constituency.setDistrictName(rs.getString(3));
					constituency.setConstituencyNumber(rs.getInt(4));
					constituency.setElectionTypeName(electionType); 

					constituenciesList.add(constituency);
				}

				return constituenciesList;

			} catch (SQLException sqe) {

				throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
			}
		}
	}
	
	
	public static int findElectionTypeId(String electionName) {
		
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
			
			
			sqe.printStackTrace();
		}

		return electionId;
	}

	
public static String findElectionTypeName(int electionId) {
		
		final String query = "SELECT electionType FROM Election WHERE id = ?";

		String electionName="";

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(query)) {

			pst.setInt(1, electionId);

			try (ResultSet resultSet = pst.executeQuery()) {

				if (resultSet.next()) {

					electionName = resultSet.getString("electionType");

				}
			}

		} catch (SQLException sqe) {
			
			
			sqe.printStackTrace();
		}

		return electionName;
	}
}
