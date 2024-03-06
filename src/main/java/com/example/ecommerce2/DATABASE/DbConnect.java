package com.example.ecommerce2.DATABASE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
    private static DbConnect instance =  null;
    private static Connection con;

    private DbConnect (){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/products", "root", "");
            Statement statement = con.createStatement();
            String PersonneQuery = "CREATE TABLE IF NOT EXISTS shop("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "nomClient VARCHAR(50),"
                    + "idproduct INT ,"
                    + "FOREIGN KEY (idproduct) REFERENCES produit(id)"
                    + ")";
            statement.executeUpdate(PersonneQuery);
            System.out.println("Connection Done");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static DbConnect GetInstance(){
        if (instance == null) {
            return new DbConnect();
        }
        return instance;
    }

    public static Connection getCon() {
        return con;
    }
}
