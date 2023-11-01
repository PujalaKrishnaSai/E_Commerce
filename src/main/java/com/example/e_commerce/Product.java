package com.example.e_commerce;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
  //create constructor for variable


    public Product(int id, String name,double price) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }
    public static ObservableList<Product> getAllProducts(){
        String  selectAllProducts ="SELECT id,name,price FROM product";
        return fetchProductData(selectAllProducts);
    }
   public static ObservableList<Product> fetchProductData(String query){
       ObservableList<Product> data = FXCollections.observableArrayList();
       DBconnection connection = new DBconnection();
       try{
           ResultSet rs = connection.getQueryTable(query);
           while(rs.next()){
               //fetching values from reultset from DB of customer table in ecommerce database
               Product product = new Product(rs.getInt("id"),rs.getString("name"),
                    rs.getDouble("price"));
               data.add(product);


           }
           return data;
       }
       catch(Exception e){
           e.printStackTrace();
       }
       return null;
   }


 //create getters for all variables
    //remove getters of SimpleIntegerProperty()

    public int getId() {
        return id.get();
    }
    public String getName() {
        return name.get();
    }
    public double getPrice() {
        return price.get();
    }


}
