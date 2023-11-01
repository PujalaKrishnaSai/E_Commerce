/*package com.example.e_commerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProductList {
    //init tableview wtih product as datatype to display productlist in the tabularform
    public TableView<Product> productTable;
    //create table
    public VBox createTable(){
        //create columns                // "ID"--> display column name in tabular form
        TableColumn<Product,Integer> id = new TableColumn<>("ID");
        //bounding to Product variables with column name so "id"--> is same name as in Product.java class
        id.setCellFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn<>("NAME");
        name.setCellFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("PRICE");
        price.setCellFactory(new PropertyValueFactory<>("price"));
//        TableColumn id = new TableColumn<>("ID");
//        id.setCellFactory(new PropertyValueFactory<>("id"));
//        TableColumn name = new TableColumn<>("NAME");
//        name.setCellFactory(new PropertyValueFactory<>("name"));




        //create data
        ObservableList<Product> data = FXCollections.observableArrayList() ;
      //create and add dummy data to data
      data.add(new Product(2,"iphone",45000));
      data.add(new Product(5,"iphone",60000));


        productTable = new TableView<>();//create table for productList to display
        productTable.setItems(data); //set data items to productTable
         productTable.getColumns().addAll(id, name);//get columns to productTable
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

      //create VBox obj and add productTable to VBox obj
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(productTable);
        return vBox;

    }
}

 */
package com.example.e_commerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public  class ProductList {
    public static TableView<Product> productTable;

    public VBox createTable(ObservableList<Product> data) {
        TableColumn<Product, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product, String> name = new TableColumn<>("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Double> price = new TableColumn<>("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Create data
//        ObservableList<Product> data = FXCollections.observableArrayList();
//        data.add(new Product(2, "iphone", 45000));
//        data.add(new Product(5, "iphone", 60000));

        productTable = new TableView<>();

        productTable.getColumns().addAll(id, name, price);
        productTable.setItems(data);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().add(productTable);
        return vBox;
    }
//   public  VBox getDummyTable(){
//       ObservableList<Product> data = FXCollections.observableArrayList();
//       data.add(new Product(2, "iphone", 45000));
//       data.add(new Product(5, "iphone", 60000));
//       return createTable(data);
//
//
//   }
   public VBox getAllProducts(){
        ObservableList<Product> data = Product.getAllProducts();
        return createTable(data);
   }

   public static Product getSelectedProduct(){
        return productTable.getSelectionModel().getSelectedItem();
   }
   //to display product items in tabular form in cart after add_to_cart action is done
   public VBox getProductInCart(ObservableList<Product> data){
        return createTable(data);
   }
}

