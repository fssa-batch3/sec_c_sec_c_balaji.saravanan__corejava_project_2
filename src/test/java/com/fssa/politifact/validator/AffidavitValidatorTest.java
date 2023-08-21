package com.fssa.politifact.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.Affidavit;

/**
 * 
 * @author BalajiSaravanan
 *
 *
 * affidavit validator send the value from here.
 */
 class AffidavitValidatorTest {
	 

	 /**
	  * this private affidavit validator instance create
	  */
	private AffidavitValidator affidavitValidator;
	
	/**
	 * this before each run one method run before.
	 */

	@BeforeEach
	void setUp() {
		affidavitValidator = new AffidavitValidator();
	}
	
	/**
	 * 
	 * @throws LeaderValidateException
	 */

	@Test
	void testValidAffidavit() throws LeaderValidateException {
		Affidavit affidavit = new Affidavit(1, 1001, "https://www.example.com/affidavit1.pdf");
		boolean result = affidavitValidator.validate(affidavit);
		assertEquals(true, result); 
	}

	@Test
	void testNullAffidavit() {
		
		assertThrows(LeaderValidateException.class, () -> affidavitValidator.validate(null));
		
	}

	@Test
	void testInvalidElectionId() {
		Affidavit affidavit = new Affidavit(-1, 1002, "https://www.example.com/affidavit2.pdf");
		
		assertThrows(LeaderValidateException.class, () -> affidavitValidator.validate(affidavit));
	}

	@Test
	void testInvalidLeaderId() {
		
		Affidavit affidavit = new Affidavit(1, 0, "https://www.example.com/affidavit3.pdf");
		
		assertThrows(LeaderValidateException.class, () -> affidavitValidator.validate(affidavit));
	}

	@Test
	void testInvalidAffidavitUrl() {
		
		Affidavit affidavit = new Affidavit(1, 1003, "invalid_url");
		
		assertThrows(LeaderValidateException.class, () -> affidavitValidator.validate(affidavit));
	}
	
	@Test
	void testInvalidAffidavitUrlNull() {
		
		Affidavit affidavit = new Affidavit(1, 1003, null);
		
		assertThrows(LeaderValidateException.class, () -> affidavitValidator.validate(affidavit));
	}
}
