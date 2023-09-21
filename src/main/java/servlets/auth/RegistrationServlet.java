package servlets.auth;

import database.UserModel;
import exceptions.PasswordsNotMatchException;
import exceptions.UserAlreadyExistException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registrationServlet", value = "/auth/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.renderTemplate(req, resp);
    }


    private void renderTemplate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/auth/registration.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");

        UserModel userModel = new UserModel();

        try {
            userModel.create(username, password1, password2);
            req.setAttribute("error", null);
            resp.sendRedirect("/auth/login");
        } catch (UserAlreadyExistException e) {
            req.setAttribute("error", "User Already Exist");
            this.renderTemplate(req, resp);
        } catch (PasswordsNotMatchException e) {
            req.setAttribute("error", "Passwords Not Match");
            this.renderTemplate(req, resp);
        }
    }
}
