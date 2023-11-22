package models;

public class Customer {
    private String userId;
    private String userName;
    private String userPassword;
    private Integer userAge;

    public Customer() {

    }

    public Customer(String userId, String userName, String userPassword, Integer userAge) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAge = userAge;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
