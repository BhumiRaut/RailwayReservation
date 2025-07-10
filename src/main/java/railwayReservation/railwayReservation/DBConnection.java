package railwayReservation.railwayReservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/railway_db";
    private static final String USER = "postgres";
    private static final String PASS = "root";

    public static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

//public static void main(String[] args)
//{
//createTable("railway_db","train");
//}
//
//static void createdb(String DBName)
//{
//try{
//Connection conn = DriverManager.getConnection(URL,USER,PASS);
//String query = ("CREATE DATABASE "+DBName);
//Statement stmt = conn.createStatement();
//stmt.execute(query);
//System.out.println("Database created");
//stmt.close();
//conn.close();
//}catch(SQLException e)
//{
//e.printStackTrace();
//}
//}
//
////static void createTable(String DBName, String TBName)
////{
////try{
////Connection conn = DriverManager.getConnection(URL,USER,PASS);
////String createTableQuery = "CREATE TABLE IF NOT EXISTS "+TBName+"
////}
////}
}
