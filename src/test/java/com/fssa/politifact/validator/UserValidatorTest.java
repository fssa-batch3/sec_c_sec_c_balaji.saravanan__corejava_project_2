package com.fssa.politifact.validator;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.User;

public class UserValidatorTest {

    private UserValidator userValidator;

    @BeforeEach
    public void setUp() { 
    	
        userValidator = new UserValidator();
    }

    @Test
    public void testValidUser() throws LeaderValidateException {
        User user = new User();
        user.setEmailId("balajis@gmail.com");
        user.setUserName("JohnDoe");
        user.setPassword("password123");
        user.setMobileNo("1234567890");

        assertTrue(userValidator.validate(user));
    }

    @Test
    public void testInvalidUser() throws LeaderValidateException {
        User user = null;
        assertThrows(LeaderValidateException.class, () ->userValidator.validate(user));
    }

    @Test
    public void testValidEmail() throws LeaderValidateException {
        String email = "balajis@gmail.com";
        assertTrue(userValidator.validateEmail(email));
    }

    @Test
    public void testInvalidEmail() throws LeaderValidateException {
        String email = "invalid-email";
        
        assertThrows(LeaderValidateException.class, () -> userValidator.validateEmail(email));
    }

    @Test
    public void testValidUserName() throws LeaderValidateException {
        String userName = "balajis";
        assertTrue(userValidator.validateUserName(userName));
    }

    @Test
    public void testInvalidUserName() throws LeaderValidateException {
        String userName = "balaji345678";
        assertThrows(LeaderValidateException.class, () -> userValidator.validateUserName(userName));
    }
 
    @Test
    public void testValidPassword() throws LeaderValidateException {
        String password = "password123";
        assertTrue(userValidator.validatePassword(password));
    }

    @Test
    public void testInvalidPassword() throws LeaderValidateException {
        String password = "pass";
        assertThrows(LeaderValidateException.class, () ->userValidator.validatePassword(password));
    }

    @Test
    public void testInValidMobileNo() throws LeaderValidateException {
        String mobileNo = "123456tytdf";
        assertThrows(LeaderValidateException.class, () ->userValidator.validateMobileNo(mobileNo));
    }

    @Test
    public void testInvalidMobileNo() throws LeaderValidateException {
        String mobileNo = "invalid-mobile";
        assertThrows(LeaderValidateException.class, () -> userValidator.validateMobileNo(mobileNo));
    }
}
