import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class User {

    private String fullName;
    private String userID;
    private String passWord;
    private String email;
    private String bio;
    private int primaryKey;
    private ArrayList<WorkSpace> myWorkSpaces = new ArrayList<>();


    public User(String fullName, String userID, String passWord, String email, int primaryKey) {
        this.fullName = fullName;
        this.userID = userID;
        this.passWord = passWord;
        this.email = email;
        this.primaryKey = primaryKey;
    }

    public User(String fullName, String userID) {
        this.fullName = fullName;
        this.userID = userID;
    }

    public ArrayList<WorkSpace> getMyWorkSpaces() {
        return myWorkSpaces;
    }
    public void setMyWorkSpaces(ArrayList<WorkSpace> myWorkSpaces) {
        this.myWorkSpaces = myWorkSpaces;
    }
    public int getPrimaryKey() {
        return primaryKey;
    }
    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
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

        PreparedStatement preparedStatement = connection.prepareStatement("Select * from users where username = ? " +
                "or email = ?");
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "This User Name or Email has Been" +
                        " Chosen Before", "Sign-Up page", JOptionPane.INFORMATION_MESSAGE);

            } else {
                PreparedStatement preparedStatement2 = connection.prepareStatement("Insert into users (username, password, email, " +
                        "fullname, bio) values(?, ?, ?, ?, ?)");
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
            System.out.println(err.toString());
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
                        resultSet.getString("email"), resultSet.getInt("primarykey"));
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

    public void deleteProfile() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?" +
                "autoReconnect=true&useSSL=false", "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE username = ? ");
        preparedStatement.setString(1, this.userID);
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null , "Your account was deleted " +
                        "successfully", "delete account",
                JOptionPane.INFORMATION_MESSAGE);


    }
    public void createWorkSpace() throws SQLException {
        String title = null;
        while (true) {
            title = JOptionPane.showInputDialog(null, "Please Enter the title of WorkSpace :",
                    "createWorkSpace", JOptionPane.QUESTION_MESSAGE);
            if ((title != null) && ((title.length() > 4 && title.length() < 30))) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Not accepted ! Please try another one\nYour username must have at least 5 characters ",
                        "createWorkSpace", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        int numStatus = Integer.parseInt(JOptionPane.showInputDialog(null, "Please Enter the status of WorkSpace :\n" +
                "1. Public\n2. Private", "createWorkSpace", JOptionPane.QUESTION_MESSAGE));
        String status = null;
        switch (numStatus) {
            case 1:
                status = "Public";
                break;
            case 2:
                status = "Private";
        }

        JDialog.setDefaultLookAndFeelDecorated(true);
        Object[] selectionValues = {"IT", "Education", "Business", "Marketing", "Human Recourse",
                "Other"};
        String initialSelection = "IT";
        Object type = JOptionPane.showInputDialog(null, "Please Enter the type of WorkSpace :",
                "Create WorkSpace", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?" +
                "autoReconnect=true&useSSL=false", "root", "");

        PreparedStatement preparedStatement = connection.prepareStatement("Insert into workspace(workspacename, status ," +
                " type) values(?, ?, ?)");
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, status);
        preparedStatement.setString(3, (String) type);
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "Your WorkSpace Was Added " +
                "Successfully", "Create WorkSpace page", JOptionPane.INFORMATION_MESSAGE);

        preparedStatement = connection.prepareStatement("SELECT primarykey FROM workspace ORDER BY primarykey DESC LIMIT 1 ");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int index = resultSet.getInt(1);
        preparedStatement = connection.prepareStatement("Insert into connectionbetweenuserandworkspace" +
                " values(?, ?, ?)");
        preparedStatement.setInt(1, getPrimaryKey());
        preparedStatement.setInt(2, index);
        preparedStatement.setString(3, "Admin");
        preparedStatement.executeUpdate();


    }
    public int seeWorkSpaces() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?" +
                "autoReconnect=true&useSSL=false", "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("select primarykeyworkspace from connectionbetweenuserandworkspace WHERE " +
                "connectionbetweenuserandworkspace.primarykeyuser = ?\n");
        preparedStatement.setInt(1, getPrimaryKey());
        ResultSet resultSet = preparedStatement.executeQuery();
        StringBuilder allWorkSpaces = new StringBuilder();

        getMyWorkSpaces().clear();
        while (resultSet.next()){
            preparedStatement = connection.prepareStatement("select * from workspace where primarykey = ?");
            preparedStatement.setString(1, resultSet.getString("primarykeyworkspace"));
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                WorkSpace workSpace = new WorkSpace(rs.getString("workspacename"),
                rs.getString("status"), rs.getString("type"),
                rs.getInt("primarykey"));
                getMyWorkSpaces().add(workSpace);
            }

        }
        int count = 1;
        for(WorkSpace workSpace : getMyWorkSpaces()){
            allWorkSpaces.append(count + " - " + workSpace.getWorkSpaceName());
            allWorkSpaces.append("\n");
            count++;
        }
        int index = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter a number to show more." +
                        "\nenter 0 to back\n0 - back\n" + allWorkSpaces,
                "all workspaces", JOptionPane.QUESTION_MESSAGE));
        return index;

    }

    public void searchForWorkSpaces() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?" +
                "autoReconnect=true&useSSL=false", "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from workspace where status = ?");
        String find = "Public";
        preparedStatement.setString(1, find);
        ResultSet resultSet = preparedStatement.executeQuery();
        StringBuilder allWorkSpaces = new StringBuilder();
        while (resultSet.next()) {
            allWorkSpaces.append(resultSet.getString("workspacename") + "\n");
            WorkSpace workSpace = new WorkSpace(resultSet.getString("workspacename"),
                    resultSet.getString("status"), resultSet.getString("type"),
                    resultSet.getInt("primarykey"));
            WorkSpace.publicWorkSpaced.add(workSpace);
        }
        JOptionPane.showMessageDialog(null, allWorkSpaces, "All Public workspaces",
                JOptionPane.INFORMATION_MESSAGE);
    }
    public void showDetailsOfWorkspaces(WorkSpace workSpace) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?" +
                "autoReconnect=true&useSSL=false", "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("select primarykeyuser, role" +
                " from connectionbetweenuserandworkspace WHERE primarykeyworkspace = ?\n");
        preparedStatement.setInt(1, workSpace.getPrimaryKey());
        ResultSet resultSet = preparedStatement.executeQuery();
        StringBuilder allMembers = new StringBuilder();
        workSpace.getRole().clear();
        while (resultSet.next()){
            String _role = resultSet.getString("role");
            String _username = null;
            preparedStatement = connection.prepareStatement("select username from users where primarykey = ?");
            preparedStatement.setString(1, resultSet.getString("primarykeyuser"));
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                _username = rs.getString("username");
                workSpace.getRole().put(_username, _role);
            }

        }
        int count = 1;
        for(String username : workSpace.getRole().keySet()){
            allMembers.append(count + " - " + username + " : " +
                    workSpace.getRole().get(username) + "\n");
            count++;
        }

        JOptionPane.showMessageDialog(null, "workspace name : "
                + workSpace.getWorkSpaceName() + "\n" + "Status : " + workSpace.getStatus() + "\n" +
                "Type : " + workSpace.getType() + "\n" + allMembers);
    }
    public void addMembersToWorkSpace(WorkSpace workSpace) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false", "root", "");
        String useridToAdd = JOptionPane.showInputDialog(null, "Please enter the user id which " +
                "you want to add :", "Add Member", JOptionPane.QUESTION_MESSAGE);
        PreparedStatement preparedStatement = connection.prepareStatement("select primarykey" +
                " from users where username = ?");
        preparedStatement.setString(1, useridToAdd);
        ResultSet resultSet = preparedStatement.executeQuery();
        int userid = 0;
        if(resultSet.next()){
            userid = resultSet.getInt(1);
        }
        preparedStatement = connection.prepareStatement("insert into connectionbetweenuserandworkspace values(?, ?, ?)");
        if(userid != 0){
            preparedStatement.setInt(1, userid);
            preparedStatement.setInt(2, workSpace.getPrimaryKey());
            preparedStatement.setString(3, "Member");
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "User was added to workspace!",
                    "Add Member", JOptionPane.INFORMATION_MESSAGE);

        }
        else {
            JOptionPane.showMessageDialog(null, "User not found!",
                    "Add Member", JOptionPane.INFORMATION_MESSAGE);
        }

    }


    public int showBoards(WorkSpace workSpace) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("select * " +
                "from boards where workspace_id = ?");
        preparedStatement.setInt(1, workSpace.getPrimaryKey());
        ResultSet resultSet = preparedStatement.executeQuery();
        workSpace.getBoards().clear();
        while (resultSet.next()){
            Board board = new Board(resultSet.getString("boardname"),
                    resultSet.getInt("board_id"));
            workSpace.getBoards().add(board);
        }

        int count = 1;
        StringBuilder allBoards = new StringBuilder();

        for (Board board : workSpace.getBoards()){
            allBoards.append(count + " - " + board.getTitle() + "\n");
            count++;
        }
        int chosen = Integer.parseInt(JOptionPane.showInputDialog(null, "These are all your boards \n" +
                        allBoards + "Please enter the number to show more\n" ,
                "Show all boards", JOptionPane.INFORMATION_MESSAGE));
        return chosen;
    }

    public void addBoards(WorkSpace workSpace) throws SQLException {
        String name = null;
        while (true) {
            name = JOptionPane.showInputDialog(null, "Please enter the" +
                    " name of new board :", "add board", JOptionPane.QUESTION_MESSAGE);
            if ((name != null) && ((name.length() > 3 && name.length() < 31))) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Not accepted ! Please try another one\nYour username must have at least 4 characters and at most 30 characters",
                        "add board", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Successfully add new board",
                "Add new board", JOptionPane.INFORMATION_MESSAGE);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("insert into boards (boardname, " +
                "workspace_id ) values(?, ?)");
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, workSpace.getPrimaryKey());
        preparedStatement.executeUpdate();


    }
    public int showAllLists(Board board) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("select * " +
                "from lists where board_id = ?");
        preparedStatement.setInt(1, board.getBoardId());
        ResultSet resultSet = preparedStatement.executeQuery();
        board.getLists().clear();
        while (resultSet.next()){
            List list = new List(resultSet.getString("listname"),
                    resultSet.getInt("list_id"));
            board.getLists().add(list);
        }

        int count = 1;
        StringBuilder allLists = new StringBuilder();
        for (List list : board.getLists()){
            allLists.append(count + " - " + list.getName() + "\n");
            count++;
        }
        int chosen = Integer.parseInt(JOptionPane.showInputDialog(null, "These are all your lists" +
                        "\nPlease enter the number to show more\n0. Back \n" + allLists  ,
                "Show all lists", JOptionPane.INFORMATION_MESSAGE));
        return chosen;
    }
    public void addLists(Board board) throws SQLException {
        String name = null;
        while (true) {
            name = JOptionPane.showInputDialog(null, "Please enter the" +
                    " name of new list :", "add list", JOptionPane.QUESTION_MESSAGE);
            if ((name != null) && ((name.length() > 3 && name.length() < 31))) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Not accepted ! Please try another one\nYour username must have at least 4 characters and at most 30 characters",
                        "add board", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Successfully add new list",
                "Add new list", JOptionPane.INFORMATION_MESSAGE);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("insert into lists (listname, " +
                "board_id ) values(?, ?)");
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, board.getBoardId());
        preparedStatement.executeUpdate();


    }
    public int showAllCards(List list) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("select * " +
                "from cards where list_id = ?");
        preparedStatement.setInt(1, list.getListId());
        ResultSet resultSet = preparedStatement.executeQuery();
        list.getCards().clear();
        while (resultSet.next()){
            Card card = new Card(resultSet.getString("cardname"), resultSet.getInt("card_id"),
                    resultSet.getString("description"), resultSet.getString("cardname"));
            list.getCards().add(card);
        }

        ArrayList<String> options = new ArrayList<>();
        options.add("Back");
        for (Card card : list.getCards()){
            options.add(card.getTitle());

        }


        int chosen = JOptionPane.showOptionDialog(null, "These are all your cards \n",
                "Show all cards",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options.toArray(), options.get(0));
        return chosen;
    }
    public void addCard(List list) throws SQLException{
        String name = null;
        while (true) {
            name = JOptionPane.showInputDialog(null, "Please enter the" +
                    " name of new card :", "add card", JOptionPane.QUESTION_MESSAGE);
            if ((name != null) && ((name.length() > 3 && name.length() < 31))) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Not accepted ! Please try another one\nYour username must have at least 4 characters and at most 30 characters",
                        "add card", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Successfully add new card",
                "Add new card", JOptionPane.INFORMATION_MESSAGE);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("insert into cards (cardname, " +
                "list_id ,description, label, deadline) values(?, ?, ?, ?, ?)");
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, list.getListId());
        preparedStatement.setString(3, "");
        preparedStatement.setString(4, "");
        preparedStatement.setString(5, "");
        preparedStatement.executeUpdate();
    }

    public int showCard(Card card) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("select * " +
                "from cards where card_id = ?");
        preparedStatement.setInt(1, card.getCardId());
        ResultSet resultSet = preparedStatement.executeQuery();
        StringBuilder allDetails = new StringBuilder();
        while (resultSet.next()){
            allDetails.append("name : " + resultSet.getString("cardname") + "\n" +
                            "Label : " + resultSet.getString("label") + "\n" + "Description : " +
                    resultSet.getString("description") + "\n" +
                    "Deadline : " + resultSet.getString("deadline") + "\n");

        }

        ArrayList<String> options = new ArrayList<>();
        options.add("Back");
        options.add("Edit and Add sth");
        options.add("Move card");
        options.add("Archive");
        options.add("Message");

        int chosen = JOptionPane.showOptionDialog(null, allDetails + "\n",
                "Show Card",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options.toArray(), options.get(0));
        return chosen;


    }
    public void moveCard(Board board, Card card) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        ArrayList<String> allListsName = new ArrayList<>();
        for(List list1 : board.getLists()){
            allListsName.add(list1.getName());
        }


        Object type = JOptionPane.showInputDialog(null, "Please Enter the type of WorkSpace :",
                "Create WorkSpace", JOptionPane.QUESTION_MESSAGE, null, allListsName.toArray(), allListsName.get(0));
        int listId = 0;
        for (List list1 : board.getLists()){
            if(list1.getName().equals(type.toString())){
                listId = list1.getListId();
            }
        }
        PreparedStatement preparedStatement = connection.prepareStatement("update cards" +
                " set list_id = ? where card_id = ?");
        preparedStatement.setInt(1, listId);
        preparedStatement.setInt(2, card.getCardId());
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "Card Moved Successfully",
                "Move card", JOptionPane.INFORMATION_MESSAGE);
    }
    public void editAndAddToCard(Card card) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        Object[] selectionValues = {"Title", "Label", "Description", "Deadline"};
        String initialSelection = "Title";
        Object type = JOptionPane.showInputDialog(null, "Please Choose the item which you want " +
                        "to change :",
                "Edit And Add To Card", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        if(type.toString().equals("Label")){
            String newLabel = JOptionPane.showInputDialog(null, "Please enter your new label :",
                    "Edit And Add To Card",JOptionPane.QUESTION_MESSAGE);
            PreparedStatement preparedStatement = connection.prepareStatement("update cards " +
                    "set label = ? where card_id = ? ");
            preparedStatement.setString(1, newLabel);
            preparedStatement.setInt(2, card.getCardId());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Label Changed Successfully",
                    "Change label", JOptionPane.INFORMATION_MESSAGE);

        }
        else if (type.toString().equals("Description")){
            String newDescription = JOptionPane.showInputDialog(null, "Please enter your new description :",
                    "Edit And Add To Card",JOptionPane.QUESTION_MESSAGE);
            PreparedStatement preparedStatement = connection.prepareStatement("update cards " +
                    "set description = ? where card_id = ? ");
            preparedStatement.setString(1, newDescription);
            preparedStatement.setInt(2, card.getCardId());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Description Changed Successfully",
                    "Change Description", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (type.toString().equals("Title")){
            String newTitle = JOptionPane.showInputDialog(null, "Please enter your new title :",
                    "Edit And Add To Card",JOptionPane.QUESTION_MESSAGE);
            PreparedStatement preparedStatement = connection.prepareStatement("update cards " +
                    "set cardname = ? where card_id = ? ");
            preparedStatement.setString(1, newTitle);
            preparedStatement.setInt(2, card.getCardId());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "title Changed Successfully",
                    "Change title", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (type.toString().equals("Deadline")){
            String[] year = { "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033" };
            initialSelection = "2022";
            String selectionYear = (String) JOptionPane.showInputDialog(null, "Choose the deadlines year :",
                    "Set Deadline", JOptionPane.QUESTION_MESSAGE, null, year, initialSelection);


            String[] month = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
            initialSelection = "January";
            String selectionMonth = (String) JOptionPane.showInputDialog(null, "Choose the deadlines month :",
                    "Set Deadline", JOptionPane.QUESTION_MESSAGE, null, month, initialSelection);

            String[] days = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                    "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
            initialSelection = "1";
            String selectionDay = (String) JOptionPane.showInputDialog(null, "Choose the deadlines Day :",
                    "Set Deadline", JOptionPane.QUESTION_MESSAGE, null, days, initialSelection);

            String newDeadline = selectionDay + " " + selectionMonth + " " + selectionYear;

            PreparedStatement preparedStatement = connection.prepareStatement("update cards " +
                    "set deadline = ? where card_id = ? ");
            preparedStatement.setString(1, newDeadline);
            preparedStatement.setInt(2, card.getCardId());
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Deadline is set for : \n\t*** " + newDeadline + " ***",
                    "Set Deadline", JOptionPane.INFORMATION_MESSAGE);
        }



    }
    public void archive(WorkSpace workSpace, Board board, List list ,Card card) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("update cards set " +
                "orglist_id = ? where card_id = ?");
        preparedStatement.setInt(1, list.getListId());
        preparedStatement.setInt(2, card.getCardId());
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("update cards set " +
                "list_id = ? where list_id = ?");
        preparedStatement.setInt(1, 0);
        preparedStatement.setInt(2, list.getListId());
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("insert into archive (workspace_id, " +
                "board_id, list_id, card_id) values(?, ?, ?, ?)");
        preparedStatement.setInt(1, workSpace.getPrimaryKey());
        preparedStatement.setInt(2, board.getBoardId());
        preparedStatement.setInt(3, list.getListId());
        preparedStatement.setInt(4, card.getCardId());
        preparedStatement.executeUpdate();
        list.getCards().remove(card);
        JOptionPane.showMessageDialog(null, "card archived successfully",
                "Archive", JOptionPane.INFORMATION_MESSAGE);


    }


    public int showArchives(Board board) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from cards" +
                " where list_id = ?");
        preparedStatement.setInt(1, 0);
        ResultSet resultSet = preparedStatement.executeQuery();
        StringBuilder allArchives = new StringBuilder();
        board.getArchives().clear();
        while (resultSet.next()){
            Card card = new Card(resultSet.getString("cardname"),
                    resultSet.getInt("card_id"), resultSet.getInt("orglist_id"));
            board.getArchives().add(card);
        }
        int count = 1;
        for (Card card : board.getArchives()){
            allArchives.append(count + " - " + card.getTitle() + "\n");
            count++;
        }
        int index = Integer.parseInt(JOptionPane.showInputDialog(null, "These are all Your archive cards :\n"
                + "0 - Back\n"+ allArchives, "Archives cards", JOptionPane.QUESTION_MESSAGE));
        return index;

    }
    public void backToList(Card card, Board board) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("update cards set "+
                "list_id = ? where card_id = ?");
        preparedStatement.setInt(1, card.getOrgListId());
        preparedStatement.setInt(2, card.getCardId());
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "Your card is restored",
                "restore card", JOptionPane.INFORMATION_MESSAGE);
        board.getArchives().remove(card);


    }
    public void deleteCard(Card card, Board board) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("delete from cards where " +
                "card_id = ?");
        preparedStatement.setInt(1, card.getCardId());
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "Your card is deleted",
                "delete card", JOptionPane.INFORMATION_MESSAGE);
        board.getArchives().remove(card);

    }
    public void showAllMessages(Card card) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from activities where card_id = ?");
        preparedStatement.setInt(1, card.getCardId());
        ResultSet resultSet = preparedStatement.executeQuery();

        card.getActivities_message().clear();
        while (resultSet.next()){
            String sender = resultSet.getString("username");
            String text = resultSet.getString("text");
            Activity activity = new Activity(sender, text);
            card.getActivities_message().add(activity);

        }
        StringBuilder text = new StringBuilder();
        for (Activity activity : card.getActivities_message()){
            text.append("Sender : " + activity.getSender());
            text.append("\n");
            text.append(activity.getActivityText());
            text.append("\n\n");
        }
        if (text.equals(null)){
            JOptionPane.showMessageDialog(null, "There is no message here"
                    ,"Show all messages", JOptionPane.INFORMATION_MESSAGE);
        }
        else {

            JOptionPane.showMessageDialog(null, text,"Show all messages",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void sendMessage(Card card, User user) throws SQLException {
        String text = JOptionPane.showInputDialog(null, "Enter your message to send","Show all messages", JOptionPane.QUESTION_MESSAGE);
        Activity activity = new Activity(user.getUserID(), text);
        card.getActivities_message().add(activity);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("insert into activities (card_id, username , text) values(?,?,?)");
        preparedStatement.setInt(1, card.getCardId());
        preparedStatement.setString(2, user.getUserID());
        preparedStatement.setString(3, text);
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "Your message added successfully", "Add message", JOptionPane.INFORMATION_MESSAGE);
    }

    public void deleteMessage(Card card) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from activities where card_id = ?");
        preparedStatement.setInt(1, card.getCardId());
        ResultSet resultSet = preparedStatement.executeQuery();
        card.getActivities_message().clear();
        while (resultSet.next()){
            int activity_id = resultSet.getInt("activity_id");
            String sender = resultSet.getString("username");
            String text = resultSet.getString("text");
            Activity activity = new Activity(activity_id, sender, text);
            card.getActivities_message().add(activity);
        }

        StringBuilder text = new StringBuilder();
        for (Activity activity : card.getActivities_message()){
            text.append("Activity ID : " + activity.getActivityId());
            text.append("\n");
            text.append("Sender : " + activity.getSender());
            text.append("\n");
            text.append(activity.getActivityText());
            text.append("\n\n");
        }


        int numberToDelete = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Enter id of message to delete\n" + text, "delete message", JOptionPane.QUESTION_MESSAGE));

        preparedStatement = connection.prepareStatement("delete from activities where" +
                " activity_id = ?");
        preparedStatement.setInt(1, numberToDelete);
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "This message deleted successfully",
                "delete message", JOptionPane.INFORMATION_MESSAGE);
    }

    public void editMessage(Card card) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trello?autoReconnect=true&useSSL=false",
                "root", "");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from activities where card_id = ?");
        preparedStatement.setInt(1, card.getCardId());
        ResultSet resultSet = preparedStatement.executeQuery();
        card.getActivities_message().clear();
        while (resultSet.next()){
            int activity_id = resultSet.getInt("activity_id");
            String sender = resultSet.getString("username");
            String text = resultSet.getString("text");
            Activity activity = new Activity(activity_id, sender, text);
            card.getActivities_message().add(activity);
        }

        StringBuilder text = new StringBuilder();
        for (Activity activity : card.getActivities_message()){
            text.append("Activity ID : " + activity.getActivityId());
            text.append("\n");
            text.append("Sender : " + activity.getSender());
            text.append("\n");
            text.append(activity.getActivityText());
            text.append("\n\n");
        }


        int numberToEdit = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Enter id of message to edit\n" + text, "Edit message", JOptionPane.QUESTION_MESSAGE));
        String newText = JOptionPane.showInputDialog(null, "Enter your new message : " , "Edit message", JOptionPane.QUESTION_MESSAGE);
        preparedStatement = connection.prepareStatement("update activities set text = ? where activity_id = ?");
        preparedStatement.setString(1, newText);
        preparedStatement.setInt(2, numberToEdit);
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "This message updated successfully",
                "Edit message", JOptionPane.INFORMATION_MESSAGE);


    }
}