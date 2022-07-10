import java.util.ArrayList;

public class Board {
    private String title;
    private ArrayList<User> guests = new ArrayList<>();
    private ArrayList<User> admins = new ArrayList<>();
    private ArrayList<List> lists = new ArrayList<>();

    public Board(String title) {
        this.title = title;
    }
}