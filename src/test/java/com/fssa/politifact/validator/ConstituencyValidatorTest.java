package com.fssa.politifact.validator;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.enums.ElectionTypes;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Constituency;

/**
 * 
 * @author BalajiSaravanan
 * 
 * send the value of constituency validator.
 *
 */
class ConstituencyValidatorTest {

	private ConstituencyValidator constituencyValidator;
	
	/**
	 * for each to execute all method rum.
	 */

	@BeforeEach
	public void setUp() {

		constituencyValidator = new ConstituencyValidator();
	}
	
	/**
	 * test validate constituency.
	 * @throws LeaderValidateException
	 */

	@Test
	void testValidConstituency() throws LeaderValidateException {
		Constituency constituency = new Constituency("villupuram", "villupuram", 105,
				ElectionTypes.valueOf("GENERAL_ELECTION"));
		Assertions.assertTrue(constituencyValidator.validate(constituency));
	}
	
	/**
	 * test constituency name.
	 */

	@Test
	void testInvalidConstituencyNames() {
		String[] invalidNames = { "", null, "34567", "b" };

		for (String name : invalidNames) {
			assertThrows(LeaderValidateException.class, () -> constituencyValidator.validateConstituencyName(name));
		}
	}

	/**
	 * test district name.
	 */
	@Test
	void testInvalidDistrictNames() {
		String[] invalidNames = { "", null, "34567", "b" };

		for (String name : invalidNames) {

			assertThrows(LeaderValidateException.class, () -> constituencyValidator.validateDistrictName(name));
		}
	}
	
	/**
	 * test constituency number.
	 */

	@Test
	void testInvalidConstituencyNumber() {
		Constituency constituency = new Constituency("villupuram", "villupuram", 0,
				ElectionTypes.valueOf("GENERAL_ELECTION"));

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> constituencyValidator.validate(constituency));
		Assertions.assertEquals(LeaderValidateError.INVALID_CONSTITUENCY_NUMBER, exception.getMessage());
	}
	
	/**
	 * test election type id.
	 */

	@Test
	void testInvlaidElectionId() {
		Constituency constituency = new Constituency("villupuram", "villupuram", 1, null);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> constituencyValidator.validate(constituency));
		Assertions.assertEquals(LeaderValidateError.INVALID_ELECTION_ID, exception.getMessage());
	}
}
