package com.fssa.politifact.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.model.Leaders;
import com.fssa.politifact.model.Position;

public class LeaderValidatorTest {

	private LeaderValidator leaderValidator;

	@BeforeEach
	void setUp() {
		leaderValidator = new LeaderValidator();
	} 
	
	@Test
	void testValiLeader() throws LeaderValidateException {
		
		Leaders validLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 1,
				"Birth Description", "Education Description", "Work Experience Description", "Politics Description",
				"Family Description", "Income Description", "https://www.example.com/image.jpg", 2);

		Assertions.assertTrue(leaderValidator.validate(validLeader));

		
	}

	@Test
	void testNullLeader() {
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(null));

		Assertions.assertEquals(LeaderValidateError.INVALID_LEADER_NULL, exception.getMessage());
	}

	@Test
	void testInvalidName() {
		Leaders invalidLeader = new Leaders(null, Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 1,
				"Birth Description", "Education Description", "Work Experience Description", "Politics Description",
				"Family Description", "Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_NAME, exception.getMessage());
	}
	@Test
	void testInvalidNameLength() {
		Leaders invalidLeader = new Leaders("b", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 1,
				"Birth Description", "Education Description", "Work Experience Description", "Politics Description",
				"Family Description", "Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_NAME, exception.getMessage());
	}
	@Test
	void testInvalidNameEmpty() {
		Leaders invalidLeader = new Leaders("", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 1,
				"Birth Description", "Education Description", "Work Experience Description", "Politics Description",
				"Family Description", "Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_NAME, exception.getMessage());
	}
	@Test
	void testInvalidNamePattern() {
		Leaders invalidLeader = new Leaders("23456", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 1,
				"Birth Description", "Education Description", "Work Experience Description", "Politics Description",
				"Family Description", "Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_NAME, exception.getMessage());
	}

	@Test
	void testInvalidPosition() {
		Leaders invalidLeader = new Leaders("balaji", null, 1, 5.5, "Politician", 1, "Birth Description",
				"Education Description", "Work Experience Description", "Politics Description", "Family Description",
				"Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_POSITION, exception.getMessage());
	}

	@Test
	void testInvalidPartyId() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, -1, 5.5, "Politician", 1,
				"Birth Description", "Education Description", "Work Experience Description", "Politics Description",
				"Family Description", "Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_PARTY_ID, exception.getMessage());
	}

	@Test
	void testInvalidExperience() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 0, "Politician", 1,
				"Birth Description", "Education Description", "Work Experience Description", "Politics Description",
				"Family Description", "Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_EXPERIENCE, exception.getMessage());
	}

	@Test
	void testInvalidOccupation() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, null, 1, "Birth Description",
				"Education Description", "Work Experience Description", "Politics Description", "Family Description",
				"Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_OCCUPATION, exception.getMessage());
	}
	
	@Test
	void testInvalidOccupationEmpty() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "", 1, "Birth Description",
				"Education Description", "Work Experience Description", "Politics Description", "Family Description",
				"Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_OCCUPATION, exception.getMessage());
	}
	
	@Test
	void testInvalidOccupationLength() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "b", 1, "Birth Description",
				"Education Description", "Work Experience Description", "Politics Description", "Family Description",
				"Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_OCCUPATION, exception.getMessage());
	}
	
	@Test
	void testInvalidOccupationPattern() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "12345678", 1, "Birth Description",
				"Education Description", "Work Experience Description", "Politics Description", "Family Description",
				"Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_OCCUPATION, exception.getMessage());
	}

	@Test
	void testInvalidConstituencyId() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 0,
				"Birth Description", "Education Description", "Work Experience Description", "Politics Description",
				"Family Description", "Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_CONSTITUENCY_NUMBER, exception.getMessage());
	}

	@Test
	void testInvalidDescriptionOfBirth() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 1, null,
				"Education Description", "Work Experience Description", "Politics Description", "Family Description",
				"Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());
	}
	
	@Test
	void testInvalidDescriptionOfBirthEmpty() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 1, "",
				"Education Description", "Work Experience Description", "Politics Description", "Family Description",
				"Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());
	}

	@Test
	void testInvalidDescriptionOfEducation() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 1,
				"Birth Description", "", "Work Experience Description", "Politics Description", "Family Description",
				"Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());
	}

	@Test
	void testInvalidDescriptionOfPastWorkExperience() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 1,
				"Birth Description", "Education Description", "", "Politics Description", "Family Description",
				"Income Description", "https://www.example.com/image.jpg", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());
	}

	@Test
	void testInvalidDescritionOfpolitics() {
		
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validateDescritionOfPolitics(""));

		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());
	}

	@Test
	void testInvalidDescriptionOffamily() {

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validateDescriptionOfFamily(""));

		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());
	}

	@Test
	void testInvalidDescriptionOfIncome() {
       
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validateDescriptionOfIncome(""));

		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());
	}

	@Test
	void testInvalidUrl() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 1,
				"Birth Description", "Education Description", "Work Experience Description", "Politics Description",
				"Family Description", "Income Description", "234567890-", 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_URL, exception.getMessage());
	}

	@Test
	void testInvalidUrlnull() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 1,
				"Birth Description", "Education Description", "Work Experience Description", "Politics Description",
				"Family Description", "Income Description", null, 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_URL, exception.getMessage());
	}

	@Test 
	void testInvalidAffidavitId() {
		Leaders invalidLeader = new Leaders("balaji", Position.COUNCIL_MINISTER, 1, 5.5, "Politician", 1,
				"Birth Description", "Education Description", "Work Experience Description", "Politics Description",
				"Family Description", "Income Description", "https://www.example.com/image.jpg", -1);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> leaderValidator.validate(invalidLeader));

		Assertions.assertEquals(LeaderValidateError.INVALID_AFFIDAVIT_ID, exception.getMessage());
	}
}
