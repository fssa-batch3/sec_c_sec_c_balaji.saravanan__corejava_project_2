package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.dao.ElectionDao;
import com.fssa.politifact.enums.ElectionTypes;
import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Election;
import com.fssa.politifact.util.Logger;
import com.fssa.politifact.validator.ElectionValidator;

/**
 * 
 * @author BalajiSaravanan
 *
 *
 *election service test junit test casses.
 */

 class ElectionServiceTest {
	 
	Logger logger = new Logger();
	
	/**
	 * add election represent add the value in database.
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException
	 */

	@Test
	 void testAddEletion() throws LeaderValidateException, SQLException, DaoException {

		Election election = getElection();

		ElectionService electionService = getElectionService();

		Assertions.assertTrue(electionService.addElection(election));
 
	}
	
	/**
	 * this is election constuctor.
	 * @return
	 */

	public Election getElection() {

		Election election = new Election(1,2023, ElectionTypes.LOCAL_ELECTION);

		return election;
	}
	
	/**
	 * this is service constuctor.
	 * @return
	 */

	public ElectionService getElectionService() {

		ElectionValidator electionValidator = new ElectionValidator();

		ElectionDao electionDao =ElectionDao.getObj();

		ElectionService electionService = new ElectionService(electionValidator, electionDao);

		return electionService;
	}
	
	/**
	 * update election.
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException 
	 */
 
	@Test
	 void tesupDateElection() throws LeaderValidateException, SQLException, DaoException {
		
		Election election = new Election(1,2023, ElectionTypes.LOCAL_ELECTION);

		ElectionService electionService = getElectionService();

		Assertions.assertTrue(electionService.upDateElection(election, ElectionTypes.LOCAL_ELECTION.toString()));

	} 
	
	/**
	 * delete election.
	 * @throws LeaderValidateException
	 * @throws SQLException
	 */

//	@Test
//	public void testDeleteElection() throws LeaderValidateException, SQLException {
//
//		String electionName="";
//
//		ElectionService electionService = getElectionService();
//
//		Assertions.assertTrue(electionService.deleteElection(id));
//	}
	
	/**
	 * reade all election in the table.
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException 
	 */

	@Test
	 void testAllElection() throws LeaderValidateException, SQLException, DaoException {

		ElectionService electionService = getElectionService();

		List<Election> electionList = electionService.electionList();

		Assertions.assertTrue(electionList.size() > 0);

		for (Election l : electionList) {

			logger.info(l);

		}
 
	}


}
