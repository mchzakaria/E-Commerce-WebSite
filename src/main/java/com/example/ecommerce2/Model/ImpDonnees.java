package com.example.ecommerce2.Model;

import com.example.ecommerce2.DATABASE.DbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ImpDonnees implements IDonnes{
    Connection con = DbConnect.GetInstance().getCon();
    @Override
    public void save(Donnees donnees, String name) {
        String req = "INSERT INTO shop (nomClient,idproduct) VALUES (?,?) ";
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1,name);
            ps.setInt(2,donnees.getId());
            if(ps.executeUpdate() == 1){
                System.out.println("Added Succesfully ");
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
}
