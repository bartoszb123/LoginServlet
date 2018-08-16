package Servlet;

import model.Document;
import model.config.CrudDataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "crudServlet", urlPatterns = {"/docAlias", "/docName"})
public class FindByAliasServlet extends HttpServlet {

private String s;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");

        if (req.getSession(false) != null) {

            String alias = req.getParameter("alias");
            String name = req.getParameter("name");
            CrudDataBase cdb = new CrudDataBase();
            try {

                String userPath = req.getServletPath();

                if (userPath.equals("/docAlias")) {
                    s = cdb.finByAlias(alias);
                }else if(userPath.equals("/docName")){
                     s = cdb.finByName(name);
                }


                out.write(s);
                out.flush();
                out.close();


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        } else {

            resp.sendRedirect("login.html");
        }


    }


}
