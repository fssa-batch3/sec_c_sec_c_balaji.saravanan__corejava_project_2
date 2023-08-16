package com.fssa.politifact.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.enums.Position;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Leader;


/**
 * 
 * @author BalajiSaravanan
 *
 * this is leader validator j unit test casses.
 */
class LeaderValidatorTest {

	private LeaderValidator validator;
	private Leader leader;

	/**
	 * this is for instanc create.
	 */
	@BeforeEach
	public void setUp() {
		validator = new LeaderValidator(); 
		leader = new Leader();
	}
	
	/**
	 * test valide leader.
	 * @throws LeaderValidateException
	 */

	@Test
	void testValidLeader() throws LeaderValidateException {
		Leader leader = new Leader();
		leader.setName("balaji");
		leader.setPosition("CHIEF_MINISTER");
		leader.setPartyName("balajis");
		leader.setExperience(5.0);
		leader.setOccupation("Politician");
		leader.setCounstuencyName("chennai");
		leader.setDescriptionOfBirth("Born on some date");
		leader.setDescriptionOfEducation("Bachelor's in Politics");
		leader.setDescriptionOfPastWorkExperience("Served as a local council member");
		leader.setDescritionOfpolitics("Advocates for environmental policies");
		leader.setDescriptionOffamily("Married with two children");
		leader.setDescriptionOfIncome("Income from political work");
		leader.setImageUrl("https://example.com/image.jpg");

		assertTrue(validator.validate(leader));
	}
	
	/**
	 * test null leader.
	 */

	@Test
	void testNullLeader() {

		LeaderValidateException exception = assertThrows(LeaderValidateException.class, () -> validator.validate(null));

		Assertions.assertEquals(LeaderValidateError.INVALID_LEADER_NULL, exception.getMessage());
	}
	
	/**
	 * test invalide name.
	 */

	@Test
	void testInvalidName() {

		leader.setName(null);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validate(leader));

		Assertions.assertEquals(LeaderValidateError.INVALID_NAME, exception.getMessage());
	}

	@Test
	void testInvalidNameLength() {
		leader.setName("b");

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validate(leader));

		Assertions.assertEquals(LeaderValidateError.INVALID_NAME, exception.getMessage());
	}

	@Test
	void testInvalidNameEmpty() {
		leader.setName("");
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validate(leader));

		Assertions.assertEquals(LeaderValidateError.INVALID_NAME, exception.getMessage());
	}

	@Test
	void testInvalidNamePattern() {
		leader.setName("b2hack");
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validate(leader));

		Assertions.assertEquals(LeaderValidateError.INVALID_NAME, exception.getMessage());
	}
	
	/**
	 * test postion
	 */

	@Test
	void testInvalidPosition() {
		assertThrows(LeaderValidateException.class, () -> validator.validatePosition(null));

	}
	
	/**
	 * test party name.
	 */
	
	@Test
	void testInvalidPartyName() {
		assertThrows(LeaderValidateException.class, () -> validator.validatePartyName(""));
		assertThrows(LeaderValidateException.class, () -> validator.validatePartyName(null));
	}

	/**
	 * test experience
	 */
	@Test
	void testInvalidExperience() {
		assertThrows(LeaderValidateException.class, () -> validator.validateExperience(-1));
	}

	@Test
	void testInvalidOccupation() {
		assertThrows(LeaderValidateException.class, () -> validator.validateOccupation(null));
		assertThrows(LeaderValidateException.class, () -> validator.validateOccupation("A")); // Invalid occupation
		assertThrows(LeaderValidateException.class, () -> validator.validateOccupation("")); 																					
		assertThrows(LeaderValidateException.class, () -> validator.validateOccupation("123")); // Invalid occupation
																								// containing numbers
	}

	@Test
	void testInvalidConstituencyName() {
		assertThrows(LeaderValidateException.class, () -> validator.validateConstituencyName(""));
		assertThrows(LeaderValidateException.class, () -> validator.validateConstituencyName(null));
	}

	@Test
	void testInvalidDescriptionOfBirth() {
		assertThrows(LeaderValidateException.class, () -> validator.validateDescriptionOfBirth(null));
		assertThrows(LeaderValidateException.class, () -> validator.validateDescriptionOfBirth(""));
	}

	@Test
	void testInvalidDescriptionOfPastWorkExperience() {
		assertThrows(LeaderValidateException.class, () -> validator.validateDescriptionOfPastWorkExperience(""));
	}

	@Test
	void testInvalidDescritionOfPolitics() {
		assertThrows(LeaderValidateException.class, () -> validator.validateDescritionOfPolitics(""));
	}

	@Test
	void testInvalidDescriptionOfEducation() {
		assertThrows(LeaderValidateException.class, () -> validator.validateDescriptionOfEducation(""));
	}

	@Test
	void testInvalidDescriptionOfFamily() {
		assertThrows(LeaderValidateException.class, () -> validator.validateDescriptionOfFamily(""));
	}

	@Test
	void testInvalidDescriptionOfIncome() {
		assertThrows(LeaderValidateException.class, () -> validator.validateDescriptionOfIncome(""));
	}

	@Test
	void testValidUrl() throws LeaderValidateException {
		assertTrue(validator.validateUrl("https://example.com"));
	}

	@Test
	void testInvalidUrl() {

		assertThrows(LeaderValidateException.class, () -> validator.validateUrl("invalid_url"));
		assertThrows(LeaderValidateException.class, () -> validator.validateUrl(null));
		
	}
}
