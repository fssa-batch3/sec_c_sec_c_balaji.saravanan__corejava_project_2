package com.fssa.politifact.validator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.User;

public class UserValidatorTest {

    private UserValidator userValidator;

    @Before
    public void setUp() {
        userValidator = new UserValidator();
    }

    @Test
    public void testValidUser() throws LeaderValidateException {
        User user = new User();
        user.setEmailId("test@example.com");
        user.setUserName("JohnDoe");
        user.setPassword("password123");
        user.setMobileNo("1234567890");

        assertTrue(userValidator.validate(user));
    }

    @Test
    public void testInvalidUser() throws LeaderValidateException {
        User user = null;
        userValidator.validate(user);
    }

    @Test
    public void testValidEmail() throws LeaderValidateException {
        String email = "test@example.com";
        assertTrue(userValidator.validateEmail(email));
    }

    @Test
    public void testInvalidEmail() throws LeaderValidateException {
        String email = "invalid-email";
        
        userValidator.validateEmail(email);
    }

    @Test
    public void testValidUserName() throws LeaderValidateException {
        String userName = "balajis";
        userValidator.validateUserName(userName);
    }

    @Test
    public void testInvalidUserName() throws LeaderValidateException {
        String userName = "balaji345678";
        userValidator.validateUserName(userName);
    }

    @Test
    public void testValidPassword() throws LeaderValidateException {
        String password = "password123";
        userValidator.validatePassword(password);
    }

    @Test
    public void testInvalidPassword() throws LeaderValidateException {
        String password = "pass";
        userValidator.validatePassword(password);
    }

    @Test
    public void testValidMobileNo() throws LeaderValidateException {
        String mobileNo = "1234567890";
        userValidator.validateMobileNo(mobileNo);
    }

    @Test
    public void testInvalidMobileNo() throws LeaderValidateException {
        String mobileNo = "invalid-mobile";
        userValidator.validateMobileNo(mobileNo);
    }
}
