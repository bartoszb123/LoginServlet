package Servlet;

import Service.CrudDataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "crudServlet", urlPatterns = {"/docAlias", "/docName", "/docName/*", "/docAlias/*"})
public class FindByServlet extends HttpServlet {

    private String s;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");

        if (req.getSession(false) != null) {


            CrudDataBase cdb = new CrudDataBase();
            try {

                if (req.getPathInfo() != null) {

                    if ("/docAlias".equals(req.getServletPath())) {
                        String pathInfo = req.getPathInfo();
                        this.s = cdb.finByAlias(pathInfo.substring(1));
                    } else if ("/docName".equals(req.getServletPath())) {
                        String pathInfo = req.getPathInfo();
                        this.s = cdb.finByName(pathInfo.substring(1));
                    }
                } else {

                    String userPath = req.getServletPath();

                    if (userPath.equals("/docAlias")) {
                        String alias = req.getParameter("alias");
                        this.s = cdb.finByAlias(alias);
                    } else if (userPath.equals("/docName")) {
                        String name = req.getParameter("name");
                        this.s = cdb.finByName(name);
                    }
                }


                out.write(this.s);
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
