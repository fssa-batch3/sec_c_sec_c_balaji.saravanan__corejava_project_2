package com.fssa.politifact.validator;

import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.User;

public class UserValidator {
	 
	// this is page is user validation perform 

    public boolean validate(User user) throws LeaderValidateException {
    	
        if (user == null) {
            throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);
        }
        validateEmail(user.getEmailId());
        validateUserName(user.getUserName());
        validatePassword(user.getPassword());
        validateMobileNo(user.getMobileNo());
        return true;
    }
    
    /**
     * check for email validation
     * @param email
     * @return
     * @throws LeaderValidateException
     */

    public boolean validateEmail(String email) throws LeaderValidateException {
        if (email == null || email.isEmpty() || !email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+[a-zA-Z.-]+$")) {
            throw new LeaderValidateException(LeaderValidateError.INVALID_USER_EMAIL);
        }
        return true;
    }
    
    /**
     * check for user name
     * @param userName
     * @return
     * @throws LeaderValidateException
     */

    public boolean validateUserName(String userName) throws LeaderValidateException {
        if (userName == null || userName.isEmpty() || !userName.matches("[a-zA-Z .]+")) {
            throw new LeaderValidateException(LeaderValidateError.INVALID_NAME);
        }
        return true;
    }
    
    /**
     * check for valdate password
     * @param password
     * @return
     * @throws LeaderValidateException
     */

    public boolean validatePassword(String password) throws LeaderValidateException {
        if (password == null || password.isEmpty() || password.length() < 8) {
            throw new LeaderValidateException(LeaderValidateError.INVALID_PASSWORD);
        }
        return true;
    }
    
    /**
     * check for validate movile No
     * @param mobileNo
     * @throws LeaderValidateException
     */
    public boolean validateMobileNo(String mobileNo) throws LeaderValidateException {
        if (mobileNo == null || !mobileNo.matches("^[0-9]+$")) {
            throw new LeaderValidateException(LeaderValidateError.INVALID_MOBILE_NUMBER);
        }
        
        return true;
    }
}
