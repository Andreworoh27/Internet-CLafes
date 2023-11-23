package controller;
import java.util.List;

import models.User;

public class UserController {
	
	private static UserController uc = null;
	
	private UserController() {}
	
	public synchronized static UserController getInstance() {
		if (uc == null) uc = new UserController();
		return uc;
	}
	
	public List<User> getAllUserData() {
		return null;
	}
	
	public User getUserData(String username, String password) {
		return null;
	}
	
	public User getUserById(Integer userId) {
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
