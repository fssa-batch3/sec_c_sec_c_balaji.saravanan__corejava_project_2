package com.fssa.politifact.validator;

import com.fssa.politifact.enums.ElectionTypes;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Constituency;

/**
 * 
 * @author BalajiSaravanan
 *
 *constituency validator is validate constituency object all attributes.
 */

public class ConstituencyValidator {
	
	/**
	 * this is validate constituency object
	 * @param constituency
	 * @return 
	 * @throws LeaderValidateException
	 */

	public boolean validate(Constituency constituency) throws LeaderValidateException {

		validateConstituencyName(constituency.getConstituencyName());

		validateDistrictName(constituency.getDistrictName());

		validateConstituencyNumber(constituency.getConstituencyNumber());

		validateElectionTypeName(constituency.getElectionTypeName());

		return true;   

	}
	
	/**
	 * check for constituency name
	 * @param cName
	 * @throws LeaderValidateException
	 */

	public void validateConstituencyName(String cName) throws LeaderValidateException {

		if (cName == null || cName.trim().isEmpty() || !cName.matches("[a-zA-Z\\s]+") || cName.length() < 2) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_CONSTITUENCY_NAME);
		}
	}
	
	/**
	 * check for district name.
	 * @param dName
	 * @throws LeaderValidateException
	 */

	public void validateDistrictName(String dName) throws LeaderValidateException {

		if (dName == null || dName.trim().isEmpty() || !dName.matches("[a-zA-Z\\s]+") || dName.length() < 2) {
			throw new LeaderValidateException(LeaderValidateError.INVALID_CONSTITUENCY_NAME);
		}
	}
	
	/**
	 * check for constituency number.
	 * @param no
	 * @throws LeaderValidateException
	 */

	public void validateConstituencyNumber(int no) throws LeaderValidateException {

		if (no <= 0) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_CONSTITUENCY_NUMBER);

		}

	}
	
	/**
	 * check forelection type neme.
	 * @param election
	 * @throws LeaderValidateException
	 */

	public void validateElectionTypeName(int id) throws LeaderValidateException {

		if (id < 0) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_ELECTION_ID);
		}
	}

}
