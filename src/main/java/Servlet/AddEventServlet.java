package Servlet;

import Database.EntityDao;
import Entity.Event;
import Entity.User;

import javax.servlet.ServletException;
import Exception.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Nurislam on 01.11.2016.
 */
@WebServlet("/addEvent")
public class AddEventServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameEvent = req.getParameter("nameEvent");
        String description = req.getParameter("description");
        String place = req.getParameter("place");
        String date = req.getParameter("date");

        Event newEvent = new Event(nameEvent, date, place, description);
        EntityDao additor = EntityDao.getInstance();
        try {
            additor.addEvent(newEvent);
        } catch (DBException e) {
            e.printStackTrace();
        } catch (AlreadyExistException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            req.getRequestDispatcher("/WEB-INF/JSP/AddEvent.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/main");
        }
    }
}
