package com.fssa.politifact.validator;
/*
 *  this page for custom exception for my project 
 * 
 * 
 * */
public class LeaderValidateException extends Exception {

	private static final long serialVersionUID= -8105491977357554060L;
	
	public LeaderValidateException(String msg) {
		
		super(msg);
	}
	
	public LeaderValidateException(Throwable te) {
		super(te);
	}
	
	public LeaderValidateException(String msg, Throwable te) {
		
		super(msg, te);
	}
	
}

