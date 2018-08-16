package model.config;

import java.sql.*;

public class CrudDataBase {
    
    private int id;
    private String name = "";
    private String cont;
    private String alias_doc;


    public String finByAlias(String alias) throws SQLException, ClassNotFoundException {


        String sql = ("SELECT* FROM documents WHERE alias='" + alias + "'");

        String string= setQuery(sql);

        return string ;
    }

    public String finByName(String name) throws SQLException, ClassNotFoundException {

        String sql = ("SELECT* FROM documents WHERE name='" + name + "'");

        String string= setQuery(sql);

        return string ;
    }



    private String setQuery(String query) throws ClassNotFoundException, SQLException {

         final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
         final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        // static final String DB_URL = "jdbc:mysql://localhost:3306/mojabaza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
         final String USER = "postgres";
         final String PASS = "admin";


        Connection conn = null;
        Statement stmt = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {

            id = rs.getInt("id");
            name = rs.getString("name");
            cont = rs.getString("content");
            alias_doc = rs.getString("alias");
        }

        rs.close();
        stmt.close();
        conn.close();

        return "Id:  " + id + ", name:  " + name + ", Content:  " + cont + ", Alias:  " + alias_doc;

    }

}

