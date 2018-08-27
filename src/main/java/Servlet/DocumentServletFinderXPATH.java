package Servlet;

import Service.CrudDataBase;

import org.xml.sax.InputSource;
import model.Document;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.*;
import java.io.*;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "searching", urlPatterns = {"/findby", "/findbyID"})
public class DocumentServletFinderXPATH extends HttpServlet {

    public void setNameParam(String nameParam) {
        this.nameParam = nameParam;
    }

    public void setIdParam(String idParam) {
        this.idParam = idParam;
    }


    private String nameParam = null;
    private String idParam = null;
    private List<String> documentByName = null;
    private List<String> documentById = null;
    private static InterestingEvent ie;


    public DocumentServletFinderXPATH(InterestingEvent event) {

        ie = event;
        //  interestingEventHandlerIDValue = ie;
    }

    public DocumentServletFinderXPATH(){

    }




    public List<String> getDocumentByName() {
        return documentByName;
    }

    public List<String> getDocumentById() {
        return documentById;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {


            req.getRequestDispatcher("find.html").forward(req, resp);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        CrudDataBase crudDataBase = new CrudDataBase();
//        List<Document> docs = null;
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = null;
//
//
//        try {
//
//            docs = crudDataBase.findAllDoc();
//            dBuilder = dbFactory.newDocumentBuilder();
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        }
//
//        final org.w3c.dom.Document doc = dBuilder.newDocument();
//
////---------------------------------------------------------------------------------------------------
//        // root element
//        org.w3c.dom.Element documents = doc.createElement("documents");
//
//        for (final Document docc : docs) {
//            //Child element
//            final org.w3c.dom.Element child = doc.createElement("document");
//
//            org.w3c.dom.Element id = doc.createElement("id");
//            String s = Long.toString(docc.getId());
//            id.appendChild(doc.createTextNode(s));
//            child.appendChild(id);
//
//            org.w3c.dom.Element name = doc.createElement("name");
//            name.appendChild(doc.createTextNode(docc.getName()));
//            child.appendChild(name);
//
//            org.w3c.dom.Element cont = doc.createElement("content");
//            cont.appendChild(doc.createTextNode(docc.getContent()));
//            child.appendChild(cont);
//
//            org.w3c.dom.Element alias = doc.createElement("alias");
//            alias.appendChild(doc.createTextNode(docc.getAlias()));
//            child.appendChild(alias);
//
//            documents.appendChild(child);
//        }
//        doc.appendChild(documents);

        //----------------------------------------------------------------------------

//        DOMSource domSource = new DOMSource(doc);


    }

    private void getAllNames(org.w3c.dom.Document doc, XPath xPath) {

        XPathExpression pathExp;

        Object result = null;

        try {
            pathExp = xPath.compile("//document//name//text()");

            result = pathExp.evaluate(doc, XPathConstants.NODESET);

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        NodeList nodeList = (NodeList) result;

        for (int i = 0; i < nodeList.getLength(); i++) {

            System.out.println(nodeList.item(i).getParentNode().getNodeName() + "-"
                    + " " + nodeList.item(i).getNodeValue());


        }


    }


    private static List<String> getDocumentByName(org.w3c.dom.Document doc, XPath xpath, String name) {
        List<String> list = new ArrayList<String>();
        try {
            XPathExpression expr =xpath.compile("//document[name/text()='"+name+"']//text()");
//                    xpath.compile("/documents/document[@name=" + name + "]/text()");
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return list;
    }


    private static List<String> getDocumentById(org.w3c.dom.Document doc, XPath xpath, String id) {
//        Object result = null;
        List<String> list = new ArrayList<String>();
        try {
            XPathExpression expr =xpath.compile("//documents/document[id/text()='"+id+"']/name/text()");
            XPathExpression expr2 =xpath.compile("//document[id/text()='"+id+"']/name/text()");
            XPathExpression expr3 =xpath.compile("//document[id/text()='"+id+"']/content/text()");
            XPathExpression expr4 =xpath.compile("//document[id/text()='"+id+"']//text()");
//                    xpath.compile("//document/name//text()");
//            result = expr3.evaluate(doc, XPathConstants.STRING);
            NodeList nodes = (NodeList) expr4.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++)
                list.add(nodes.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return list;
    }


    public org.w3c.dom.Document convertStringToDOM(String str) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(str));
        org.w3c.dom.Document doc = db.parse(is).getOwnerDocument();

        return doc;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {

            CrudDataBase crudDataBase = new CrudDataBase();
            List<Document> docs = crudDataBase.findAllDoc();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
             final org.w3c.dom.Document doc = dBuilder.newDocument();

            // root element
            org.w3c.dom.Element documents = doc.createElement("documents");

            for (final Document docc : docs) {
                //Child element
                final org.w3c.dom.Element child = doc.createElement("document");

                org.w3c.dom.Element id = doc.createElement("id");
                String s = Long.toString(docc.getId());
                id.appendChild(doc.createTextNode(s));
                child.appendChild(id);

                org.w3c.dom.Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(docc.getName()));
                child.appendChild(name);

                org.w3c.dom.Element cont = doc.createElement("content");
                cont.appendChild(doc.createTextNode(docc.getContent()));
                child.appendChild(cont);

                org.w3c.dom.Element alias = doc.createElement("alias");
                alias.appendChild(doc.createTextNode(docc.getAlias()));
                child.appendChild(alias);

                documents.appendChild(child);
            }
            doc.appendChild(documents);

            setNameParam(req.getParameter("name"));
            setIdParam(req.getParameter("id"));

            HttpSession session = req.getSession(false);
            session.setAttribute("name", nameParam);
            session.setAttribute("id", idParam);


            XPath xPath = XPathFactory.newInstance().newXPath();
            if (!"".equals(nameParam)) {
                documentByName = getDocumentByName(doc, xPath, nameParam);

                ie.interestingEvent (documentByName);


            } else if (!"".equals(idParam)) {
               documentById = getDocumentById(doc, xPath, idParam);

                ie.interestingEvent (documentById);


            }

            resp.sendRedirect("findby");
            // req.getRequestDispatcher("findby").forward(req,resp);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//    private static List<String> getDocumentByName(Document doc, XPath xpath, String name) {
//        List<String> list = new ArrayList<String>();
//        try {
//            XPathExpression expr =
//                    xpath.compile("/documents/document[@name=" + name + "]/name/text()");
//            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
//            for (int i = 0; i < nodes.getLength(); i++)
//                list.add(nodes.item(i).getNodeValue());
//        } catch (XPathExpressionException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

    }

//    interface InterestingEventHandlerIDValue {
//        void interestingEvent();
//    }

//    private InterestingEventHandlerIDValue interestingEventHandlerIDValue;

//    public void setInterestingEventHandlerIDValue(InterestingEventHandlerIDValue interestingEventHandlerIDValue) {
//        this.interestingEventHandlerIDValue = interestingEventHandlerIDValue;
//    }


}