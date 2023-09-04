package com.fssa.politifact.validator;

import com.fssa.politifact.enums.Position;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Leader;
/**
 * 
 * @author BalajiSaravanan
 *
 *
 *leader validator validate the all attributes in leader object.
 */
public class LeaderValidator {
	
	/**
	 * this validate all attribute in leader object.
	 * @param leader
	 * @return
	 * @throws LeaderValidateException
	 */

	public boolean validate(Leader leader) throws LeaderValidateException {

		if (leader == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_LEADER_NULL);
		} 

		validateName(leader.getName());
		validatePosition(leader.getPosition());
		validatePartyName(leader.getPartyName());
		validateExperience(leader.getExperience());
		validateOccupation(leader.getOccupation());
		validateConstituencyName(leader.getCounstuencyName());
		validateDescriptionOfBirth(leader.getDescriptionOfBirth());
		validateDescriptionOfEducation(leader.getDescriptionOfEducation());
		validateDescriptionOfPastWorkExperience(leader.getDescriptionOfPastWorkExperience());
		validateDescritionOfPolitics(leader.getDescritionOfpolitics());
		validateDescriptionOfFamily(leader.getDescriptionOffamily());
		validateDescriptionOfIncome(leader.getDescriptionOfIncome());
		validateUrl(leader.getImageUrl());

		return true;
	}
	
	/**
	 * validate name.
	 * @param name
	 * @return
	 * @throws LeaderValidateException
	 */

	public boolean validateName(String name) throws LeaderValidateException {

		if (name == null || name.trim().isEmpty() || name.length() <= 2 || !name.matches("[A-Za-z\\s]+")) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_NAME);
		}
		return true;
	}
	
	/**
	 * validate the position.
	 * @param position
	 * @throws LeaderValidateException
	 */

	public void validatePosition(Position position) throws LeaderValidateException {

		if (position == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_POSITION);
		}
	}
	
	/**
	 * validate the party name.
	 * @param partyName
	 * @throws LeaderValidateException
	 */

	public void validatePartyName(String partyName) throws LeaderValidateException {

		if (partyName == null || partyName.isEmpty()) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_PARTYNAME);
		}
	}
	
	/**
	 * validate the eperience.
	 * @param experience
	 * @throws LeaderValidateException
	 */

	public void validateExperience(double experience) throws LeaderValidateException {

		if (experience <= 0) {
			throw new LeaderValidateException(LeaderValidateError.INVALID_EXPERIENCE);
		}
	}
	
	/**
	 * validate the occupation.
	 * @param occupation
	 * @throws LeaderValidateException
	 */

	public void validateOccupation(String occupation) throws LeaderValidateException {

		if (occupation == null || occupation.trim().isEmpty() || occupation.length() < 2
				|| !occupation.matches("[a-zA-Z//s]+")) {
			throw new LeaderValidateException(LeaderValidateError.INVALID_OCCUPATION);
		}
	}
	
	/**
	 * validate the constituency name.
	 * @param constituencyName
	 * @throws LeaderValidateException
	 */

	public void validateConstituencyName(String constituencyName) throws LeaderValidateException {

		if (constituencyName == null || constituencyName.isEmpty()) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_CONSTITUENCY_NAME);
		}
	}
	
	/**
	 * validate descriptions
	 * @param description
	 * @throws LeaderValidateException
	 */

	public void validateDescriptionOfBirth(String description) throws LeaderValidateException {
		if (description == null || description.isEmpty()) {
			throw new LeaderValidateException(LeaderValidateError.INVALID_DESCRIPTION);
		}
	}

	public void validateDescriptionOfPastWorkExperience(String description) throws LeaderValidateException {
		if (description.isEmpty()) {
			throw new LeaderValidateException(LeaderValidateError.INVALID_DESCRIPTION);
		}
	}

	public void validateDescritionOfPolitics(String description) throws LeaderValidateException {
		if (description.isEmpty()) {
			throw new LeaderValidateException(LeaderValidateError.INVALID_DESCRIPTION);
		}
	}

	public void validateDescriptionOfEducation(String description) throws LeaderValidateException {
		if (description.isEmpty()) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_DESCRIPTION);
		}
	}

	public void validateDescriptionOfFamily(String description) throws LeaderValidateException {
		if (description.isEmpty()) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_DESCRIPTION);
		}
	}

	public void validateDescriptionOfIncome(String description) throws LeaderValidateException {
		if (description.isEmpty()) {
			throw new LeaderValidateException(LeaderValidateError.INVALID_DESCRIPTION);
		}
	}
	
	/**
	 * validate image url
	 * @param url
	 * @return
	 * @throws LeaderValidateException
	 */

	public boolean validateUrl(String url) throws LeaderValidateException {
		if (url == null || !url.matches("^(https?://)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(\\/.*)?$")) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_URL);

		}
		return true;
	}
}
