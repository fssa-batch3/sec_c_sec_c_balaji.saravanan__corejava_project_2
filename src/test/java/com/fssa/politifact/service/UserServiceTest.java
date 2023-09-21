package com.fssa.politifact.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.politifact.dao.UserDao;
import com.fssa.politifact.exceptions.DaoException;
import com.fssa.politifact.exceptions.LeaderValidateException;
import com.fssa.politifact.model.User;
import com.fssa.politifact.util.Logger;
import com.fssa.politifact.validator.UserValidator;

public class UserServiceTest {
	
	Logger logger= new Logger();
	
	
	@Test
	 void testAddUser() throws LeaderValidateException, SQLException, DaoException {

		User user = getUser(); 

		UserService userService = getUserService();

		Assertions.assertTrue(userService.addUser(user));

	}
	
	/**
	 * this is affidavit constuctor.
	 * @return
	 */

	public User getUser() { 

		User user = new User();
		user.setEmailId("balajis@gmail.com");
		user.setUserName("balajis");
		user.setPassword("balajis@55");
		user.setMobileNo("1234567890"); 
		user.setAge(19);
		user.setOccupation("politician");
		user.setGender("MALE");
		return user;
	}
	
	/**
	 * this is service  constuctor.
	 * @return
	 */ 

	public UserService getUserService() {

		UserValidator userValidator = new UserValidator();

		UserDao userDao = UserDao.getObj();

		UserService UserService = new UserService(userValidator,userDao);

		return UserService;
	}
	
	
	@Test
	 void testStatus() throws LeaderValidateException, SQLException, DaoException {


		UserService userService = getUserService();

		Assertions.assertTrue(userService.userStatus("balajis@gmail.com"));

	} 
	
	@Test
	  void userDetails() throws LeaderValidateException, SQLException, DaoException {


		UserService userService = getUserService();

	    List<User> userListt= userService.getUser("hemanathm4@gmail.com");
	    
	    for(User e: userListt) {
	    	
	    	logger.info(e);
	    }

	}
	
	@Test
	  void userUpdateDetails() throws LeaderValidateException, SQLException, DaoException {


		UserService userService = getUserService();
	    
	    Assertions.assertTrue(userService.updateUser(getUser()));
	   

	}
	

}
