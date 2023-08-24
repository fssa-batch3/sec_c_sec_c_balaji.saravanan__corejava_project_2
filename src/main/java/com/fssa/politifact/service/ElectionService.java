package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.politifact.dao.ElectionDao;
import com.fssa.politifact.enums.ElectionTypes;
import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Election;
import com.fssa.politifact.validator.ElectionValidator;
import com.fssa.politifact.validator.LeaderValidateError;

/*
 * election service validate election object 
 * election validate all test case pass then invokke the object in election dao
 */

public class ElectionService {

	private final ElectionValidator electionValidator;

	private final ElectionDao electionDao;
	/**
	 * this is constuctor
	 * @param electionValidator
	 * @param electionDao
	 */
 
	public ElectionService(ElectionValidator electionValidator, ElectionDao electionDao) {

		this.electionValidator = electionValidator;
		this.electionDao = electionDao;
	}
	/**
	 * add election service check the validator and then sen value in dao layer
	 * @param election
	 * @return true or false
	 * @throws DaoException
	 * @throws SQLException
	 * @throws LeaderValidateException
	 */

	public boolean addElection(Election election) throws DaoException, SQLException, LeaderValidateException {

		if (election == null) {

			throw new DaoException(LeaderValidateError.INVALID_OBJECT);

		}

		if (this.electionValidator.validate(election)) {

			return this.electionDao.addElection(election);
		} else {

			return false;

		}

	}
	
	/**
	 * update election doing receive on objet and one name for updating purpose.
	 * @param election
	 * @param electionName
	 * @return
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException 
	 */

	public boolean upDateElection(Election election, String electionName) throws LeaderValidateException, SQLException, DaoException {

		if (election == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		if (this.electionValidator.validate(election)) {

			return this.electionDao.updateElection(election, electionName);
		} else {

			return false;

		}

	}
	
	/**
	 * delete election method delete the row of data base .
	 * @param electionName
	 * @return
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException 
	 */

	public boolean deleteElection(String electionName) throws LeaderValidateException, SQLException, DaoException {
		if (electionName == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_CANDIDATE_ID);

		}

		if (electionValidator.validateElectionType(ElectionTypes.valueOf(electionName))) {

			return this.electionDao.deleteElection(electionName);

		} else {
			return false;

		}
	}
	
	/**
	 * this election list return. 
	 * @return
	 * @throws LeaderValidateException
	 * @throws SQLException
	 * @throws DaoException 
	 */

	public List<Election> electionList() throws LeaderValidateException, SQLException, DaoException {

		return this.electionDao.readAllElection();

	}

}
