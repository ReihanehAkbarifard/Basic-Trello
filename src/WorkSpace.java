import java.util.ArrayList;

public class WorkSpace {
    private String workSpaceName;
    private ArrayList<User> guests = new ArrayList<>();
    private ArrayList<User> admins = new ArrayList<>();
    private ArrayList<User> members = new ArrayList<>();
    private ArrayList<Board> boards = new ArrayList<>();
    private String status;
    private String type;
    private String primaryKey;
    public static ArrayList publicWorkSpaced = new ArrayList();

    public WorkSpace(String workSpaceName, String status, String type, String primaryKey) {
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
    public String getPrimaryKey() {
        return primaryKey;
    }
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }


}