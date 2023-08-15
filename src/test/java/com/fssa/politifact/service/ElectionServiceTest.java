package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.dao.ElectionDao;
import com.fssa.politifact.dao.Logger;
import com.fssa.politifact.enums.ElectionTypes;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Election;
import com.fssa.politifact.validator.ElectionValidator;

 class ElectionServiceTest {
	
	Logger logger = new Logger();

	@Test
	 void testAddEletion() throws LeaderValidateException, SQLException {

		Election election = getElection();

		ElectionService electionService = getElectionService();

		Assertions.assertTrue(electionService.addElection(election));
 
	}

	public Election getElection() {

		Election election = new Election(1,2023, ElectionTypes.LOCAL_ELECTION);

		return election;
	}

	public ElectionService getElectionService() {

		ElectionValidator electionValidator = new ElectionValidator();

		ElectionDao electionDao =ElectionDao.getObj();

		ElectionService electionService = new ElectionService(electionValidator, electionDao);

		return electionService;
	}
 
	@Test
	 void tesupDateElection() throws LeaderValidateException, SQLException {
		
		Election election = new Election(1,2023, ElectionTypes.LOCAL_ELECTION);

		ElectionService electionService = getElectionService();

		Assertions.assertTrue(electionService.upDateElection(election, ElectionTypes.LOCAL_ELECTION.toString()));

	} 

//	@Test
//	public void testDeleteElection() throws LeaderValidateException, SQLException {
//
//		String electionName="";
//
//		ElectionService electionService = getElectionService();
//
//		Assertions.assertTrue(electionService.deleteElection(id));
//	}

	@Test
	 void testAllElection() throws LeaderValidateException, SQLException {

		ElectionService electionService = getElectionService();

		List<Election> electionList = electionService.electionList();

		Assertions.assertTrue(electionList.size() > 0);

		for (Election l : electionList) {

			logger.info(l);

		}

	}


}
