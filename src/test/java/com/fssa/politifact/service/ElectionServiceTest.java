package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.dao.ElectionDao;
import com.fssa.politifact.dao.Logger;
import com.fssa.politifact.model.Election;
import com.fssa.politifact.model.ElectionTypes;
import com.fssa.politifact.validator.ElectionValidator;
import com.fssa.politifact.validator.LeaderValidateException;

public class ElectionServiceTest {
	
	Logger logger = new Logger();

	@Test
	public void testAddEletion() throws LeaderValidateException, SQLException {

		Election election = getElection();

		ElectionService electionService = getElectionService();

		Assertions.assertTrue(electionService.addElection(election));

	}

	public Election getElection() {

		Election election = new Election(1,2023, ElectionTypes.LOCALA_ELECTION);

		return election;
	}

	public ElectionService getElectionService() {

		ElectionValidator electionValidator = new ElectionValidator();

		ElectionDao electionDao = new ElectionDao();

		ElectionService electionService = new ElectionService(electionValidator, electionDao);

		return electionService;
	}

	@Test
	public void tesupDateElection() throws LeaderValidateException, SQLException {
		
		Election election = new Election(1,2023, ElectionTypes.LOCALA_ELECTION);

		ElectionService electionService = getElectionService();

		Assertions.assertTrue(electionService.upDateElection(election));

	} 

//	@Test
//	public void testDeleteElection() throws LeaderValidateException, SQLException {
//
//		int id = 4;
//
//		ElectionService electionService = getElectionService();
//
//		Assertions.assertTrue(electionService.deleteElection(id));
//	}

	@Test
	public void testAllElection() throws LeaderValidateException, SQLException {

		ElectionService electionService = getElectionService();

		List<Election> electionList = electionService.electionList();

		Assertions.assertTrue(electionList.size() > 0);

		for (Election l : electionList) {

			logger.info(l);

		}

	}


}
