package com.example.e_commerce;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;


public class UserInterface {
    GridPane loginpage; //init the name of  GridPane here
    HBox headerbar ;//init the name of HBox here for searchbar
    HBox footerBar; //(buyNowbutton) button in horizontal in footerbar space
    Button signInbutton; // setting signInbutton as global variable becoz to remove signInbutton after action is done
    Label userWelcomeMsg;//(global variable) to display mesg welcome after successfull login
    VBox body;//for footer bar to add buy now and order buttons here
    Customer loggedInCustomer;//to store customer details whos is login ,
                             // so 1st i will create variable of Customer
     ProductList productList = new ProductList();//init ProductList class here
    VBox productPage;   //init VBox here of ProductList.java
    Button placedOrderButton = new Button("Place Order");// placed order button create in footerbar in cart page
    ObservableList<Product> itemsInCart= FXCollections.observableArrayList();//selected products items to fetch in cart

    public BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setPrefSize(800,600);//set size of pane
        root.setTop(headerbar); //set in top place in pane

        //root.getChildren().add(loginpage);// methods are added as child to pane(here parent)
        //root.setCenter(loginpage);// setting loginpages method to centres of pane by using borderpane
                                  // not happen until we set alignment position of loginpage in centre
        body = new VBox();         //obj create(footer bar)
        body.setPadding(new Insets(10)); //setting padding to body(footer bar)
        body.setAlignment(Pos.CENTER); // setting body to center alinment
        root.setCenter(body);     //setting (footer bar) to center in pane


