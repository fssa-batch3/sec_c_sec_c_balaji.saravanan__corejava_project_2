package com.fssa.politifact.validator;

import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Party;

/**
 * 
 * @author BalajiSaravanan
 *
 *party validator validate party object.
 */
public class PartyValidator {
	
	/**
	 * validate party object.
	 * @param party
	 * @return
	 * @throws LeaderValidateException
	 */

	public boolean validate(Party party) throws LeaderValidateException {
		
		if (party == null) {
			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
		}
		
		validatePartyName(party.getPartyName());
		validatePartyImageUrl(party.getPartyImageUrl());
		
		return true;
	} 
	
	/**
	 * validateparty name.
	 * @param partyName
	 * @return
	 * @throws LeaderValidateException
	 */
	
	public boolean validatePartyName(String partyName) throws LeaderValidateException {
		
	    if (partyName == null || partyName.trim().isEmpty() || !partyName.matches("[a-zA-Z\\s]+")) {
	    	
	        throw new LeaderValidateException(LeaderValidateError.INVALID_PARTYNAME);
	        
	    }
	    return true;
	}
	
	/**
	 * validate party image url.
	 * @param partyImageUrl
	 * @throws LeaderValidateException
	 */
	public void validatePartyImageUrl(String partyImageUrl) throws LeaderValidateException {
	    if (partyImageUrl == null || partyImageUrl.isEmpty()
	            || !partyImageUrl.matches("^https?://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(:(\\d+))?(/\\S*)?$")) {
	        throw new LeaderValidateException(LeaderValidateError.INVALID_URL);
	    }
	}
}
