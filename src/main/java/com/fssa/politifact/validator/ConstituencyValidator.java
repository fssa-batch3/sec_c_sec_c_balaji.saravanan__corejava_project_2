package com.fssa.politifact.validator;

import com.fssa.politifact.model.Constituency;

public class ConstituencyValidator {

	public boolean validate(Constituency constituency) throws LeaderValidateException {

		
		validateConstituencyName(constituency.getConstituencyName());
		
		validateDistrictName(constituency.getDistrictName());
		
		validateConstituencyNumber(constituency.getConstituencyNumber());
		
        validateElectionId(constituency.getElectionTypeId());
        
		return true;

	}



	public void validateConstituencyName(String cName) throws LeaderValidateException {
		
		if (cName == null || cName.trim().isEmpty() || !cName.matches("[a-zA-Z\\s]+")) {
			
			throw new LeaderValidateException(LeaderValidateError.INVALID_CONSTITUENCY_NAME);
		}
	}

	public void validateDistrictName(String dName) throws LeaderValidateException {

		if (dName == null || dName.trim().isEmpty() || !dName.matches("[a-zA-Z\\s]+")) {
			throw new LeaderValidateException(LeaderValidateError.INVALID_CONSTITUENCY_NAME);
		}
	}

	public void validateConstituencyNumber(int no) throws LeaderValidateException {

		if (no <= 0) {
			
			throw new LeaderValidateException(LeaderValidateError.INVALID_CONSTITUENCY_NUMBER);

		}

	}
	public void validateElectionId(int id) throws LeaderValidateException {
		
		if(id<=0) {
			
			throw new LeaderValidateException(LeaderValidateError.INVALID_ELECTION_ID);
		}
	}

}
