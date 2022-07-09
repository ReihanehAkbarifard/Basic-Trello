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
                                    "Please choose an option :\n1. WorkSpaces\n2. See Profile\n3. Log Out",
                                    "Home page", JOptionPane.QUESTION_MESSAGE));
                            switch (chosenOption) {
                                case 1:
                                    switch (Integer.parseInt(JOptionPane.showInputDialog(null, "Please choose " +
                                            "an option :\n1. create all my work space\n2. see all my work space\n4. back"))){
                                        case 1:
                                            currentUser.createWorkSpace();
                                            break;
                                        case 2:
                                            currentUser.seeWorkSpaces();
                                            break;
                                        case 3:
                                            break;
                                    }
                                    break;
                                case 2:
                                    switch (Integer.parseInt(JOptionPane.showInputDialog(null, "Please choose " +
                                            "an option :\n1. Show all my detail\n2. Edit Profile\n3. Delete Profile\n4. back"))){
                                        case 1:
                                            currentUser.seeProfile();
                                            break;
                                        case 2:
                                            currentUser.editProfile();
                                            break;
                                        case 3:
                                            currentUser.deleteProfile();
                                            isInHomePage = false;
                                            break;
                                        case 4:
                                            break;
                                    }
                                    break;
                                case 3:
                                    isInHomePage = false;
                                    break;


                            }

                        }
                    }
                    break;
                case 2:
                    User.signUp();
                    break;
                case 3:
                    isAppRunning = false;
                    break;
            }

        }
    }
}