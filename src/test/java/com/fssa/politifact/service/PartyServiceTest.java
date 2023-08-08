package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.dao.Logger;
import com.fssa.politifact.dao.PartyDao;
import com.fssa.politifact.model.Party;
import com.fssa.politifact.validator.LeaderValidateException;
import com.fssa.politifact.validator.PartyValidator;

public class PartyServiceTest {
	
	Logger logger = new Logger();

	@Test
	public void testAddParty() throws LeaderValidateException, SQLException {

		Party party = getParty();

		PartyService partyService = getPartyService();

		Assertions.assertTrue(partyService.addParty(party));

	}

	public Party getParty() {

		Party party = new Party("dmk", "https://www.example.com/party.jpg");

		return party;
	}

	public PartyService getPartyService() {

		PartyValidator PartyValidator = new PartyValidator();

		PartyDao partyDao = new PartyDao();

		PartyService partyService = new PartyService(PartyValidator, partyDao);

		return partyService;
	}

	@Test
	public void tesupDateParty() throws LeaderValidateException, SQLException {

		Party party = new Party("aiadmk", "https://www.example.com/party.jpg");

		PartyService partyService = getPartyService();
		
		Assertions.assertTrue(partyService.upDateParty(party));

	}

//	@Test
//	public void testDeleteParty() throws LeaderValidateException, SQLException {
//
//		int id = 4;
//
//		PartyService partyService = getPartyService();
//		
//		Assertions.assertTrue(partyService.deleteParty(id));
//	}

	@Test
	public void testAllParty() throws LeaderValidateException, SQLException {

		PartyService partyService = getPartyService();
		
		List<Party> PartyList =partyService.PartyList();

		Assertions.assertTrue(PartyList.size() > 0);

		for (Party l : PartyList) {

			logger.info(l);

		}

	}


}
