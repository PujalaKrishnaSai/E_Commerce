package com.example.e_commerce;

import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Order {
    static boolean placeOrder(Customer customer, Product product){
        String groupOrderId ="select max(group_order_id) +1 id from orders"; //
        //connection to DB
        DBconnection dBconnection = new DBconnection();
        try{
            ResultSet rs = dBconnection.getQueryTable(groupOrderId);
            if(rs.next()){
                String placeOrder="insert into orders(group_order_id,customer_id,product_id) values("+rs.getInt("id")+","+customer.getId()+","+product.getId()+")";
                return dBconnection.updateDatabase(placeOrder)!=0;
            }
        }
        catch (Exception e){ e.printStackTrace();}
        return false;
    }
    static int placeMutlipleOrder(Customer customer, ObservableList<Product> productList){
        String groupOrderId ="select max(group_order_id) +1 id from orders"; //
        //connection to DB
        DBconnection dBconnection = new DBconnection();
        try{
            ResultSet rs = dBconnection.getQueryTable(groupOrderId);
            int count=0;
            if(rs.next()){
                for(Product product : productList){
                    String placeOrder="insert into orders(group_order_id,customer_id,product_id) values("+rs.getInt("id")+","+customer.getId()+","+product.getId()+")";
                    count += dBconnection.updateDatabase(placeOrder);
                }
                return count;
            }
        }
        catch (Exception e){ e.printStackTrace();}
        return 0;
    }


}
