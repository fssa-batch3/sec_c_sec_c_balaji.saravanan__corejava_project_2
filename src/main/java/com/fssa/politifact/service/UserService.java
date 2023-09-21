package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import com.fssa.politifact.dao.UserDao;
import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.User;
import com.fssa.politifact.validator.LeaderValidateError;
import com.fssa.politifact.validator.UserValidator;

public class UserService {
	
	private UserValidator userValidator;
	private UserDao userDao;
	
	public UserService(UserValidator userValidator, UserDao userDao) {
		
		this.userValidator= userValidator;
		this.userDao= userDao;
		
	}
	
	public boolean addUser(User user) throws DaoException, SQLException, LeaderValidateException {

		if (user == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		if (this.userValidator.validate(user)) {

			return this.userDao.userRegistration(user);
		} else {

			return false;

		}

	}
	
	public boolean userStatus(String email) throws LeaderValidateException, DaoException, SQLException {
		if (email == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		if (this.userValidator.validateEmail(email)) {

			return this.userDao.emailExists(email);
			
		} else {

			return false;

		}
		
	}
	
	public boolean userLogin(String emailId, String password) throws  SQLException, DaoException {
		
	    if (userDao.emailExists(emailId)) {
	    	
		    return userDao.userLogin(emailId, password);
		    
		} else {
		    return false;
		}
	    
	}
	
	public List<User> getUser(String emailId) throws  SQLException, DaoException { 
			
	    return userDao.getUserByEmail(emailId);
	    
	}
	
	
	public boolean updateUser(User user) throws DaoException, SQLException, LeaderValidateException {

		if (user == null) {

			throw new LeaderValidateException(LeaderValidateError.INVALID_OBJECT);

		}

		if (this.userValidator.validate(user)) {

			return this.userDao.updateUserProfile(user);
		} else {

			return false;

		}

	}

}
