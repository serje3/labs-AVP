package servlets;

import database.UserModel;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.renderTemplate(req, resp);
    }


    private void renderTemplate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = (String) req.getSession().getAttribute("user");
        UserModel userModel = new UserModel();
        User user = userModel.get(username);
        if (username == null){
            resp.sendRedirect("/auth/login");
        } else if (!user.isSuperUser()) {
            resp.sendRedirect("/");
        } else {
            req.setAttribute("admin:users", userModel.list());
            req.getRequestDispatcher("/views/admin-panel.jsp").forward(req, resp);
        }
    }
}
