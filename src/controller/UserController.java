package controller;
import java.util.List;

import models.User;

public class UserController {
	
	private User u = new User();
	
	public List<User> getAllUserData() {
		return u.getAllUserData();
	}
	
	public User getUserData(String username, String password) {
		return u.getUserData(username, password);
	}
	
	public User getUserDataById(Integer userId) {
		return u.getUserDataById(userId);
	}
	
	public List<User> getAllTechnician() {
		return u.getAllTechnician();
	}
	
	public Boolean checkAlphanumeric(String str) {
		Integer len = str.length();
		Boolean alpha = false, num = false;
		for (int i = 0; i < len; i++) {
			if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z' || str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
				alpha = true;
			
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
				num = true;
			
			if (alpha && num) 
				return true;
		}
		return false;
	}
	
	public void addNewUser(String username, String password, Integer age) {
		u.addNewUser(username, password, age);
	}
	
	public String changeUserRole(Integer userId, String newRole) {
		String errorMessage = "";
		if (userId == 0)
			errorMessage = "User must be chosen";
		else if (!newRole.equals("Admin") && !newRole.equals("Customer") && !newRole.equals("Operator") && !newRole.equals("Computer Technician"))
			errorMessage = "New role must be be either Admin, Customer, Operator, or Computer Technician";
		
		if (errorMessage.isEmpty()) u.changeUserRole(userId, newRole);
		
		return errorMessage;
	}
	
	public String login(String username, String password) {
		User user = getUserData(username, password);
		if (user == null) return "Invalid credentials";
		return "Successfully logged in";
	}
	
	public String register(String username, String password, String confirmPassword, Integer age) {
		String errorMessage = "";
		if (username.isEmpty()) 
			errorMessage = "Username can't be empty";
		else if (u.getUserDataByUsername(username) != null) 
			errorMessage = "Username must be unique";
		else if (username.length() < 7) 
			errorMessage = "Username must contain at least 7 characters";
		else if (password.isEmpty()) 
			errorMessage = "Password can't be empty";
		else if (!checkAlphanumeric(password)) 
			errorMessage = "Password must contain alpha numeric characters";
		else if (password.length() < 6) 
			errorMessage = "Password must contain at least 6 characters";
		else if (confirmPassword.isEmpty())
			errorMessage = "Confirm password can't be empty";
		else if (!password.equals(confirmPassword))
			errorMessage = "Confirm password and password doesn't match";
		else if (age < 13 || age > 65)
			errorMessage = "Age must be between 13 and 65";
		
		if (errorMessage.isEmpty()) addNewUser(username, password, age);
	
		return errorMessage;
	}

}
