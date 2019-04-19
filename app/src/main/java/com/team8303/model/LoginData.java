package com.team8303.model;



import java.util.ArrayList;
import java.util.Arrays;


/**
 * creates and updates users when they login
 *
 * */
public class LoginData {

    private static ArrayList<User> userInfo = new ArrayList<>();
    private static final User first = new User("name", "user","pass",UserType.USER,"", "", 0, "");


    //private Context context; WARNING!!!! Never store Contexts. They were not meant to be stored.
    // Leave this line commented.

    private LoginData() {
    }

    /**
     * updates the array of users, userInfo, when the database is altered
     *
     * */
    /*public static void updateUserInfo() {
        //UserDatabase db = WelcomeActivity.getDb();
        userInfo.addAll(Arrays.asList(db.userDao().loadAllUsers()));
        if (!userInfo.contains(first)) {
            userInfo.add(first);
            db.userDao().insertUsers(first);
        }
    }*/

    /**
     * searches for a user with the username
     *
     * @param name the name you're searching for
     * @return whether or not it exists in userInfo
     * */
    public static boolean findUser(String name) {
        userInfo.add(first);
        int length = userInfo.size();
        for (int i = 0; i < length; i++) {
            User x = userInfo.get(i);
            String u = x.getUsername();
            if (u.equals(name)) {
                return true;
            }
        }
        return false;
    }


    /**
     * gets a user's password
     *
     * @param name the username whose password you need
     * @return whether or not the password is a match
     * */
    public static Boolean comparePass(String name, String pass) {
        boolean match = false;
        for (User user : userInfo) {
            String u = user.getUsername();
            if (u.equals(name)) {
                if (pass.equals(user.getPassword())) {
                    match = true;
                }
            }
        }
        return match;
    }

    /**
     * finds a user in the array of users
     *
     * @param name the username of the user you're looking for
     * @return the user you're looking for
     * */
    public static User getUser(String name) {
        User ourUser = new User();
        int length = userInfo.size();
        for (int i = 0; i < length; i++) {
            User x = userInfo.get(i);
            String u = x.getUsername();
            if (u.equals(name)) {
                ourUser = userInfo.get(i);
            }
        }
        return ourUser;
    }

    public static void addUser(String name, String user, String pass, UserType type, String number, String email) {
        User newser = new User(name,user,pass,type,number, email, 0, ""); //I added active count attribute and date_registered since they were in account profile screeen.
        userInfo.add(newser);
        /*I've been storing registered users in this userInfo ArrayList, which isn't linked
        to the database, but is easier to work with. I can't write to db, because doing so
        requires using an interface method call on a null object, and I can't write to the
        UserDatabase itself because of a catch-22 wherein the method to do it must be
        simultaneously static and non-static.
        */
    }

    /**
     * provides the list of users
     *
     * @return the list of users
     * */
    public static ArrayList<User> getUserInfo() {
        return userInfo;
    }

    /**
     * instantiates the list of users
     *
     * @param users a list of users
     * */
    public static void setUserInfo(ArrayList<User> users) {
        userInfo = users;
    }

    /*
    static LoginData getInstance() {
        return ourInstance;
    } */

    /*
    public String getUser() {
        return myUsername;
    }
    */
}
