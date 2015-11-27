package com.HackDev.App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseOps {
    
    
    public static Connection getConnection()
    {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/telcomfine?zeroDateTimeBehavior=convertToNull", "root", "");
        } catch (SQLException e) {
            Logger.getLogger(DatabaseOps.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }
    
    public static void main(String[] args) {
           HashMap<String, SLPDdatabase> map = new HashMap<String, SLPDdatabase>();
        
           Statement st = null;
           ResultSet rs = null;
           Connection con = getConnection();
           SLPDdatabase data;
           try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM telcomfine");
               while (rs.next()) {       
                   String mobile = "12052";
                   String reference = rs.getString("Reference_No");
                   String id = rs.getString("User_ID_No");
                   String amount = rs.getString("Amount");
                   data = new SLPDdatabase(reference, id, amount);
                   map.put(id, data);
               }
        } catch (Exception e) {
            e.printStackTrace();
        }
           
           for(String i : map.keySet())
           {
               SLPDdatabase database = map.get(i);
               System.out.println(database.getreferenceNo()+"  "+database.getcustomerIDNo()+""+database.getamount());
           }
    }


           
}
