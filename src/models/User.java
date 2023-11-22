package models;

import java.util.List;

public class User {
	
    private Integer userId, userAge;
    private String username, userPassword, userRole;

    public User() {}

	public User(Integer userId, Integer userAge, String username, String userPassword, String userRole) {
		super();
		this.userId = userId;
		this.userAge = userAge;
		this.username = username;
		this.userPassword = userPassword;
		this.userRole = userRole;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public List<User> getAllUserData() {
		return null;
	}
	
	public User getUserData(String username, String password) {
		return null;
	}
	
	public void addNewUser(String username, String password, Integer age) {
		
	}
	
	public void changeUserRole(Integer userId, String newRole) {
		
	}
	
	public List<User> getAllTechnician() {
		return null;
	}

}
