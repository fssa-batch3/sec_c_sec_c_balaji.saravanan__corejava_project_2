package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.politifact.dao.PartyDao;
import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Party;
import com.fssa.politifact.validator.LeaderValidateError;
import com.fssa.politifact.validator.PartyValidator;

/**
 * 
 * @author BalajiSaravanan
 *
 */
public class PartyService {

	public final PartyValidator partyValidator;
	public final PartyDao partyDao;
	
	/**
	 * this is party service constructor
	 * @param partyValidator
	 * @param partyDao
	 */

	public PartyService(PartyValidator partyValidator, PartyDao partyDao) {

		this.partyValidator = partyValidator;

		this.partyDao = partyDao;

	}
	
	/**
	 * add party service metod.
	 * @param party
	 * @return
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException 
	 */

	public boolean addParty(Party party) throws LeaderValidateException, SQLException, DaoException {

		if (party == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
		}
		if (this.partyValidator.validate(party)) {

			return this.partyDao.addParty(party);

		} else {

			return false;
		}

	}
	
	/**
	 * update the party
	 * @param party
	 * @param name
	 * @return
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException 
	 */

	public boolean upDateParty(Party party, String name) throws LeaderValidateException, SQLException, DaoException {

		if (party == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		if (this.partyValidator.validate(party)) {

			return this.partyDao.updateParty(party, name);

		} else {

			return false;

		}

	}
	
	/**
	 * delete the party service layer.
	 * @param party
	 * @return
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException 
	 */

	public boolean deleteParty(String party) throws LeaderValidateException, SQLException, DaoException {
		if (party == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_CANDIDATE_ID);

		}

		if (this.partyValidator.validatePartyName(party)) {

			return this.partyDao.deleteParty(party);

		} else {
			return false;

		}
	}
	
	/**
	 * read all party
	 * @return
	 * @throws SQLException
	 * @throws LeaderValidateException
	 * @throws DaoException 
	 */

	public List<Party> partyList() throws SQLException, LeaderValidateException, DaoException {

		return this.partyDao.readAllParties();

	}

}
