import java.util.ArrayList;

public class Card {
    private String title;
    private int cardId;
    private String description;
    private String label;
    ArrayList<Activity> activities_message = new ArrayList<>();

    public Card(String title,int cardId, String description, String label) {
        this.title = title;
        this.cardId = cardId;
        this.description = description;
        this.label = label;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
