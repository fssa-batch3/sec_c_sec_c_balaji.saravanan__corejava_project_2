package com.fssa.politifact.validator;

import com.fssa.politifact.model.Election;
import com.fssa.politifact.model.ElectionTypes;

public class ElectionValidator {

	public boolean validate(Election election) throws LeaderValidateException {

		if (election == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		validateElectionYear(election.getElectionYear());

		validateElectionType(election.getElectionType());

		return true;
	}

	public void validateElectionYear(int year) throws LeaderValidateException {

		if (year < 1900) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_STARTING_DATE);
		}
	}

	public void validateElectionType(ElectionTypes electionType) throws LeaderValidateException {

		if (electionType == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_ELECTIPTION_TYPE);
		}
	}

	
}
