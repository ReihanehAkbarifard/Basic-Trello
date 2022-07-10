import javax.swing.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        JDialog.setDefaultLookAndFeelDecorated(true);
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
                                            "an option :\n1. create new workspace\n2. see all my work space\n" +
                                            "3. search for public work spaces\n4. back"))){
                                        case 1:
                                            currentUser.createWorkSpace();
                                            break;
                                        case 2:
                                            boolean isInAllWorkSpaces = true;
                                            while (isInAllWorkSpaces){
                                            int index = currentUser.seeWorkSpaces();
                                                switch (index) {
                                                    case 0:
                                                        isInAllWorkSpaces = false;
                                                        break;
                                                    default:
                                                        WorkSpace workSpace = currentUser.getMyWorkSpaces().get(index - 1);
                                                        boolean isInWorkSpaces = true;
                                                        while (isInWorkSpaces){
                                                            switch (Integer.parseInt(JOptionPane.showInputDialog(null,
                                                                    "1. Show details\n2. add boards\n3. show boards\n" +
                                                                            "4. add members\n5. Back"))){
                                                                case 1:
                                                                    currentUser.showDetailsOfWorkspaces(workSpace);
                                                                    break;
                                                                case 2:
                                                                    currentUser.addBoards(workSpace);
                                                                    break;
                                                                case 3:
                                                                    currentUser.showBoards(workSpace);
                                                                    break;
                                                                case 4:
                                                                    currentUser.addMembersToWorkSpace(workSpace);
                                                                    break;
                                                                case 5:
                                                                    isInWorkSpaces = false;
                                                                    break;
                                                            }
                                                        }
                                                }
                                            }

                                            break;
                                        case 3:
                                            currentUser.searchForWorkSpaces();
                                            break;
                                        case 4:
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