package com.team8303.model;


/**
 * creates and sets stuff for a user
 * */
public class User {
    private String name;
    private String username = "";
    private String password;
    private UserType userType;
    private String phoneNumber;
    private String email;
    public int activeCount;
    private String date_registered;

    /**
     * creates a user
     * */
    public User() {
        //Please never use this.
    }

    /**
     * creates a user
     *
     * @param username the users name
     * @param password the users password
     * @param userType admin or user
     * */
    public User(String name, String username, String password, UserType userType,
                String phoneNumber, String email, int activeCount, String date_registered) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.activeCount = activeCount;
        this.date_registered = date_registered;
    }

    public String getDate_registered() {
        return date_registered;
    }

    public void setDate_registered(String date_registered) {
        this.date_registered = date_registered;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return admin or user
     * */
    public UserType getUserType() {
        return userType;
    }

    /**
     * @return user's name
     * */

    public String getUsername() {
        return username;
    }

    /**
     * @return user's password
     * */
    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() { return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() { return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param o the object being compared
     * @return if the same or nah
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        User user = (User) o;

        return username.equals(user.username) && password.equals(user.password);
    }

    /**
     * @return the hashcode
     * */
    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = (31 * result) + password.hashCode();
        return result;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }
}
