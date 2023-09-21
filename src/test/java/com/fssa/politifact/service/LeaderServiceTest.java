package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.dao.LeaderDao;
import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Leader;
import com.fssa.politifact.util.Logger;
import com.fssa.politifact.validator.LeaderValidateError;
import com.fssa.politifact.validator.LeaderValidator;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 
 * @author BalajiSaravanan
 *
 * leader test give the value in service laye that layer validate all value and then send the dao.
 */
class LeaderServiceTest {

	Logger logger = new Logger();
	 
	/**
	 * add leader give the value in service layer.
	 * @throws SQLException
	 * @throws LeaderValidateException 
	 * @throws DaoException 
	 */ 

	@Test
	void testAddLeader() throws SQLException, LeaderValidateException, DaoException {

		Leader leader = getLeader();

		LeaderService leaderService = getLeaderService();

		Assertions.assertTrue(leaderService.addLeader(leader));

	}
	
	/**
	 * this is getter setter for leader.
	 * @return
	 */

	public Leader getLeader() {

		Leader leader = new Leader();

		leader.setName("balaji");
		leader.setPosition("GENERAL_ELECTION");
		leader.setPartyName("dmk");
		leader.setExperience(2.2);
		leader.setOccupation("politicin");
		leader.setCounstuencyName("perungudi");
		leader.setDescriptionOfBirth("Description of Birth");
		leader.setDescriptionOfEducation("Description of education");
		leader.setDescriptionOfPastWorkExperience("Description of Past work experience");
		leader.setDescritionOfpolitics("Description of Politics");
		leader.setDescriptionOffamily("Description of Family");
		leader.setDescriptionOfIncome("Description of income");
		leader.setImageUrl("https://www.example.com/image.jpg");

		return leader;
	}
	
	/**
	 * it is a constructor of service.
	 * @return
	 */

	public LeaderService getLeaderService() {
 
		LeaderValidator leaderValidator = new LeaderValidator();
		

		LeaderDao leaderDao = LeaderDao.getObj();

		LeaderService leaderService = new LeaderService(leaderValidator, leaderDao);
		
		return leaderService;
	}
	
	/**
	 * check for invalid leader
	 * @throws LeaderValidateException
	 */

	@Test

	void testInvalidLeader() throws LeaderValidateException {

		LeaderService leaderService = getLeaderService();

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderService.addLeader(null));
		Assertions.assertEquals(LeaderValidateError.INVALID_LEADER_NULL, exception.getMessage());

	}
	 
	/**
	 * update leader.
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException
	 */
 
	@Test
	void testUpdateLeader() throws LeaderValidateException, SQLException, DaoException {

		Leader leader = new Leader();

		leader.setName("Uthayanithi");
		leader.setPosition("CHIEF_MINISTER");
		leader.setPartyName("DMK");
		leader.setExperience(2.2);
		leader.setOccupation("politicin"); 
		leader.setCounstuencyName("perungudi");
		leader.setDescriptionOfBirth("Description of Birth");
		leader.setDescriptionOfEducation("Description of education");
		leader.setDescriptionOfPastWorkExperience("Description of Past work experience");
		leader.setDescritionOfpolitics("Description of Politics");
		leader.setDescriptionOffamily("Description of Family");
		leader.setDescriptionOfIncome("Description of income");
		leader.setImageUrl("https://www.example.com/image.jpg");

		LeaderService leaderService = getLeaderService();

		Assertions.assertTrue(leaderService.upDateLeader(leader, 18)); 

	}
	/**
	 * this is invalid update check method
	 * @throws SQLException
	 * @throws DaoException
	 */
	
	@Test
	void testInvalidUpdateLeader() throws SQLException, DaoException {

		Leader leader = new Leader();

		leader.setName("balaji");
		leader.setPosition("CHIEF_MINISTER");
		leader.setPartyName("DMKk");
		leader.setExperience(2.2);
		leader.setOccupation("politicin"); 
		leader.setCounstuencyName("perungudi");
		leader.setDescriptionOfBirth("Description of Birth");
		leader.setDescriptionOfEducation("Description of education");
		leader.setDescriptionOfPastWorkExperience("Description of Past work experience");
		leader.setDescritionOfpolitics("Description of Politics");
		leader.setDescriptionOffamily("Description of Family");
		leader.setDescriptionOfIncome("Description of income");
		leader.setImageUrl("https://www.example.com/image.jpg");

		LeaderService leaderService = getLeaderService();

		DaoException exception = assertThrows(DaoException.class,()->leaderService.upDateLeader(leader, 1));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_PARTYNAME,exception.getMessage());

	}
	
	/**
	 * 
	 * @throws LeaderValidateException
	 */

	@Test
	void testUpdateLeaderInvalid() throws LeaderValidateException { 

		LeaderService leaderService = getLeaderService();

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderService.upDateLeader(null, 0));

		Assertions.assertEquals(LeaderValidateError.INVALID_LEADER_NULL, exception.getMessage());

	}
	
	/**
	 * delete the leader.
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException
	 */

//	@Test
//	public void testDeleteLeader() throws LeaderValidateException, SQLException, DaoException {
//
//		int id = 19;
//
//		LeaderService leaderService = getLeaderService();
//
//		Assertions.assertTrue(leaderService.deleteLeader(id));
//	}
	
	
	/**
	 * read the all value in the database.
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException
	 */

	@Test
	void testAllLeader() throws LeaderValidateException, SQLException, DaoException {

		LeaderService leaderService = getLeaderService();

		List<Leader> leadersList = leaderService.callAllLeader();

		Assertions.assertTrue(leadersList.size() > 0);

		for (Leader leaderInfo : leadersList) { 
			
				logger.info(leaderInfo);

		}
	}
	
	/**
	 * this is join function method.
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException
	 */
	
	@Test
	void testAllLeaderWithJoin() throws LeaderValidateException, SQLException, DaoException {

		LeaderService leaderService = getLeaderService(); 

		List<String> leadersList = leaderService.readAllLeader();

		Assertions.assertTrue(leadersList.size() > 0);

		for (String leaderInfo : leadersList) {

			String[] leaderAttributes = leaderInfo.split(", ");

			for (String attribute : leaderAttributes) {

				logger.info(attribute);

			} 
			logger.info(" ");
		}
	}
	
	
	
	@Test
	void testSpeiicLeader() throws LeaderValidateException, SQLException, DaoException {

		LeaderService leaderService = getLeaderService();

		List<Leader> leadersList = leaderService.readSpecifc(18);

		Assertions.assertTrue(leadersList.size() > 0);
			
				logger.info(leadersList);

		
	}
	
	
	@Test
	void testUpdateVerify() throws LeaderValidateException, SQLException, DaoException {

		LeaderService leaderService = getLeaderService();

		Assertions.assertTrue(leaderService.updateVeerify("VERIFY", 5)); 

	}
	
	

}
