package com.fssa.politifact.service;

import static org.junit.Assert.assertThrows;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.dao.LeaderDao;
import com.fssa.politifact.dao.Logger;
import com.fssa.politifact.model.Leaders;
import com.fssa.politifact.model.Position;
import com.fssa.politifact.validator.LeaderValidateError;
import com.fssa.politifact.validator.LeaderValidateException;
import com.fssa.politifact.validator.LeaderValidator;

public class LeaderServicetest {

	Logger logger = new Logger();

	@Test
	public void testAddLeader() throws LeaderValidateException, SQLException {

		Leaders leader = getLeaders();

		LeaderService leaderService = getLeaderService();

		Assertions.assertTrue(leaderService.addLeader(leader));

	}

	public Leaders getLeaders() {

		Leaders leader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 3, "Birth Description",
				"Education Description", "Work Experience Description", "Politics Description", "Family Description",
				"Income Description", "https://www.example.com/image.jpg", 2);

		return leader;
	}

	public LeaderService getLeaderService() {

		LeaderValidator leaderValidator = new LeaderValidator();

		LeaderDao leaderDao = new LeaderDao();

		LeaderService leaderService = new LeaderService(leaderValidator, leaderDao);

		return leaderService;
	}

	@Test

	public void testInvalidLeader() throws LeaderValidateException {

		LeaderService leaderService = getLeaderService();

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderService.addLeader(null));
		Assertions.assertEquals(LeaderValidateError.INVALID_LEADER_NULL, exception.getMessage());

	}

	@Test
	public void testUpdateLeader() throws LeaderValidateException, SQLException {

		Leaders leader = new Leaders("bhack", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 2, "Birth Description",
				"Education Description", "Work Experience Description", "Politics Description", "Family Description",
				"Income Description", "https://www.example.com/image.jpg", 2);

		LeaderService leaderService = getLeaderService();

		Assertions.assertTrue(leaderService.upDateLeader(leader));

	}

	@Test
	public void testUpdateLeaderInvalid() throws LeaderValidateException {

		LeaderService leaderService = getLeaderService();

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderService.upDateLeader(null));

		Assertions.assertEquals(LeaderValidateError.INVALID_LEADER_NULL, exception.getMessage());

	}

//	@Test
//	public void testDeleteLeader() throws LeaderValidateException, SQLException {
//
//		int id = 3;
//
//		LeaderService leaderService = getLeaderService();
//
//		Assertions.assertTrue(leaderService.deleteLeader(id));
//	}

	@Test
	public void testAllLeader() throws LeaderValidateException, SQLException {

		LeaderService leaderService = getLeaderService();

		List<Leaders> leadersList = leaderService.callAllLeader();

		Assertions.assertTrue(leadersList.size() > 0);

		for (Leaders l : leadersList) {
			logger.info(l);
		}

	}

//	@Test
//	public void testAllLeaderWithConstituency()throws LeaderValidateException, SQLException{
//		
//		LeaderService leaderService = getLeaderService();
//		
//		List<Leaders> leadersList = leaderService.readLeaderWithConstituency();
//		
//		Assertions.assertTrue(leadersList.size()>0);
//		
//		for(Leaders l: leadersList) {
//			logger.info(l);
//		}
//		
//		
//	}

}
