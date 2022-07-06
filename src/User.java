import javax.swing.*;
import java.sql.*;

public class User {
    private String fullName;
    private String userID;
    private String passWord;
    private String email;
    private String bio;


    public User(String fullName, String userID, String passWord, String email) {
        this.fullName = fullName;
        this.userID = userID;
        this.passWord = passWord;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }
    public String getUserID() {
        return userID;
    }
    public String getPassWord() {
        return passWord;
    }
    public String getEmail() {
        return email;
    }
    public String getBio() {
        return bio;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    public static void signUp() throws SQLException {
        String userName =null;
        String fullName = null;
        String passWord = null;
        String email = null;
        while (true) {
            fullName = JOptionPane.showInputDialog(null, "Please Enter Your FullName :",
                    "Sign-Up page", JOptionPane.QUESTION_MESSAGE);
            if ((fullName != null) && (!(fullName.length() == 0)) && (fullName.matches("^[A-Za-z\\s]*$"))) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Not accepted ! Please try another one",
                        "Sign-Up page", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        while (true) {
            userName = JOptionPane.showInputDialog(null, "Please Enter Your username :",
                    "Sign-Up page", JOptionPane.QUESTION_MESSAGE);
            if ((userName != null) && ((userName.length() > 4))) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Not accepted ! Please try another one\nYour username must have at least 5 characters ",
                        "Sign-Up page", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        while (true){
            passWord = JOptionPane.showInputDialog(null, "Please Enter your passWord :",
                    "Sign-Up Page", JOptionPane.QUESTION_MESSAGE);
            if ((passWord != null) && ((passWord.length() > 4))) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Not accepted ! Please try another one\nYour username must have at least 5 characters ",
                        "Sign-Up page", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        while (true){
            email = JOptionPane.showInputDialog(null, "Please Enter Your Email :",
                    "Sign-Up page", JOptionPane.QUESTION_MESSAGE);
            if ((email != null) && (!(email.length() == 0)) && (email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Not accepted ! Please try another one",
                        "Sign-Up page", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?" +
                "autoReconnect=true&useSSL=false", "root", "");

        PreparedStatement preparedStatement = connection.prepareStatement("Select * from Users where username = ? " +
                "or email = ?");
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "This User Name or Email has Been" +
                        " Chosen Before", "Sign-Up page", JOptionPane.INFORMATION_MESSAGE);

            } else {
                PreparedStatement preparedStatement2 = connection.prepareStatement("Insert into Users values(?, ?, ?, ?, ?)");
                preparedStatement2.setString(1, userName);
                preparedStatement2.setString(2, passWord);
                preparedStatement2.setString(3, email);
                preparedStatement2.setString(4, fullName);
                preparedStatement2.setString(5, "");
                preparedStatement2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Your Account Was Registered " +
                        "Successfully", "Sign-Up page", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (Exception err) {
            System.out.println("err");
        }

    }
    public static User logIn() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?" +
                "autoReconnect=true&useSSL=false", "root", "");
        do {
            String userName = JOptionPane.showInputDialog(null ,"Please Enter Your username :",
                    "logIn page" , JOptionPane.QUESTION_MESSAGE);
            String passWord = JOptionPane.showInputDialog(null ,"Please Enter your passWord :",
                    "logIn Page" , JOptionPane.QUESTION_MESSAGE);
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from Users where username = ? " +
                    "and password = ?");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, passWord);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Welcome Dear " +
                                resultSet.getString("fullname"), "Sign-Up page",
                        JOptionPane.INFORMATION_MESSAGE);
                User user = new User(resultSet.getString("fullname"),
                        resultSet.getString("username"), resultSet.getString("password") ,
                        resultSet.getString("email"));
                return user;

            }
            else {
                JOptionPane.showMessageDialog(null, "User Not Found"
                        ,"Log-Up page",JOptionPane.INFORMATION_MESSAGE);
            }
        }while (true);

    }
    public void editProfile() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?" +
                "autoReconnect=true&useSSL=false", "root", "");
        int chosenOpt = Integer.parseInt(JOptionPane.showInputDialog(null, "Please Choose an Option Which You" +
                        " Want to edit :\n1. username\n2. password\n3 .bio\n4. back" , "Edit Profile",
                JOptionPane.QUESTION_MESSAGE));
        switch (chosenOpt){
            case 1:
                String newUserName= JOptionPane.showInputDialog(null, "Please Enter your new " +
                        "name", "Edit username", JOptionPane.QUESTION_MESSAGE);
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET username = ? WHERE " +
                        "username = ?");
                preparedStatement.setString(1, newUserName);
                preparedStatement.setString(2, this.getUserID());
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Your username has edited " +
                        "successfully", "Edit username", JOptionPane.INFORMATION_MESSAGE);
                setUserID(newUserName);
                break;
            case 2:
                String newPassword= JOptionPane.showInputDialog(null, "Please Enter your new " +
                        "password", "Edit password", JOptionPane.QUESTION_MESSAGE);
                preparedStatement = connection.prepareStatement("UPDATE Users SET password = ? WHERE " +
                        "password = ?");
                preparedStatement.setString(1, getPassWord());
                preparedStatement.setString(2, newPassword);
                JOptionPane.showMessageDialog(null, "Your password has edited " +
                        "successfully", "Edit password", JOptionPane.INFORMATION_MESSAGE);
                setPassWord(newPassword);
                break;
            case 3:
                String bio= JOptionPane.showInputDialog(null, "Please Enter your new bio "
                        , "Edit bio", JOptionPane.QUESTION_MESSAGE);
                preparedStatement = connection.prepareStatement("UPDATE Users SET bio = ? WHERE " +
                        "username = ?");
                preparedStatement.setString(1, bio);
                preparedStatement.setString(2, getUserID());
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Your bio has edited " +
                        "successfully", "Edit bio", JOptionPane.INFORMATION_MESSAGE);
                setBio(bio);
                break;
            case 4:
                break;

        }

    }
    public void seeProfile() throws SQLException {
        StringBuilder allInformation = new StringBuilder();
        if(getBio() == null){
            bio = " ";
        }
        allInformation.append("user name : " + this.userID + "\n").append("full name : " + this.fullName + "\n")
                .append("email : " + this.email + "\n").append("bio : " + bio + "\n");
        JOptionPane.showMessageDialog(null , allInformation, "See Profile",
                JOptionPane.INFORMATION_MESSAGE);
    }



}