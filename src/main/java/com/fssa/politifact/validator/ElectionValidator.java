package com.fssa.politifact.validator;

import com.fssa.politifact.enums.ElectionTypes;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Election;

/**
 * 
 * @author BalajiSaravanan
 *
 *validate election object all attributes.
 */

public class ElectionValidator {
	
	/**
	 * validate election object. 
	 * @param election
	 * @return 
	 * @throws LeaderValidateException
	 */

	public boolean validate(Election election) throws LeaderValidateException {

		if (election == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		validateElectionYear(election.getElectionYear());

		validateElectionType(election.getElectionType());

		return true;
	}
	
	/**
	 * validate election year.
	 * @param year
	 * @throws LeaderValidateException
	 */

	public void validateElectionYear(int year) throws LeaderValidateException {

		if (year < 1900) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_ELECTION_YEAR);
		}
	}
	
	/**
	 * validate election type.
	 * @param electionType
	 * @return
	 * @throws LeaderValidateException
	 */

	public boolean validateElectionType(ElectionTypes electionType) throws LeaderValidateException {

		if (electionType == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_ELECTION_TYPE);
		}
		return true;
	}
	

	
}
