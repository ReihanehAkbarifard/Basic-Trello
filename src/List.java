import java.util.ArrayList;

public class List {
    private String name;
    private int listId;
    private ArrayList<Card>  cards = new ArrayList<>();

    public List(String name, int listId) {
        this.name = name;
        this.listId = listId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getListId() {
        return listId;
    }
    public void setListId(int listId) {
        this.listId = listId;
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
