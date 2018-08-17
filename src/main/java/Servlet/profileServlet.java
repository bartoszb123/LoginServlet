package Servlet;

import Service.CrudDataBase;
import model.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/profile")
public class profileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession(false) != null) {


            String name = req.getParameter("name");
            String content = req.getParameter("content");
            String alias = req.getParameter("alias");

            CrudDataBase crudDataBase = new CrudDataBase();
            try {
                crudDataBase.createdoc(new Document(name, content, alias));

                req.getRequestDispatcher("profile.html").forward(req, resp);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }else

        {
            resp.sendRedirect("login.html");
        }


    }




}
