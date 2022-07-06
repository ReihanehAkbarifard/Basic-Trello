import javax.swing.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        JOptionPane.showMessageDialog(null, "--- Welcome To Mini Trello ---",
                "Welcome", JOptionPane.INFORMATION_MESSAGE);

        boolean isAppRunning = true;
        while (isAppRunning) {
            int chosenOption = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Please choose an option :\n 1. Log-In\n 2. Sign-Up\n 3. Exit", "Log-In/Sign-Up Page"
                    , JOptionPane.QUESTION_MESSAGE));
            switch (chosenOption) {
                case 1:
                    User currentUser = User.logIn();
                    boolean isInHomePage = true;
                    while (isInHomePage) {
                        while (isInHomePage) {
                            chosenOption = Integer.parseInt(JOptionPane.showInputDialog(null,
                                    "Please choose an option :\n1.WorkSpaces\n2. Edit Profile\n 3.Log Out",
                                    "Home page", JOptionPane.QUESTION_MESSAGE));
                            switch (chosenOption) {
                                case 1:
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    isInHomePage = false;
                                    break;


                            }

                        }
                    }
            }

        }
    }
}