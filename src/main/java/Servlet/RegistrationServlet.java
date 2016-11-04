package Servlet;

import Database.SQLHelper;
import Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import Exception.*;
import Service.Encryptor;
import Service.RegularTester;

/**
 * Created by Nurislam on 25.10.2016.
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static String[] CITYS = {"Madrid", "London", "Chelny", "Praga", "Berlin", "Paris"};
    private String email;
    private String firstName;
    private String secondName;
    private String password;
    private String repassword;
    private String sex;
    private String city;
    private String age;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            resp.sendRedirect("/mypage");
        } else {
            req.setAttribute("citys", CITYS);
            fillEnteredInformation(req);
            req.getRequestDispatcher("/WEB-INF/JSP/Register.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        email = req.getParameter("email");
        firstName = req.getParameter("firstName");
        secondName = req.getParameter("secondName");
        password = req.getParameter("password");
        repassword = req.getParameter("repassword");
        sex = req.getParameter("sex");
        city = req.getParameter("city");
        age = req.getParameter("age");

        RegularTester ret = new RegularTester();
        boolean f = true;

        if (!ret.isCorrectEmail(email)) {
            f = false;
            email = null;
            showError(req, "email", "Invalid email!!!");
        }

        if (!ret.isCorrectName(firstName)) {
            f = false;
            firstName = null;
            showError(req, "firstName", "Invalid first name");
        }

        if (!ret.isCorrectName(secondName)) {
            f = false;
            secondName = null;
            showError(req, "secondName", "Invalid second name");
        }

        if (!ret.isCorrectPassword(password)) {
            f = false;
            showError(req, "password", "Invalid password");
        } else {
            if (!password.equals(repassword)) {
                f = false;
                showError(req, "repassword", "Passwords don't equal");
            }
        }

        if (sex == null) {
            f = false;
            showError(req, "sex", "Choose your sex");
        } else {
            if (!(sex.equals("Male") || sex.equals("Female"))) {
                f = false;
                showError(req, "sex", "Choose your sex");
            }
        }

        if (city == null) {
            f = false;
            showError(req, "city", "Choose your city");
        } else {
            if (city.equals("null")) {
                f = false;
                showError(req, "city", "Choose your city");
            }
        }

        if (age == null) {
            f = false;
            showError(req, "age", "Incorrect your age");
        } else {
            if (ret.isCorrectAge(age)) {
                f = false;
                showError(req, "age", "Incorrect your age");
            }
        }

        if (f) {
            try {
                addUser(req);
                resp.sendRedirect("/login");
            } catch (DBException e) {
                e.printStackTrace();
                doGet(req, resp);
            } catch (AlreadyExistException e) {
                showError(req, "email", "This email already used");
                doGet(req, resp);
            }
        } else {
            doGet(req, resp);
        }

    }

    private void fillEnteredInformation(HttpServletRequest req) {
        req.setAttribute("email", email);
        req.setAttribute("firstName", firstName);
        req.setAttribute("secondName", secondName);
        req.setAttribute("city", city);
        req.setAttribute("sex", sex);
        req.setAttribute("age", age);
    }

    private void showError(HttpServletRequest req, String name, String errMessage) {
        req.setAttribute("err" + name, errMessage);
    }

    private void addUser(HttpServletRequest req) throws DBException, AlreadyExistException {
        SQLHelper base = new SQLHelper();
        User user = new User(secondName + " " + firstName, email, /*Encryptor.getHash(password, email)*/ password, city, sex.equals("M"), age);
        base.addUser(user);
        req.getSession().setAttribute("user", user);
    }

}
