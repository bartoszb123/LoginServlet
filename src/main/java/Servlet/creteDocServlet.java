package Servlet;

import Service.CrudDataBase;
import model.Document;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/addDoc")
public class creteDocServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (req.getSession(false) != null) {
//
//            String name = req.getParameter("name");
//            String content = req.getParameter("content");
//            String alias = req.getParameter("alias");

            req.getRequestDispatcher("createDoc.html").forward(req, resp);
            //resp.sendRedirect("profile.html");
        } else {

            resp.sendRedirect("login.html");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession(false) != null) {


            String name = req.getParameter("name");
            String content = req.getParameter("content");
            String alias = req.getParameter("alias");

            CrudDataBase crudDataBase = new CrudDataBase();


            if(name != null && !name.equals("")) {
                try {
                    crudDataBase.createdoc(new Document(name, content, alias));

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            // crudDataBase.createTable();
           // req.getRequestDispatcher("profile.html").forward(req, resp);
                resp.sendRedirect("profile");

        } else

        {
            resp.sendRedirect("login.html");
        }






    }
}
