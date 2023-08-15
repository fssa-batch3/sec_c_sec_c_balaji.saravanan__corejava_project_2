package com.fssa.politifact.validator;

import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Affidavit;

public class AffidavitValidator {

	public boolean validate(Affidavit affidavit) throws LeaderValidateException {
		
		if(affidavit==null) {
			
			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);	
			
		}
		
		validateElectionId(affidavit.getElectionId());
		
		validateLeaderId(affidavit.getLeaderId());
		
		validateAffidavitUrl(affidavit.getAffidateUrl());
		
		return true;
	}

	public void validateElectionId(int id) throws LeaderValidateException {

		if (id <= 0) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_ELECTION_ID);
		}

	}

	public void validateLeaderId(int id) throws LeaderValidateException {

		if (id <= 0) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_ELECTION_ID);
		}

	}
	
	public void validateAffidavitUrl(String url) throws LeaderValidateException {
		
		   if (url == null || !url.matches("^(https?://)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(\\/.*)?$")) {
			   
	            throw new LeaderValidateException(LeaderValidateError.INVALID_URL);
	            
	        }
	}
}
