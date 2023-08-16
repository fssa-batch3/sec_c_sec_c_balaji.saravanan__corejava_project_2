package com.fssa.politifact.service;

import java.sql.SQLException; 
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fssa.politifact.dao.ConstituencyDao;
import com.fssa.politifact.dao.Logger;
import com.fssa.politifact.enums.ElectionTypes;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Constituency;
import com.fssa.politifact.validator.ConstituencyValidator;

/**
 * 
 * @author BalajiSaravanan
 *  
 *  this is constituenc junit test casses.
 */
class ConstituencyServiceTest {
	
	/**
	 * logger debug and print the value.
	 */

	Logger logger = new Logger();
	
	/**
	 * add constituency . this send the value in service layer that layer validate and then send for dao
	 * @throws LeaderValidateException
	 * @throws SQLException
	 */

	@Test
	 void testAddConstituency() throws LeaderValidateException, SQLException {

		Constituency constituency = getConstituency(); 

		ConstituencyService constituencyService = getConstituencyService();

		Assertions.assertTrue(constituencyService.addConstituency(constituency));

	}
	
	/**
	 * this is constituency constuctor.
	 * @return
	 */

	public Constituency getConstituency() { 

		Constituency constituency = new Constituency("villupuram", "villupuram", 1, ElectionTypes.GENERAL_ELECTION);

		return constituency;
	}
	
	/**
	 * this is service  constuctor.
	 * @return
	 */
 
	public ConstituencyService getConstituencyService() {

		ConstituencyValidator constituencyValidator = new ConstituencyValidator();

		ConstituencyDao constituencyDao = ConstituencyDao.getObj();

		ConstituencyService constituencyService = new ConstituencyService(constituencyValidator, constituencyDao);

		return constituencyService;
	}
	
	/**
	 * update constituency .
	 * @throws LeaderValidateException
	 * @throws SQLException
	 */

	@Test
	 void tesupDateConstituency() throws LeaderValidateException, SQLException {

		Constituency constituency = new Constituency("tambaram", "villupuram", 1, ElectionTypes.GENERAL_ELECTION);

		ConstituencyService constituencyService = getConstituencyService();

		Assertions.assertTrue(constituencyService.upDateConstituency(constituency, "villupuram"));

	}
	
	/**
	 * delete constituency.
	 * @throws LeaderValidateException
	 * @throws SQLException
	 */

//	@Test
//	public void testDeleteConstituency() throws LeaderValidateException, SQLException {
//
//		int id = 4;
//
//		ConstituencyService constituencyService = getConstituencyService();
//
//		Assertions.assertTrue(constituencyService.deleteConstituency(id));
//	}
	
	/**
	 * read all constituency.
	 * @throws LeaderValidateException
	 * @throws SQLException
	 */

	@Test
	void testAllConstituency() throws LeaderValidateException, SQLException {

		ConstituencyService constituencyService = getConstituencyService();

		List<Constituency> constituencyList = constituencyService.constuencyList();

		Assertions.assertTrue(constituencyList.size() > 0);

		for (Constituency l : constituencyList) {

			logger.info(l);

		}

	}

}
