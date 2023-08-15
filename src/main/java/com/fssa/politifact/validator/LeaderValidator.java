package com.fssa.politifact.validator;

import com.fssa.politifact.enums.Position;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Leader;

public class LeaderValidator {

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

	public boolean validateName(String name) throws LeaderValidateException {

		if (name == null || name.trim().isEmpty() || name.length() <= 2 || !name.matches("[A-Za-z\\s]+")) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_NAME);
		}
		return true;
	}

	public void validatePosition(Position position) throws LeaderValidateException {

		if (position == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_POSITION);
		}
	}

	public void validatePartyName(String partyName) throws LeaderValidateException {

		if (partyName == null || partyName.isEmpty()) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_PARTY_ID);
		}
	}

	public void validateExperience(double experience) throws LeaderValidateException {

		if (experience <= 0) {
			throw new LeaderValidateException(LeaderValidateError.INVALID_EXPERIENCE);
		}
	}

	public void validateOccupation(String occupation) throws LeaderValidateException {

		if (occupation == null || occupation.trim().isEmpty() || occupation.length() < 2
				|| !occupation.matches("[a-zA-Z//s]+")) {
			throw new LeaderValidateException(LeaderValidateError.INVALID_OCCUPATION);
		}
	}

	public void validateConstituencyName(String constituencyName) throws LeaderValidateException {

		if (constituencyName == null || constituencyName.isEmpty()) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_CONSTITUENCY_NUMBER);
		}
	}

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

	public boolean validateUrl(String url) throws LeaderValidateException {
		if (url == null || !url.matches("^(https?://)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(\\/.*)?$")) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_URL);

		}
		return true;
	}
}
