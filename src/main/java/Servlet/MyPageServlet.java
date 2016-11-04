package Servlet;

import Database.SQLHelper;
import Entity.User;
import Exception.*;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Nurislam on 27.10.2016.
 */
@WebServlet("/mypage")
public class MyPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/main");
        } else {
            fillUserInformation(req, resp, user);
            req.getRequestDispatcher("/WEB-INF/JSP/MyPage.jsp").forward(req, resp);
            String btnValue = req.getParameter("button");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String btnValue = req.getParameter("button");
        clickOnButton(btnValue, req, resp);
    }

    private void clickOnButton(String textFromButton, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        switch (textFromButton) {
            case "logout":
                req.getSession().setAttribute("user", null);
                resp.sendRedirect("/");
                break;
            case "delete me":
                HttpSession session = req.getSession();
                User user = (User) session.getAttribute("user");
                SQLHelper repository = new SQLHelper();
                try {
                    repository.delUser(user.getEmail(), user.getPassword());
                    req.getSession().setAttribute("user", null);
                    resp.sendRedirect("/");
                } catch (DBException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void fillUserInformation(HttpServletRequest req, HttpServletResponse resp, User user) {
        req.setAttribute("name", user.getName());
        req.setAttribute("city", user.getCity());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("sex", user.getSex() ? "Male" : "Female");
        req.setAttribute("age", user.getCity());
    }
}
