package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.politifact.dao.PartyDao;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Party;
import com.fssa.politifact.validator.LeaderValidateError;
import com.fssa.politifact.validator.PartyValidator;

public class PartyService {

	public final PartyValidator partyValidator;
	public final PartyDao partyDao;

	public PartyService(PartyValidator partyValidator, PartyDao partyDao) {

		this.partyValidator = partyValidator;

		this.partyDao = partyDao;

	}

	public boolean addParty(Party party) throws LeaderValidateException, SQLException {

		if (party == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
		}
		if (this.partyValidator.validate(party)) {

			return this.partyDao.addParty(party);

		} else {

			return false;
		}

	}

	public boolean upDateParty(Party party, String name) throws LeaderValidateException, SQLException {

		if (party == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		if (this.partyValidator.validate(party)) {

			return this.partyDao.updateParty(party, name);

		} else {

			return false;

		}

	}

	public boolean deleteParty(String party) throws LeaderValidateException, SQLException {
		if (party == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_CANDIDATE_ID);

		}

		if (this.partyValidator.validatePartyName(party)) {

			return this.partyDao.deleteParty(party);

		} else {
			return false;

		}
	}

	public List<Party> partyList() throws SQLException, LeaderValidateException {

		return this.partyDao.readAllParties();

	}

}
