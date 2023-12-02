import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getSession().getAttribute("user") == null){
            resp.sendRedirect(req.getContextPath() + "/auth/login");
        } else {
            req.getRequestDispatcher("views/time.jsp").forward(req, resp);
        }
    }
}
