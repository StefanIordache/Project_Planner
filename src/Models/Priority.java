package Models;

public class Priority extends Entity {

    private String Title;

    public Priority(int id, String title) {
        Id = id;
        Title = title;
    }

    public Priority(String title) {
        Title = title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTitle() {
        return Title;
    }
}
