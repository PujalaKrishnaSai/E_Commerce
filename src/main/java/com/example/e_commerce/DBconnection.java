package com.example.e_commerce;


import java.sql.*;

public class DBconnection {
    private final String dburl= "jdbc:mysql://localhost:3306/ecommerce";
    private final String username="root";
    private final String password="Kri$#n@007";
    private Statement getStatement(){
        try{ Connection connection =DriverManager.getConnection(dburl,username,password);
            return connection.createStatement();

        }
        catch(Exception e){
            e.printStackTrace();

        }
        return null;
    }
    public ResultSet getQueryTable(String query){
        try {
            Statement statement=getStatement();
            return statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //for order class
   public int updateDatabase(String query){
       try {
           Statement statement=getStatement();
           return statement.executeUpdate(query);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return 0;


   }

    public static void main(String[] args) {
       DBconnection dBconnection = new DBconnection();
       ResultSet rs = dBconnection.getQueryTable("SELECT * FROM customer");
       if(rs!=null)
           System.out.println("connection successful");
       else
           System.out.println("connection Failed");


    }
}
