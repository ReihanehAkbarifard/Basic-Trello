import java.util.ArrayList;

public class Board {
    private String title;
    private int boardId;
    private ArrayList<User> guests = new ArrayList<>();
    private ArrayList<User> admins = new ArrayList<>();
    private ArrayList<List> lists = new ArrayList<>();

    public Board(String title, int boardId) {
        this.title = title;
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public ArrayList<User> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<User> guests) {
        this.guests = guests;
    }

    public ArrayList<User> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<User> admins) {
        this.admins = admins;
    }

    public ArrayList<List> getLists() {
        return lists;
    }

    public void setLists(ArrayList<List> lists) {
        this.lists = lists;
    }
}