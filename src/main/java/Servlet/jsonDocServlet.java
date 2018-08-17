package Servlet;

import Service.CrudDataBase;
import model.Document;
import org.json.JSONArray;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@WebServlet("/jsonDoc")
public class jsonDocServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);
        resp.setCharacterEncoding("UTF-8");

        CrudDataBase crudDataBase = new CrudDataBase();
        try {
            List<Document> allDoc = crudDataBase.findAllDoc();
            List<String> listNAmes= new ArrayList<String>();
            for (Document doc:allDoc) {

                listNAmes.add(doc.getName());
            }
//            JSONArray allDataArray = new JSONArray();
            JSONObject jsonobj = new JSONObject();
//            for (Document doc : allDoc) {
//
//                jsonobj.append("name", doc.getName());
//
//            }
//            allDataArray.put(jsonobj);
//            JSONObject root = new JSONObject();
//            root.put("arr", allDataArray);
//            String jsonString = root.toString();
            String lista = jsonobj.put("arr", listNAmes).toString();

            out.write(lista);
            out.flush();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
