/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hadil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author WINDOWS 10
 */
public class database {
    
    public static Connection connectDB(){
        
        String url="jdbc:mysql://localhost:3308/tasck";

        try {
           Connection connect =  DriverManager.getConnection(url,"root",""); // root is the default username while "" or empty or null is the default password
            System.out.println("connection il ya a");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    
    }
}
    
    

