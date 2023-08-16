package com.fssa.politifact.service;

import static org.junit.Assert.assertThrows;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.politifact.dao.LeaderDao;
import com.fssa.politifact.dao.Logger;
import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Leader;
import com.fssa.politifact.validator.LeaderValidateError;
import com.fssa.politifact.validator.LeaderValidator;

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
	 */

	@Test
	void testAddLeader() throws SQLException, LeaderValidateException {

		Leader leader = getLeaders();

		LeaderService leaderService = getLeaderService();

		Assertions.assertTrue(leaderService.addLeader(leader));

	}
	
	/**
	 * this is getter setter for leader.
	 * @return
	 */

	public Leader getLeaders() {

		Leader leader = new Leader();

		leader.setName("balaji");
		leader.setPosition("CHIEF_MINISTER");
		leader.setPartyName("Independent candidate");
		leader.setExperience(2.2);
		leader.setOccupation("politicin");
		leader.setCounstuencyName("villupuram");
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
	 * it is a constuctor of service.
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

		leader.setName("bhack");
		leader.setPosition("CHIEF_MINISTER");
		leader.setPartyName("Independent candidate");
		leader.setExperience(2.2);
		leader.setOccupation("politicin");
		leader.setCounstuencyName("villupuram");
		leader.setDescriptionOfBirth("Description of Birth");
		leader.setDescriptionOfEducation("Description of education");
		leader.setDescriptionOfPastWorkExperience("Description of Past work experience");
		leader.setDescritionOfpolitics("Description of Politics");
		leader.setDescriptionOffamily("Description of Family");
		leader.setDescriptionOfIncome("Description of income");
		leader.setImageUrl("https://www.example.com/image.jpg");

		LeaderService leaderService = getLeaderService();

		Assertions.assertTrue(leaderService.upDateLeader(leader, "bhack"));

	}
	
	/**
	 * 
	 * @throws LeaderValidateException
	 */

	@Test
	void testUpdateLeaderInvalid() throws LeaderValidateException {

		LeaderService leaderService = getLeaderService();

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderService.upDateLeader(null, ""));

		Assertions.assertEquals(LeaderValidateError.INVALID_LEADER_NULL, exception.getMessage());

	}
	
	/**
	 * delete the leader.
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException
	 */

//	@Test
//	public void testDeleteLeader() throws LeaderValidateException, SQLException {
//
//		int id = "balaji";
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
	
	
	

}
