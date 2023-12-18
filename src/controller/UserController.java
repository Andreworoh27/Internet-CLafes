package controller;

import java.util.List;

import models.User;
import view.Page;

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
		if (u.getUserDataById(userId) == null)
			return "User must be chosen";
		if (!newRole.equals("Admin") && !newRole.equals("Customer") && !newRole.equals("Operator")
				&& !newRole.equals("Computer Technician"))
			return "New role must be be either Admin, Customer, Operator, or Computer Technician";

		u.changeUserRole(userId, newRole);
		return "Successfully changed user role";
	}

	public String login(String username, String password) {
		if (u.getUserDataByUsername(username) == null)
			return "Username doesn't exist in database";
		
		User user = getUserData(username, password);
		if (user == null)
			return "Invalid credentials";
		
		Page.user = user;
		return "Successfully logged in";
	}

	public String register(String username, String password, String confirmPassword, Integer age) {
		if (username.isEmpty())
			return "Username can't be empty";
		if (u.getUserDataByUsername(username) != null)
			return "Username must be unique";
		if (username.length() < 7)
			return "Username must contain at least 7 characters";
		if (password.isEmpty())
			return "Password can't be empty";
		if (!checkAlphanumeric(password))
			return "Password must contain alpha numeric characters";
		if (password.length() < 6)
			return "Password must contain at least 6 characters";
		if (confirmPassword.isEmpty())
			return "Confirm password can't be empty";
		if (!password.equals(confirmPassword))
			return "Confirm password and password doesn't match";
		if (age < 13 || age > 65)
			return "Age must be between 13 and 65";

		addNewUser(username, password, age);
		return "Successfully add a new user";
	}

	public List<User> getAllStaff() {
		return u.getAllStaff();
	}

}
