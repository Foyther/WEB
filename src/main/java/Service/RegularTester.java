package Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nurislam on 27.10.2016.
 */
public class RegularTester {
    public boolean isCorrectName(String name) {
        return Pattern.matches("^[A-Za-zА-Яа-я]{2,20}", name);
    }

    public boolean isCorrectPassword(String password) {
        return Pattern.matches("^[A-Za-z0-9]{6,60}", password);
    }

    public boolean isCorrectAge(String age) {
        return Pattern.matches("^d{1,3}", age);
    }

    public boolean isCorrectEmail(String email) {
        return Pattern.matches("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+" +
                        "(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*" +
                        "([a-z]+)$",
                email);
    }
}
