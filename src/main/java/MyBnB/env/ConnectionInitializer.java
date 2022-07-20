package MyBnB.env;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionInitializer {

  // Have each of these variables defined in your .MyBnB.env file !!!
  public static String CONNECTION_STRING = "CONNECTION_STRING";
  public static String USERNAME = "USERNAME";
  public static String PASSWORD = "PASSWORD";

  public static Connection getDBConnection(){
    Dotenv dotenv = Dotenv.load();
    String connectURL = dotenv.get(CONNECTION_STRING);
    String username = dotenv.get(USERNAME);
    String password = dotenv.get(PASSWORD);

    if (connectURL == null || username == null || password == null){
      System.out.println("Could not get values from .MyBnB.env file");
      return null;
    }

    try{
      return DriverManager.getConnection(connectURL, username, password);
    }catch(SQLException e){
      System.out.println("Could not connect to the database");
      return null;
    }
  }

  public static void printSampleDBQuery() throws SQLException {
    Connection conn = getDBConnection();
    PreparedStatement execStat=conn.prepareStatement("SELECT * FROM User");
    ResultSet rs = execStat.executeQuery();

    // extract data from result set
    while (rs.next()) {
      String name = rs.getString("name");
      String sin = rs.getString("sin");
      int eid = rs.getInt("id");
      System.out.println(name);
      System.out.println(eid);
      System.out.println(sin);
    }
  }


}