       productPage = productList.getAllProducts();
       // root.setCenter(productPage);
        body.getChildren().add(productPage);//adding productpage to body(footer bar )
        root.setBottom(footerBar);
        return root;
    }
    // create construction for useInterface otherwise throws child null exception
    // to call createloginpage methods--> calls Grids loginpage--->calls createContent()
    public UserInterface(){
        createloginpage();
        createHeaderBar();
        createFooterBar();
    }
     private void createloginpage(){
         Text userNametext = new Text("User Name");
         TextField usernamefield = new TextField("krishna@gmail.com");
         usernamefield.setPromptText("enter the username");

         Text passwordtext = new Text("Password");
         PasswordField passwordfield = new PasswordField();
         passwordfield.setText("abc123");
         passwordfield.setPromptText("Enter the password");

         Label mesglabel = new Label("hello baby");//

         Button loginbutton = new Button("Login"); // create button which displays as  name Login

         loginpage = new GridPane(); // create obj of GridPane
         loginpage.setStyle(" -fx-background-color:grey; ");//setting color to pane
         loginpage.setAlignment(Pos.CENTER);//setting username and password alignment to centre
         loginpage.setHgap(10); // setting horizontal gap b/w username and password
         loginpage.setVgap(10); // setting vertical gap b/w username and password
         loginpage.add(userNametext,0,0); // add username text to gridPane
         loginpage.add(usernamefield,1,0); // add username textfield to gridpane
         loginpage.add(passwordtext,0,1);// add password text to gridpane
         loginpage.add(passwordfield,1,1); // add password field to grid pane
         loginpage.add(mesglabel,0,2);

         loginpage.add(loginbutton,1,2);// add loginbutton to gridpane
         loginbutton.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 String name = usernamefield.getText();
                 String pass = passwordfield.getText();
                 Login login = new Login();
                 loggedInCustomer = login.customerlogin(name,pass);
                // mesglabel.setText(username);
                 //two possibilties if successful or failed
                 //for time been print message
                 if(loggedInCustomer != null) {
                     mesglabel.setText("customer" + loggedInCustomer.getName());//to display user_Name beside login button
                     userWelcomeMsg.setText("welcome"+loggedInCustomer.getName());//to display welcome with user_Name beside search button
                     headerbar.getChildren().add(userWelcomeMsg);// adding to welcome mesg to header bar
                     body.getChildren().clear();
                     body.getChildren().add(productPage);

                 }
                 else {
                     mesglabel.setText("login failed please provide correct credentials");
                 }

             }
         });





     }

     // create header bar at top in horizontal by using HBox pane




    private void createHeaderBar(){
        Button homeButton = new Button("Home");// crate homeButton here
        Image image = new Image("D:\\java-programes\\E_Commerce\\src\\istockphoto-1340117122-1024x1024.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(80);
        homeButton.setGraphic(imageView);//adding imageview to home_button
        //adding search bar in HBox
        TextField searchbar = new TextField();// create field for searchbar to search
        searchbar.setPromptText("Search bar");// set prompt text for searchbar
        Button searchbutton = new Button("Search");// create button for search button
        signInbutton = new Button("Sign In"); // becoz of global variable;
        userWelcomeMsg = new Label();//displaying welcoming after succesful login
        Button cartButton = new Button("Cart"); // adding cartbutton beside search bar(ie headerbar)
        Button ordersButton = new Button("Orders"); // adding orders button


        headerbar = new HBox(10);//create obj for headerbar
        // with giving specific space b/w components
        //similary to headerbar.setSpacing(10);
        headerbar.setPadding(new Insets(20));   // setting padding means keeping space b/w headerbar and frame

        headerbar.setAlignment(Pos.CENTER);// setting in center position
        headerbar.getChildren().addAll(homeButton,searchbar,searchbutton,signInbutton,cartButton,ordersButton);//
        //adding action when sign_in button is clicked
        signInbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                body.getChildren().clear(); //clearing the page ,to look like plain page
                body.getChildren().add(loginpage); // adding login page to body here
                headerbar.getChildren().remove(signInbutton);// removing signInbutton after action event is performed
                // above one OR below one are having same agenda
                //signInbutton.setVisible(false);

            }
        });
        //action event on click of CartButton
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                VBox productItemsInCart = productList.getProductInCart(itemsInCart);
                productItemsInCart.setAlignment(Pos.CENTER);
                productItemsInCart.setSpacing(10);
                productItemsInCart.getChildren().add(placedOrderButton);
                body.getChildren().add(productItemsInCart);
                footerBar.setVisible(false);

            }
        });
        //action event of place_order_Button click
        placedOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //need product and a customer
                if(itemsInCart == null){
                    //please select the order before buying
                    showDialog("please add some products in cart to place order");

                    return;

                }
                if(loggedInCustomer==null){
                    showDialog("please login first");

                    return;
                }

                int count = Order.placeMutlipleOrder(loggedInCustomer, itemsInCart);
                if(count!=0){
                    showDialog("order "+count+" products are placed successful");
                }
                else {
                    showDialog("ordered failed");
                }

            }
        });
        //action event on homeButton click
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(productPage);
                footerBar.setVisible(true);
                if(loggedInCustomer==null && headerbar.getChildren().indexOf(signInbutton)==-1){
                    headerbar.getChildren().add(signInbutton);

                }
            }
        });
    }

    private void createFooterBar(){

        Button buyNowButton = new Button("BUY NOW");// create button for Buy_Now button
        Button addToCartButton = new Button("Add to cart");//create addtocart button beside BUY_NOW button
        footerBar = new HBox(10);//create obj for footerbar
        footerBar.setPadding(new Insets(20));   // setting padding means keeping space b/w headerbar and frame
        footerBar.setAlignment(Pos.CENTER);// setting in center position
        footerBar.getChildren().addAll(buyNowButton,addToCartButton);//

        //event action on Buy_now button
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = ProductList.getSelectedProduct();
                if(product == null){
                    //please select the order before buying
                    showDialog("please select the order before buying");

                    return;

                }
                if(loggedInCustomer==null){
                    showDialog("please login first");

                    return;
                }

               boolean status = Order.placeOrder(loggedInCustomer,product);
                if(status==true){
                    showDialog("order successful");
                }
                else {
                    showDialog("ordered failed");
                }
            }
        });
        //event action on AddToCart button
        addToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = ProductList.getSelectedProduct();
                if(product == null){
                    //please select the order before buying
                    showDialog("please select the product before add to cart");

                    return;

                }
                itemsInCart.add(product);
                showDialog("product is added to cart successful");

            }
        });



    }

    private void showDialog(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle("Message");
        alert.showAndWait();


    }







}
