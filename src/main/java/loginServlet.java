import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "s1", urlPatterns = "/login")
public class loginServlet extends HttpServlet {

    private String username;
    private String password;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        user user = new user(req.getParameter("name"), req.getParameter("pass"));
        username = user.getName();
        password = user.getPass();
        int Cnt = 0;
        if ("admin".equals(username) && "admin".equals(password)) {

            session.setAttribute("uzytkownik", username);
            session.setAttribute("haslo", password);
            session.setAttribute("VISIT_COUNTER_ATTR", Cnt);
            session.setMaxInactiveInterval(10);

            resp.sendRedirect(resp.encodeRedirectURL("info"));
            return;
        }
        resp.sendRedirect(resp.encodeRedirectURL("login.html"));
    }
}
