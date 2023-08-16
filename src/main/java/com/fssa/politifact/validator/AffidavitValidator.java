package com.fssa.politifact.validator;

import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Affidavit;

/**
 * 
 * @author BalajiSaravanan
 * 
 *         this is validator affidate validator is validate the affidate model
 *         class all attribute validate
 *
 */

public class AffidavitValidator {
	/**
	 * check the all object
	 * @param affidavit
	 * @return
	 * @throws LeaderValidateException
	 */

	public boolean validate(Affidavit affidavit) throws LeaderValidateException {

		if (affidavit == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		validateElectionId(affidavit.getElectionId());

		validateLeaderId(affidavit.getLeaderId());

		validateAffidavitUrl(affidavit.getAffidateUrl());

		return true;
	}
	
	/**
	 * check the id;
	 * @param id
	 * @throws LeaderValidateException
	 */

	public void validateElectionId(int id) throws LeaderValidateException {

		if (id <= 0) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_ELECTION_ID);
		}

	}
	
	/**
	 * check the election id
	 * @param id
	 * @throws LeaderValidateException
	 */

	public void validateLeaderId(int id) throws LeaderValidateException {

		if (id <= 0) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_ELECTION_ID);
		}

	}
	
	/**
	 * check the affidate url 
	 * @param url
	 * @throws LeaderValidateException
	 */

	public void validateAffidavitUrl(String url) throws LeaderValidateException {

		if (url == null || !url.matches("^(https?://)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(\\/.*)?$")) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_URL);

		}
	}
}
