import javax.swing.*;
import java.sql.*;

public class User {
    private String fullName;
    private String userID;
    private String passWord;
    private String email;


    public User(String fullName, String userID, String passWord, String email) {
        this.fullName = fullName;
        this.userID = userID;
        this.passWord = passWord;
        this.email = email;
    }

    public static void signUp() throws SQLException {
        String userName =null;
        String fullName = null;
        String passWord = null;
        String email = null;
        while (true) {
            fullName = JOptionPane.showInputDialog(null, "Please Enter Your FullName :",
                    "Sign-Up page", JOptionPane.QUESTION_MESSAGE);
            if ((fullName != null) && (!(fullName.length() == 0)) && (fullName.matches("^[a-zA-Z]*$"))) {
                break;
            } else {
                JOptionPane.showInputDialog(null, "Not accepted ! Please try another one",
                        "Sign-Up page", JOptionPane.QUESTION_MESSAGE);
            }
        }
        while (true) {
            userName = JOptionPane.showInputDialog(null, "Please Enter Your username :",
                    "Sign-Up page", JOptionPane.QUESTION_MESSAGE);
            if ((userName != null) && ((userName.length() > 4))) {
                break;
            } else {
                JOptionPane.showInputDialog(null, "Not accepted ! Please try another one",
                        "Sign-Up page", JOptionPane.QUESTION_MESSAGE);
            }
        }
        while (true){
            passWord = JOptionPane.showInputDialog(null, "Please Enter your passWord :",
                    "Sign-Up Page", JOptionPane.QUESTION_MESSAGE);
            if ((passWord != null) && ((passWord.length() > 4))) {
                break;
            } else {
                JOptionPane.showInputDialog(null, "Not accepted ! Please try another one",
                        "Sign-Up page", JOptionPane.QUESTION_MESSAGE);
            }
        }
        while (true){
            email = JOptionPane.showInputDialog(null, "Please Enter Your Email :",
                    "Sign-Up page", JOptionPane.QUESTION_MESSAGE);
            if ((email != null) && (!(email.length() == 0)) && (email.matches("^[A-Za-z0-9+_.-]+@(.+)$"))) {
                break;
            } else {
                JOptionPane.showInputDialog(null, "Not accepted ! Please try another one",
                        "Sign-Up page", JOptionPane.QUESTION_MESSAGE);
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
        Boolean isFound = false;
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
                        resultSet.getString("userID"), resultSet.getString("password") ,
                        resultSet.getString("email"));
                return user;

            }
            else {
                JOptionPane.showMessageDialog(null, "User Not Found"
                        ,"Log-Up page",JOptionPane.INFORMATION_MESSAGE);
            }
        }while (true);

    }


}