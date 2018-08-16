package Servlet;

import model.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/DatabaseAccess")
public class DatabaseAccessServlet extends HttpServlet {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/servletDB";
    static final String USER = "postgres";
    static final String PASS = "admin";

    private List<Document> docList = new ArrayList<Document>();
    private Document document = new Document();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        Connection conn = null;
//        Statement stmt = null;
//
//
//        resp.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = resp.getWriter();
//
//        try {
//
//            if (req.getSession(false) != null) {
//                //Class.forName("com.mysql.jdbc.Driver");
//                conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//                stmt = conn.createStatement();
//                String sql = "SELECT id, name, content FROM documents";
//
//                ResultSet rs = stmt.executeQuery(sql);
//
//                while (rs.next()) {
//
//                    int id = rs.getInt("id");
//                    String name = rs.getString("name");
//                    String cont = rs.getString("content");
//
//                    document.setId(id);
//                    document.setNazwa(name);
//                    document.setContetnt(cont);
//
//                    docList.add(document);
//
//                    out.println("ID:" + id + ", Document Name:" + name + ", Content: " + cont);
//
//                }
//                stmt.close();
//                conn.close();
//                out.flush();
//                out.close();
//                rs.close();
//
//
//                for (Document doc:docList) {
//                    System.out.print("Nazwa dokumentu: "+doc.getNazwa());
//                }
//
//                //  req.getRequestDispatcher("documents.html").forward(req, resp);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//       catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }


    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doGet(req,resp);
//    }






}

