package com.fssa.politifact.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Leader;

/**
 * 
 * @author BalajiSaravanan
 *
 *         this is leader validator j unit test casses.
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
	 * 
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

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validatePosition(null));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_POSITION, exception.getMessage());

	}

	/**
	 * test party name.
	 */

	@Test
	void testInvalidPartyName() {
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validatePartyName(""));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_PARTYNAME, exception.getMessage());

	}

	@Test
	void testInvalidPartyNamenULL() {

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validatePartyName(null));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_PARTYNAME, exception.getMessage());
	}

	/**
	 * test experience
	 */
	@Test
	void testInvalidExperience() {

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateExperience(-1));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_EXPERIENCE, exception.getMessage());
	}

	@Test
	void testInvalidOccupationNull() {
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateOccupation(null));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_OCCUPATION, exception.getMessage());
		// containing numbers
	}

	@Test
	void testInvalidOccupationLength() {

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateOccupation("A")); // Invalid occupation
		// containing numbers
		Assertions.assertEquals(LeaderValidateError.INVALID_OCCUPATION, exception.getMessage());
	}

	@Test
	void testInvalidOccupationEmpty() {

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateOccupation(""));
		// containing numbers
		
		Assertions.assertEquals(LeaderValidateError.INVALID_OCCUPATION, exception.getMessage());
	}

	@Test
	void testInvalidOccupationPattern() {

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateOccupation("123")); // Invalid occupation
		// containing numbers
		
		Assertions.assertEquals(LeaderValidateError.INVALID_OCCUPATION, exception.getMessage());
	}

	@Test
	void testInvalidConstituencyNameEmpty() {
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateConstituencyName(""));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_CONSTITUENCY_NAME, exception.getMessage());

	}

	@Test
	void testInvalidConstituencyNameNull() {

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateConstituencyName(null));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_CONSTITUENCY_NAME, exception.getMessage());
	}

	@Test
	void testInvalidDescriptionOfBirthNull() {
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateDescriptionOfBirth(null));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());

	}

	@Test
	void testInvalidDescriptionOfBirthEmpty() {
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateDescriptionOfBirth(""));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());
	}

	@Test
	void testInvalidDescriptionOfPastWorkExperience() {
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateDescriptionOfPastWorkExperience(""));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());
	}

	@Test
	void testInvalidDescritionOfPolitics() {
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateDescritionOfPolitics(""));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());
	}

	@Test
	void testInvalidDescriptionOfEducation() {
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateDescriptionOfEducation(""));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());
	}

	@Test
	void testInvalidDescriptionOfFamily() {
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateDescriptionOfFamily(""));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());
	}

	@Test
	void testInvalidDescriptionOfIncome() {
		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateDescriptionOfIncome(""));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_DESCRIPTION, exception.getMessage());
	}

	@Test
	void testValidUrl() throws LeaderValidateException {

		Assertions.assertTrue(validator.validateUrl("https://example.com/image.jpg"));
	}

	@Test
	void testInvalidUrl() {

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateUrl("invalid_url"));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_URL, exception.getMessage());

	}

	@Test
	void testInvalidUrlNull() {

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> validator.validateUrl(null));
		
		Assertions.assertEquals(LeaderValidateError.INVALID_URL, exception.getMessage());

	}
}
