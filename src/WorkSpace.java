import java.util.ArrayList;
import java.util.HashMap;

public class WorkSpace {
    private String workSpaceName;
    private ArrayList<User> guests = new ArrayList<>();
    public ArrayList<User> admins = new ArrayList<>();
    public ArrayList<User> members = new ArrayList<>();
    private ArrayList<Board> boards = new ArrayList<>();
    private String status;
    private String type;
    private int primaryKey;
    public static ArrayList publicWorkSpaced = new ArrayList();
    private HashMap<String, String> role = new HashMap<>();

    public WorkSpace(String workSpaceName, String status, String type, int primaryKey) {
        this.workSpaceName = workSpaceName;
        this.status = status;
        this.type = type;
        this.primaryKey = primaryKey;
    }

    public ArrayList getPublicWorkSpaced() {
        return publicWorkSpaced;
    }
    public void setPublicWorkSpaced(ArrayList publicWorkSpaced) {
        this.publicWorkSpaced = publicWorkSpaced;
    }
    public String getWorkSpaceName() {
        return workSpaceName;
    }
    public void setWorkSpaceName(String workSpaceName) {
        this.workSpaceName = workSpaceName;
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
    public ArrayList<User> getMembers() {
        return members;
    }
    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }
    public ArrayList<Board> getBoards() {
        return boards;
    }
    public void setBoards(ArrayList<Board> boards) {
        this.boards = boards;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getPrimaryKey() {
        return primaryKey;
    }
    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public HashMap<String, String> getRole() {
        return role;
    }

    public void setRole(HashMap<String, String> role) {
        this.role = role;
    }
}