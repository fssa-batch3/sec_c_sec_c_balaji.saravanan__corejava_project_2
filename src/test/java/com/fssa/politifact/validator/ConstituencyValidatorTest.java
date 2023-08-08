package com.fssa.politifact.validator;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.model.Constituency;

public class ConstituencyValidatorTest {

	private ConstituencyValidator constituencyValidator;

	@BeforeEach
	public void setUp() {
		constituencyValidator = new ConstituencyValidator();
	}

	@Test
	public void testValidConstituency() throws LeaderValidateException {
		Constituency constituency = new Constituency("villupuram", "villupuram", 105, 1);
		Assertions.assertTrue(constituencyValidator.validate(constituency));
	}


	@Test
	public void testInvalidConstituencyName() {
		Constituency constituency = new Constituency( null, "villupuram", 105, 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> constituencyValidator.validate(constituency));
		Assertions.assertEquals(LeaderValidateError.INVALID_CONSTITUENCY_NAME, exception.getMessage());
	}

	@Test
	public void testInvalidConstituencyNameEmpty() {
		Constituency constituency = new Constituency("", "villupuram", 105, 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> constituencyValidator.validate(constituency));
		Assertions.assertEquals(LeaderValidateError.INVALID_CONSTITUENCY_NAME, exception.getMessage());
	}
	@Test
	public void testInvalidConstituencyNameLength() {
		Constituency constituency = new Constituency("v", "villupuram", 105, 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> constituencyValidator.validate(constituency));
		Assertions.assertEquals(LeaderValidateError.INVALID_CONSTITUENCY_NAME, exception.getMessage());
	}
	@Test
	public void testInvalidConstituencyNamePattern() {
		Constituency constituency = new Constituency("2345678", "villupuram", 105, 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> constituencyValidator.validate(constituency));
		Assertions.assertEquals(LeaderValidateError.INVALID_CONSTITUENCY_NAME, exception.getMessage());
	}
	@Test
	public void testInvalidDistrictName() {
		Constituency constituency = new Constituency( "villupuram", null, 105, 3);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> constituencyValidator.validate(constituency));
		Assertions.assertEquals(LeaderValidateError.INVALID_CONSTITUENCY_NAME, exception.getMessage());
	}
	
	@Test
	public void testInvalidDistrictNameLength() {
		Constituency constituency = new Constituency( "villupuram", "v", 105, 3);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> constituencyValidator.validate(constituency));
		Assertions.assertEquals(LeaderValidateError.INVALID_CONSTITUENCY_NAME, exception.getMessage());
	}

	@Test
	public void testInvalidConstituencyNumber() {
		Constituency constituency = new Constituency("villupuram", "villupuram", 0, 2);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> constituencyValidator.validate(constituency));
		Assertions.assertEquals(LeaderValidateError.INVALID_CONSTITUENCY_NUMBER, exception.getMessage());
	}
	
	@Test
	public void testInvalidDistrictNameEmpty() {
		Constituency constituency = new Constituency("villupuram", "", 105, 3);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> constituencyValidator.validate(constituency));
		Assertions.assertEquals(LeaderValidateError.INVALID_CONSTITUENCY_NAME, exception.getMessage());
	}
	
	@Test
	public void testInvalidDistrictNamePattern() {
		Constituency constituency = new Constituency("villupuram", "234567", 105, 3);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> constituencyValidator.validate(constituency));
		Assertions.assertEquals(LeaderValidateError.INVALID_CONSTITUENCY_NAME, exception.getMessage());
	}

	@Test
	public void testInvlaidElectionId() {
		Constituency constituency = new Constituency("villupuram", "villupuram", 1, 0);

		LeaderValidateException exception = assertThrows(LeaderValidateException.class,
				() -> constituencyValidator.validate(constituency));
      Assertions.assertEquals(LeaderValidateError.INVALID_ELECTION_ID, exception.getMessage());
	}
}
