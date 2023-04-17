import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Connect_DB {
 public static Connection createConnection()
 {
     Connection con = null;
     String url = "jdbc:mysql://localhost:3306/project?characterEncoding=utf8"; //MySQL URL and followed by the database name
     String username = "ooad_project"; //MySQL username
     String password = "ooad_password"; //MySQL password   
     try 
     {
         try 
         {
            Class.forName("com.mysql.jdbc.Driver"); //loading mysql driver 
            System.out.println("Connected to driver");
         } 
         catch (ClassNotFoundException e)
         {
            System.out.println("Driver not found");
            e.printStackTrace();
         } 
         try {
            con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
            System.out.println("Printing connection object "+con);
         }
         catch (SQLException e){
            System.out.println("Connection failed");
            e.printStackTrace();
         }
         // con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
         // System.out.println("Printing connection object "+con);
     } 
     catch (Exception e) 
     {
        e.printStackTrace();
     }
     return con; 
 }
}
