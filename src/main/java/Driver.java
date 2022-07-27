import java.sql.*;

//TODO: eventually delete later, since it's not used for anyting
public class Driver {
    public static void main(String[] args) {
        System.out.println("hereee");
        try {
            /* TODO: A static method of the Class class. It loads the  specified driver */
//            Class.forName("org.cj.mysql.jdbc.Driver"); // tutorial did this...
            String url = "jdbc:mysql://127.0.0.1:3306/mydb";
            Connection conn =  DriverManager.getConnection(url, "root", "root"); // tut has no password
            PreparedStatement execStat=conn.prepareStatement("SELECT * FROM employees");
            ResultSet rs = execStat.executeQuery();

            // extract data from result set
            while (rs.next()) {
                String name = rs.getString("name");
                int eid = rs.getInt("eid");
                int age = rs.getInt("age");
                System.out.println(name);
                System.out.println(eid);
                System.out.println(age);
            }

            // if query isnt known till runtime => use preparedstatement
            // this is like injection, giving a string to a statement
            PreparedStatement newStat =  conn.prepareStatement(
                    "INSERT INTO employees(name, eid, age)  VALUES(?,?,?)");
            newStat.setString(1, "hi");
            newStat.setInt(2, 5); // eid of 5
            newStat.setInt(3, 7); // age of 7
//            newStat.executeUpdate();

            // if full query is known
            Statement stat = conn.createStatement();
            String query = "SELECT age FROM employees WHERE name = \"sina\"";
            ResultSet exec = stat.executeQuery(query);
            System.out.println(exec);

            // TODO: use executeQuery for pure queries (?)
            // TODO: for statements that change the DB (insert, delete etc) use executeUpdate

        } catch (Exception ex) {
            // handle the error
            ex.printStackTrace();
            System.out.println(ex);
        }
    }
}