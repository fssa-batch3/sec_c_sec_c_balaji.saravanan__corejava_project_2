package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.dao.AffidavitDao;
import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Affidavit;
import com.fssa.politifact.model.Leader;
import com.fssa.politifact.util.Logger;
import com.fssa.politifact.validator.AffidavitValidator;

 class AffidavitServiceTest {
	
	/**
	 * logger debug and print the value.
	 */

	Logger logger = new Logger();
	
	/**
	 * add affidavit . this send the value in service layer that layer validate and then send for dao
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException 
	 */

	@Test
	 void testAddAffidavit() throws LeaderValidateException, SQLException, DaoException {

		Affidavit affidavit = getAffidavit(); 

		AffidavitService affidavitService = getAffidavitService();

		Assertions.assertTrue(affidavitService.addAffidavit(affidavit));

	}
	
	/**
	 * this is affidavit constuctor.
	 * @return
	 */

	public Affidavit getAffidavit() { 

		Affidavit affidavit = new Affidavit(1, 18, "https://www.example.com/affidavit1.pdf");

		return affidavit;
	}
	
	/**
	 * this is service  constuctor.
	 * @return
	 */
 
	public AffidavitService getAffidavitService() {

		AffidavitValidator affidavitValidator = new AffidavitValidator();

		AffidavitDao affidavitDao = AffidavitDao.getObj();

		AffidavitService affidavitService = new AffidavitService(affidavitDao, affidavitValidator);

		return affidavitService;
	}
	
	/**
	 * update affidavit .
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException 
	 */

	@Test
	 void tesupDateAffidavit() throws LeaderValidateException, SQLException, DaoException {

		Affidavit affidavit = new Affidavit(1, 18, "https://www.example.com/affidavit1.pdf");

		AffidavitService affidavitService = getAffidavitService();

		Assertions.assertTrue(affidavitService.upDateAffidavit(affidavit, 4));

	}
	
	/**
	 * delete affdavit.
	 * @throws LeaderValidateException
	 * @throws SQLException
	 */

//	@Test
//	public void testDeleteAffidavit() throws LeaderValidateException, SQLException {
//
//		int id = 4;
//
//		AffidavitService affidavitService = getAffidavitService();
//
//		Assertions.assertTrue(affidavitService.upDateAffidavit(id));
//	}
	
	@Test
	 void readAllAffidavit() throws LeaderValidateException, SQLException, DaoException {

		

		AffidavitService affidavitService = getAffidavitService(); 

		List<Affidavit> affidavit= affidavitService.readAll();
		
		Assertions.assertTrue(affidavit.size() > 0);
			
			logger.info(affidavit);
		
	}
	
	
	@Test
	 void testLeaderAffidavit() throws LeaderValidateException, SQLException, DaoException {

		

		AffidavitService affidavitService = getAffidavitService(); 

		List<Affidavit> affidavit= affidavitService.readSpecificLeader(18);
		
		Assertions.assertTrue(affidavit.size() > 0);
			
			logger.info(affidavit);
		
	}
	
	
	
	@Test
	 void testLeaderParty() throws LeaderValidateException, SQLException, DaoException {

		

		AffidavitService affidavitService = getAffidavitService(); 

		List<Leader> affidavit= affidavitService.readSpecificLeaderPartyId(11);
		
		Assertions.assertTrue(affidavit.size() > 0);
		
		for(Leader party: affidavit) {
			
			logger.info(party);
		}
		
	}
	
	
	

}
