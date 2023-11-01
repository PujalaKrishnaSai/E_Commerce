
package com.example.e_commerce;

import javax.xml.transform.stream.StreamSource;
import java.sql.ResultSet;

public class Login {
    public Customer customerlogin(String userid,String password){
        String query=
                "SELECT * FROM customer WHERE email='"+userid+"' AND password='"+password+"'";
        //create connection DB
        DBconnection connection = new DBconnection();
        try{
            ResultSet rs = connection.getQueryTable(query);
            if(rs.next()){
                //fetching values from reultset from DB of customer table in ecommerce database
                return new Customer(rs.getInt("id"),rs.getString("name"),
                        rs.getString("email"),rs.getString("mobile"));

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        //create login obj
        Login login = new Login();
        //
        Customer customer=login.customerlogin("krishna@gmail.com","abc123");
        //print name of userid if successful login
        System.out.println("welcome: "+customer.getName());

    }

}


