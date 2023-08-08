package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.fssa.politifact.dao.PartyDao;
import com.fssa.politifact.model.Party;
import com.fssa.politifact.validator.LeaderValidateError;
import com.fssa.politifact.validator.LeaderValidateException;
import com.fssa.politifact.validator.PartyValidator;

public class PartyService {

	public PartyValidator partyValidator;
	public PartyDao partyDao;

	public PartyService(PartyValidator partyValidator, PartyDao partyDao) {
		
		this.partyValidator = partyValidator;
		
		this.partyDao = partyDao;
		
	}

	public boolean addParty(Party party) throws LeaderValidateException, SQLException {

		if (party == null) {
			
			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
		}
		if (this.partyValidator.validate(party)) {
			
			return partyDao.addParty(party);

		} else {
			
			return false;
		}

	}

	public boolean upDateParty(Party party) throws LeaderValidateException, SQLException {

		if (party == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		if (this.partyValidator.validate(party)) {

			return this.partyDao.updateParty(party);
			
		} else {

			return false;
 
		}

	}

	public boolean deleteParty(int party) throws LeaderValidateException, SQLException {
		if (party < 0) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_CANDIDATE_ID);

		}

		if (party > 0) {

			return this.partyDao.deleteParty(party);

		} else {
			return false;

		}
	}

	public ArrayList<Party> PartyList() throws LeaderValidateException, SQLException {

		return this.partyDao.readAllParties();

	}

}
