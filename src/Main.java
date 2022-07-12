import javax.swing.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        JDialog.setDefaultLookAndFeelDecorated(true);
        JOptionPane.showMessageDialog(null, "--- Welcome To Mini Trello --- ",
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
                                                                    boolean isInBoards = true;
                                                                    while (isInBoards) {
                                                                        index = currentUser.showBoards(workSpace);
                                                                        switch (index) {
                                                                            case 0:
                                                                                isInBoards = false;
                                                                                break;
                                                                            default:
                                                                                Board board = workSpace.getBoards().get(index - 1);
                                                                                boolean isInBoard = true;
                                                                                while (isInBoard) {
                                                                                    switch (Integer.parseInt(JOptionPane.showInputDialog(null,
                                                                                            "1. show all lists\n2. add new list\n3. archives\n4. Back",
                                                                                            "show all lists", JOptionPane.QUESTION_MESSAGE))) {
                                                                                        case 1:
                                                                                            index = currentUser.showAllLists(board);
                                                                                            switch (index) {
                                                                                                case 0:
                                                                                                    break;
                                                                                                default:
                                                                                                    List list = board.getLists().get(index - 1);
                                                                                                    Boolean isInList = true;
                                                                                                    while (isInList) {
                                                                                                        switch (Integer.parseInt(JOptionPane.showInputDialog(null,
                                                                                                                "Please Choose an option :\n1. show all cards\n" +
                                                                                                                        "2. add new card\n3. Back", "All lists",
                                                                                                                JOptionPane.QUESTION_MESSAGE))) {
                                                                                                            case 1:
                                                                                                                Boolean isInCard = true;
                                                                                                                while (isInCard) {
                                                                                                                    index = currentUser.showAllCards(list);
                                                                                                                    if (index != 0) {
                                                                                                                        Card card = list.getCards().get(index - 1);
                                                                                                                        index = currentUser.showCard(card);
                                                                                                                        switch (index) {
                                                                                                                            case 0:
                                                                                                                                break;
                                                                                                                            case 1:
                                                                                                                                currentUser.editAndAddToCard(card);
                                                                                                                                break;
                                                                                                                            case 2:
                                                                                                                                currentUser.moveCard(board, card);
                                                                                                                                break;
                                                                                                                            case 3:
                                                                                                                                currentUser.archive(workSpace, board, list, card);
                                                                                                                                break;

                                                                                                                        }

                                                                                                                    } else {
                                                                                                                        break;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 2:
                                                                                                                currentUser.addCard(list);
                                                                                                                break;
                                                                                                            case 3:
                                                                                                                isInList = false;
                                                                                                                break;


                                                                                                        }
                                                                                                    }

                                                                                            }
                                                                                            break;
                                                                                        case 2:
                                                                                            currentUser.addLists(board);
                                                                                            break;
                                                                                        case 3:
                                                                                            Boolean inArchive = true;
                                                                                            while (inArchive) {
                                                                                                int optArchive = currentUser.showArchives(board);
                                                                                                if (optArchive == 0){
                                                                                                    inArchive = false;
                                                                                                    break;
                                                                                                }
                                                                                                else {
                                                                                                    int chooseOpt = Integer.parseInt(JOptionPane.showInputDialog(null,
                                                                                                            "Please choose an option :\n0. back\n1. delete\n2. return to list",
                                                                                                            "Archive", JOptionPane.QUESTION_MESSAGE));
                                                                                                    Card cardInArchive = board.getArchives().get(optArchive - 1);
                                                                                                    switch (chooseOpt) {
                                                                                                        case 0:
                                                                                                            break;
                                                                                                        case 1:
                                                                                                            currentUser.deleteCard(cardInArchive, board);
                                                                                                            break;
                                                                                                        case 2:
//                                                                                                            currentUser.backToList(cardInArchive,);
                                                                                                            break;

                                                                                                    }
                                                                                                }
                                                                                            }

                                                                                            break;
                                                                                        case 4:
                                                                                            isInBoard = false;
                                                                                            break;
                                                                                    }
                                                                                }
                                                                                break;

                                                                        }
                                                                    }
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
                                    JOptionPane.showMessageDialog(null, "Logged Out successfully",
                                            "Logout Page", JOptionPane.INFORMATION_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "Thank you ! \uD83D\uDE09",
                            "Exit Page", JOptionPane.INFORMATION_MESSAGE);
                    isAppRunning = false;
                    break;
            }

        }
    }
}