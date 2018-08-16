package Servlet;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;


@WebServlet("/jsonServlet")
public class jsonServlet extends HttpServlet {

    private String username;
    private String password;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            HttpSession session = req.getSession(false);

            resp.setCharacterEncoding("UTF-8");
            username = (String) session.getAttribute("uzytkownik");
            password = (String) session.getAttribute("haslo");

            String json = createJSON();
            out.write(json);
            out.flush();


    }


    private String createJSON() {

        JSONObject jsonobj2 = new JSONObject();
        jsonobj2.put("name", username);
        jsonobj2.put("pass", password);

        return jsonobj2.toString();
    }

}