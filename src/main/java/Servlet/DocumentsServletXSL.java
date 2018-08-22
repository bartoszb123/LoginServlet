package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Service.CrudDataBase;
import model.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@WebServlet("/documents")
public class DocumentsServletXSL extends HttpServlet {

    private Transformer transformer = null;
    private String finalHtmlString = "";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // createDocumentsXML(req);
            createXMLinMemory();

        try {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.write(finalHtmlString);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            //resp.sendRedirect(resp.encodeRedirectURL("documentsTable"));

    }


    //Stworzenie XML za pomocą ORG.JDOM2
    private void createDocumentsXML(HttpServletRequest request) throws SQLException, ClassNotFoundException, URISyntaxException {

        Element root = new Element("documents");
        org.jdom2.Document doc = new org.jdom2.Document();

        CrudDataBase crudDataBase = new CrudDataBase();
        List<Document> docs = crudDataBase.findAllDoc();

        for (final Document docc : docs) {
            Element child = new Element("document");
            Collection<Element> docElements = new ArrayList<Element>() {
                {
                    add(new Element("id").addContent(Long.toString(docc.getId())));
                    add(new Element("name").addContent(docc.getName()));
                    add(new Element("content").addContent(docc.getContent()));
                    add(new Element("alias").addContent(docc.getAlias()));

                }
            };
            child.addContent(docElements);
            root.addContent(child);
        }
        doc.setRootElement(root);

        XMLOutputter outter = new XMLOutputter();
        outter.setFormat(Format.getPrettyFormat());
        try {
            String xmlFile = "src/main/webapp/resources/documents.xml";
            String absolutePath = new File("").getAbsolutePath();
            String xmlFilePath = absolutePath + File.separator + xmlFile;
            File docFile = new File(xmlFilePath);
            docFile.createNewFile();
            outter.output(doc, new FileWriter(docFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
// Wyplucie HTML z istniejącego juz XML/XSL
    public void XmlTransformer() throws IllegalArgumentException, TransformerException {

        //XSL
        String xslFile = "src/main/webapp/resources/documents.xsl";
        String absolutePath1 = new File("").getAbsolutePath();
        String xslFilePath = absolutePath1 + File.separator + xslFile;

        //XML
        String xmlFile = "src/main/webapp/resources/documents.xml";
        String absolutePath2 = new File("").getAbsolutePath();
        String xmlFilePath = absolutePath2 + File.separator + xmlFile;

        //HTML
        String htmlFile = "src/main/webapp/documentsXSL.html";
        String absolutePath3 = new File("").getAbsolutePath();
        String htmlFilePath = absolutePath3 + File.separator + htmlFile;

        // StreamSource xslStreamSource = new StreamSource(inputStream);
        TransformerFactory transFact = TransformerFactory.newInstance();

        transFact.newTransformer(new StreamSource(xslFilePath)).
                transform(new StreamSource(new File(xmlFilePath)), new StreamResult(htmlFilePath));

    }


    //Wyrzuceinie na konsole XMLA
    public org.w3c.dom.Document parseDOM() {

        org.w3c.dom.Document doc = null;

        try {
            String xmlFile = "src/main/webapp/resources/documents.xml";
            String absolutePath = new File("").getAbsolutePath();
            String xmlFilePath = absolutePath + File.separator + xmlFile;

            File fXmlFile = new File(xmlFilePath);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);

//            doc.getDocumentElement().normalize();
            org.w3c.dom.Element documentElement = doc.getDocumentElement();

            NodeList nodes = documentElement.getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                System.out.println("" + nodes.item(i).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }


    public void createXMLinMemory() {

        try {

            CrudDataBase crudDataBase = new CrudDataBase();
            List<Document> docs = crudDataBase.findAllDoc();

            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
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

            //----------------------------------------------------------------------------

            //XSL
            String xslFile = "src/main/webapp/resources/documents.xsl";
            String absolutePath1 = new File("").getAbsolutePath();
            String xslFilePath = absolutePath1 + File.separator + xslFile;

            TransformerFactory transFact = TransformerFactory.newInstance();


            DOMSource xmlFile = new DOMSource(doc);

            StringWriter stringWriter = new StringWriter();
           // StreamResult srhtml = new StreamResult(htmlFilePath);
            StreamResult srhtml = new StreamResult();
            srhtml.setWriter(stringWriter);

            transFact.newTransformer(new StreamSource(xslFilePath)).
                    transform(xmlFile, srhtml);
            // srhtml -> przetransoformowany xml i xsl na html
            StringWriter writer = (StringWriter) srhtml.getWriter();
            StringBuffer sb = writer.getBuffer();
            finalHtmlString = sb.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
