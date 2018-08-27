package Servlet;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/jsonHTML")
public class jsonHtml extends HttpServlet implements InterestingEvent {


    private String DocByID;
    private List<String> listStr = new ArrayList<>();
    private DocumentServletFinderXPATH documentServletFinderXPATH;


    public void setListStr(List<String> listStr) {
        this.listStr = listStr;
    }

    public void setDocByID(String docByID) {
        DocByID = docByID;
    }



    public jsonHtml() {
        documentServletFinderXPATH = new DocumentServletFinderXPATH(this);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

//        HttpSession session = req.getSession(false);
//        String documentByName = (String) session.getAttribute("name");
//        String documentByID = (String) session.getAttribute("id");


//        documentServletFinderXPATH.setSendFilterValue(()->{
//
//
//            documentServletFinderXPATH.getDocumentById();
//
//
//        });


//          DocumentServletFinderXPATH documentsServletFinderXPAATH = new DocumentServletFinderXPATH();
//        String result = documentsServletFinderXPAATH.getDocumentById();
//        DocumentsServletXSL documentsServletXSL = new DocumentsServletXSL();
//        String xmLinMemory = documentsServletXSL.createXMLinMemory();

//        List<String> documentByName = documentsServletFinderXPAATH.getDocumentByName();

//        String xmLstring = documentsServletXSL.getXMLstring(); // All documents

        JSONObject jsonobj = new JSONObject();
        jsonobj.put("result", listStr);
//        jsonobj.put("result", "dupaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaadupaaaaaaaaaaa");

        out.write(jsonobj.toString());
        out.flush();


    }

    @Override
    public void interestingEvent(List<String> list) {

//        setDocByID(documentServletFinderXPATH.getDocumentById());
      //  listStr = list;
        setListStr(list);

       // System.out.println("result:" + documentServletFinderXPATH.getDocumentById() );


    }
}
