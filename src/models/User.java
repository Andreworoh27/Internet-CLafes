package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connection.Connect;

public class User {
	
	private Connect db = Connect.getConnection();
	
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
		String query = "SELECT * FROM User";
		Vector<User> users = new Vector<>();
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("UserID");
				Integer age = rs.getInt("UserAge");
				String name = rs.getString("UserName");
				String password = rs.getString("UserPassword");
				String role = rs.getString("UserRole");
				users.add(new User(id, age, name, password, role));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all user data");
			e.printStackTrace();
		}
		return users;
	}
	
	public User getUserData(String username, String password) {
		String query = "SELECT * FROM User WHERE UserName = ? AND UserPassword = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("UserID");
				Integer age = rs.getInt("UserAge");
				String role = rs.getString("UserRole");
				return new User(id, age, username, password, role);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch user data");
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserDataByUsername(String username) {
		String query = "SELECT * FROM User WHERE UserName = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("UserID");
				Integer age = rs.getInt("UserAge");
				String password = rs.getString("UserPassword");
				String role = rs.getString("UserRole");
				return new User(id, age, username, password, role);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch user data");
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserDataById(Integer userId) {
		String query = "SELECT * FROM User WHERE UserID = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer age = rs.getInt("UserAge");
				String username = rs.getString("UserName");
				String password = rs.getString("UserPassword");
				String role = rs.getString("UserRole");
				return new User(userId, age, username, password, role);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch user data");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> getAllTechnician() {
		String query = "SELECT * FROM User WHERE UserRole = ?";
		Vector<User> technicians = new Vector<>();
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, "Computer Technician");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("UserID");
				Integer age = rs.getInt("UserAge");
				String name = rs.getString("UserName");
				String password = rs.getString("UserPassword");
				String role = rs.getString("UserRole");
				technicians.add(new User(id, age, name, password, role));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch all technician data");
			e.printStackTrace();
		}
		return technicians;
	}
	
	public void addNewUser(String username, String password, Integer age) {
		String query = "INSERT INTO User VALUES (0, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, age);
			ps.setString(4, "Customer");
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to add new user data");
			e.printStackTrace();
		}
	}
	
	public void changeUserRole(Integer userId, String newRole) {
		String query = "UPDATE User SET UserRole = ? WHERE UserID = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, newRole);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to change user role");
			e.printStackTrace();
		}
	}

}
