package com.fssa.politifact.model;




/*
 * The User class represents a user details.
 * It contains the following attributes:
 *   above code......
 */

public class User {


    private int userId;
    private String emailId;
    private String userName;
    private String password;
    private String mobileNo;
	private int age;
    private String occupation;
    private String gender;
    
    
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	

	@Override
	public String toString() {
		return "User [userId=" + userId + ", emailId=" + emailId + ", userName=" + userName + ", password=" + password
				+ ", mobileNo=" + mobileNo + ", age=" + age + ", occupation=" + occupation
				+ ", gender=" + gender + "]";
	}
	
	

    
   
}
