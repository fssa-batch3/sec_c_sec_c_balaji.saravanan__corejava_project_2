package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.dao.ConstituencyDao;
import com.fssa.politifact.dao.Logger;
import com.fssa.politifact.model.Constituency;
import com.fssa.politifact.validator.ConstituencyValidator;
import com.fssa.politifact.validator.LeaderValidateException;

public class ConstituencyServiceTest {

	Logger logger = new Logger();

	@Test
	public void testAddConstituency() throws LeaderValidateException, SQLException {

		Constituency constituency = getConstituency();

		ConstituencyService constituencyService = getConstituencyService();

		Assertions.assertTrue(constituencyService.addConstituency(constituency));

	}

	public Constituency getConstituency() {

		Constituency constituency = new Constituency("villupuram", "villupuram", 1, 1);

		return constituency;
	}

	public ConstituencyService getConstituencyService() {

		ConstituencyValidator constituencyValidator = new ConstituencyValidator();

		ConstituencyDao constituencyDao = new ConstituencyDao();

		ConstituencyService constituencyService = new ConstituencyService(constituencyValidator, constituencyDao);

		return constituencyService;
	}

	@Test
	public void tesupDateConstituency() throws LeaderValidateException, SQLException {

		Constituency constituency = new Constituency("chennai", "villupuram", 1, 1);

		ConstituencyService constituencyService = getConstituencyService();

		Assertions.assertTrue(constituencyService.upDateConstituency(constituency));

	}

	@Test
	public void testDeleteConstituency() throws LeaderValidateException, SQLException {

		int id = 4;

		ConstituencyService constituencyService = getConstituencyService();

		Assertions.assertTrue(constituencyService.deleteConstituency(id));
	}

	@Test
	public void testAllConstituency() throws LeaderValidateException, SQLException {

		ConstituencyService constituencyService = getConstituencyService();

		List<Constituency> constituencyList = constituencyService.constuencyList();

		Assertions.assertTrue(constituencyList.size() > 0);

		for (Constituency l : constituencyList) {

			logger.info(l);

		}

	}

}
