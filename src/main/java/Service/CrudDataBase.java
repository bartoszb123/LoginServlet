package Service;

import model.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudDataBase {

    private int id;
    private String name;
    private String cont;
    private String alias_doc;

    private static List<Document> listdoc = new ArrayList<Document>();


    public String finByAlias(String alias) throws SQLException, ClassNotFoundException {

        String sql = ("SELECT* FROM documents2 WHERE alias='" + alias + "'");

        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        // static final String DB_URL = "jdbc:mysql://localhost:3306/mojabaza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String USER = "postgres";
        final String PASS = "admin";


        Connection conn = null;
        Statement stmt = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {

            //  id = rs.getInt("id");
            name = rs.getString("name");
            cont = rs.getString("content");
            alias_doc = rs.getString("alias");
        }

        rs.close();
        stmt.close();
        conn.close();

        return "Name: " + name + ", Content:  " + cont + ", Alias:  " + alias_doc;

    }

    public String finByName(String name) throws SQLException, ClassNotFoundException {

        String sql = ("SELECT* FROM documents2 WHERE name='" + name + "'");

        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        // static final String DB_URL = "jdbc:mysql://localhost:3306/mojabaza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String USER = "postgres";
        final String PASS = "admin";


        Connection conn = null;
        Statement stmt = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {

            //  id = rs.getInt("id");
            name = rs.getString("name");
            cont = rs.getString("content");
            alias_doc = rs.getString("alias");
        }

        rs.close();
        stmt.close();
        conn.close();

        return "Name: " + name + ", Content:  " + cont + ", Alias:  " + alias_doc;

    }


    public void createdoc(Document doc) throws SQLException, ClassNotFoundException {

        String sql = ("INSERT INTO documents2(name,content,alias) Values('" + doc.getName() + "','" + doc.getContent() + "','" + doc.getAlias() + "')");

        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        // static final String DB_URL = "jdbc:mysql://localhost:3306/mojabaza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String USER = "postgres";
        final String PASS = "admin";

        Connection conn = null;
        Statement stmt = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        stmt.executeUpdate(sql);

//        //checking
//        String sql = "SELECT* from documents2";
//        ResultSet rs = stmt.executeQuery(sql);
//
////        while (rs.next()) {
////
////            // id = rs.getInt ("id");
////            name = rs.getString("name");
////            cont = rs.getString("content");
////            alias_doc = rs.getString("alias");
////        }

        //rs.close();
        stmt.close();
        conn.close();

    }


    public List<Document> findAllDoc() throws ClassNotFoundException, SQLException {

        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        // static final String DB_URL = "jdbc:mysql://localhost:3306/mojabaza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String USER = "postgres";
        final String PASS = "admin";


        Connection conn = null;
        Statement stmt = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        listdoc.clear();

        String sql = "SELECT* from documents2";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {

            id = rs.getInt ("id");
            name = rs.getString("name");
            cont = rs.getString("content");
            alias_doc = rs.getString("alias");

            listdoc.add(new Document(id, name, cont, alias_doc));

        }

        rs.close();
        stmt.close();
        conn.close();

        return listdoc;

    }

    public void deleteDoc(String name) throws ClassNotFoundException, SQLException {

        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        // static final String DB_URL = "jdbc:mysql://localhost:3306/mojabaza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String USER = "postgres";
        final String PASS = "admin";

        // List<Document> listdoc = new ArrayList<Document>();

        Connection conn = null;
        Statement stmt = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        //id = 36;
        String query = ("DELETE FROM documents2 WHERE name ='" + name + "'");

        stmt.executeQuery(query);

        long iDbyNameFromList = findIDbyNameFromList(name);

        listdoc.remove(iDbyNameFromList);


        stmt.close();
        conn.close();

    }

    public long findIDbyNameFromList(String name) throws SQLException, ClassNotFoundException {

        for (Document doc : listdoc) {

            if (doc.getName().equals(name)) {

                return doc.getId();
            }
        }
        return 0;
    }

    public void createTable() throws ClassNotFoundException, SQLException {


        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        // static final String DB_URL = "jdbc:mysql://localhost:3306/mojabaza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String USER = "postgres";
        final String PASS = "admin";

        List<Document> listdoc = new ArrayList<Document>();

        Connection conn = null;
        Statement stmt = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        String sql = "CREATE TABLE documents2 " +
                "(id SERIAL PRIMARY KEY, " +
                " name VARCHAR(255), " +
                " content text, " +
                " alias VARCHAR(255))";


        ResultSet rs = stmt.executeQuery(sql);

        rs.close();
        stmt.close();
        conn.close();


    }


    public void reseetIDinDB() throws ClassNotFoundException, SQLException {

        String sql = "TRUNCATE TABLE documents2 RESTART IDENTITY";


        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        // static final String DB_URL = "jdbc:mysql://localhost:3306/mojabaza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String USER = "postgres";
        final String PASS = "admin";

        Connection conn = null;
        Statement stmt = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();

        stmt.executeQuery(sql);

        stmt.close();
        conn.close();


    }


}

