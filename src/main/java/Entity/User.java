package Entity;

/**
 * Created by Nurislam on 25.10.2016.
 */
public class User {

    public static final boolean GENDER_MALE = true;
    public static final boolean GENDER_FEMALE = false;
    private int id;
    private String name;
    private String email;
    private String password;
    private int age;
    private boolean sex;
    private Event[] events;
    private String city;

    public User(String name, String email, String password, String city, boolean sex, String age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.sex = sex;
        this.age = Integer.parseInt(age);
    }

    public User(int id, String name, String email, String password, String city, boolean sex, String age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.sex = sex;
        this.age = Integer.parseInt(age);
    }

    public int hashCode() {
        return email.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }
}
