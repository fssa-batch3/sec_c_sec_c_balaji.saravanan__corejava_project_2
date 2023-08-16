package com.fssa.politifact.exceptions;

/**
 * 
 * @author BalajiSaravanan
 *
 *
 *this is user defind exception for create for my purpuse.
 */

public class DaoException extends Exception{
	
	/**
	 * receive the message and send for super exception.
	 */

	private static final long serialVersionUID= -8105491977357554060L;
	
	public DaoException(String msg) {
		
		super(msg);
	}

}
