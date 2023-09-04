package com.fssa.politifact.service;

import java.sql.SQLException;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.dao.AffidavitDao;
import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Affidavit;
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

		Affidavit affidavit = new Affidavit(1, 5, "https://www.example.com/affidavit1.pdf");

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

		Affidavit affidavit = new Affidavit(1, 1, "https://www.example.com/affidavit1.pdf");

		AffidavitService affidavitService = getAffidavitService();

		Assertions.assertTrue(affidavitService.upDateAffidavit(affidavit, 1));

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
	

}
