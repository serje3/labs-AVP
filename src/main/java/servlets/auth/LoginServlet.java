package servlets.auth;

import database.UserModel;
import entity.User;
import exceptions.UserAlreadyExistException;
import utils.SecurityUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/auth/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.renderTemplate(req, resp);
    }

    private void renderTemplate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/auth/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserModel userModel = new UserModel();

        if (userModel.authenticate(username, password)) {
            User user = userModel.get(username);
            HttpSession session = req.getSession();
            session.setAttribute("user", username);
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(30 * 60);
            Cookie sessionID = new Cookie("user", SecurityUtility.encode(username + ":" + password));
            sessionID.setMaxAge(30 * 60);
            resp.addCookie(sessionID);
            req.setAttribute("error", null);
            if (user.isSuperUser()) {
                resp.sendRedirect("/admin");
            } else {
                resp.sendRedirect("/");
            }
        } else {
            req.setAttribute("error", "Authenticate failed");
            this.renderTemplate(req, resp);
        }
    }
}
