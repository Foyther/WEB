package Entity;

/**
 * Created by Nurislam on 31.10.2016.
 */
public class Event {
    private String nameEvent;
    private String date;
    private String place;
    private String description;
    private User[] goingUsers;

    public Event(String name, String date, String place, String description, User[] goingUsers) {
        this.nameEvent = name;
        this.date = date;
        this.place = place;
        this.description = description;
        this.goingUsers = goingUsers;
    }

    public Event(String name, String date, String place, String description) {
        this.nameEvent = name;
        this.date = date;
        this.place = place;
        this.description = description;
    }

    public User[] getGoingUsers() {
        return goingUsers;
    }

    public void setGoingUsers(User[] goingUsers) {
        this.goingUsers = goingUsers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String name) {
        this.nameEvent = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
