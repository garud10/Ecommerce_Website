package com.example.supply_chain_managment_system;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.io.IOException;

import static com.example.supply_chain_managment_system.Login.insertUserDetail;

public class Ecommerce extends Application {
    productList productList = new productList();
    Pane root = new Pane();

    private final int width = 500, height = 400, headerLine = 25;
    Pane bodyPane ;

    Label welcomeLable = new Label();

    Customer loggedInCustomer = null;
    private GridPane headerBar(){
        GridPane header = new GridPane();
        header. setStyle("-fx-background-color: #87CEFA; ");
        header.setTranslateX(1);
        Button signInButton = new Button("Sign In");
        signInButton.setStyle("-fx-background-color: #F5FFFA;");

        TextField searchBar = new TextField();
        Button searchButton = new Button("search");
        searchButton.setStyle("-fx-background-color: #F5FFFA;");

        Button backButton = new Button("Back");
        backButton. setStyle("-fx-background-color: #F08080; ");

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().addAll(productList.getAllProducts());
            }
        });

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().addAll(signInPage());
            }
        });

        header.setHgap(10);
        header.add(searchBar,0,0);
        header.add(searchButton,1,0);
        header.add(signInButton,2,0);
        header.add(welcomeLable,3,0);
        header.add(backButton,15,0);
        return header;
    }

    public void showDialogue(String massage){
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Order Status");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText(massage);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();

    }
    private GridPane footerBar(){
        GridPane footer = new GridPane();
        footer. setStyle("-fx-background-color: #87CEFA; ");
        footer.setTranslateX(1);
        footer.setTranslateY(headerLine+height);

        Button buyNow = new Button("Buy Now");
        buyNow.setStyle("-fx-background-color: #F5FFFA;");

        Button AddToCart = new Button("Add To Cart");
        AddToCart.setStyle("-fx-background-color: #F5FFFA;");

        Button goToCart = new Button("Go To Cart");
        goToCart.setStyle("-fx-background-color: #F5FFFA;");

        goToCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    if (loggedInCustomer != null) {
                        bodyPane.getChildren().clear();
                        bodyPane.getChildren().addAll(productList.getAddProduct(loggedInCustomer.getId()));
                    } else {
                        showDialogue("Select Poduct!");
                    }
            }
        });
        AddToCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                boolean orderStatus= false;
                    if (product != null && loggedInCustomer != null) {
                        orderStatus = Order.addToCart(loggedInCustomer, product);
                    }
                    if (orderStatus == true) {
                        showDialogue("Added to cart Successfully!!");
                    } else {
                        showDialogue("Select Poduct!");
                    }
            }
        });
        buyNow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                boolean orderStatus= false;
                if(product != null && loggedInCustomer != null){
                    orderStatus = Order.placeOrder(loggedInCustomer,product);
                }
                if(orderStatus == true){
                    showDialogue("Order Successful. Thank You!");
                }
                else{
                    showDialogue("Select Poduct!");
                }
            }
        });
        footer.setHgap(10);
        footer.add(buyNow,0,0);
        footer.add(AddToCart,1,0);
        footer.add(goToCart,28,0);

        return footer;
    }
    private GridPane signInPage(){
        Label userLable = new Label(" User_email ");
        userLable.setFont(Font.font(15));
        userLable.setStyle("-fx-background-color: #F0E68C;");

        Label passLable = new Label(" Password  ");
        passLable.setFont(Font.font(15));
        passLable.setStyle("-fx-background-color: #F0E68C;");

        Label nameLable = new Label("    Name     ");
        nameLable.setFont(Font.font(15));
        nameLable.setStyle("-fx-background-color: #F0E68C;");

        Label mobileLable = new Label("Mobile No ");
        mobileLable.setFont(Font.font(15));
        mobileLable.setStyle("-fx-background-color: #F0E68C;");

        TextField userEmail = new TextField();
        userEmail.setPromptText("Enter User Email");
        userEmail.setFont(Font.font(15));

        TextField name = new TextField();
        name.setPromptText("Enter User Name");
        name.setFont(Font.font(15));

        TextField mobileNo = new TextField();
        mobileNo.setPromptText("Enter User Number");
        mobileNo.setFont(Font.font(15));

        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");
        password.setFont(Font.font(15));

        Button signInButton = new Button("Sign-In");
        Label messageLable = new Label("        Sign-In - Message        ");
        messageLable.setFont(Font.font(15));
        messageLable.setStyle("-fx-background-color: #F0E68C;");

        GridPane signInPane = new GridPane();
        signInPane.setVgap(5);
        signInPane.setHgap(5);

        signInPane.setTranslateY(100);
        signInPane.setTranslateX(100);
        signInPane.add(userLable, 0,0);
        signInPane.add(userEmail,1,0);
        signInPane.add(nameLable, 0,2);
        signInPane.add(name,1,2);
        signInPane.add(mobileLable,0,4);
        signInPane.add(mobileNo,1,4);
        signInPane.add(passLable,0,6);
        signInPane.add(password,1,6);
        signInPane.add(signInButton,0,8);
        signInPane.add(messageLable,1,8);

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = userEmail.getText();
                String pass = password.getText();
                String mobile = mobileNo.getText();
                int No = Integer.parseInt(mobile);
                String Name = name.getText();
                loggedInCustomer = Login.customerLogin(user,  pass);
                if(loggedInCustomer == null){
                    insertUserDetail(Name, user , No,  pass);
                    messageLable.setText("Sign-In  Successfully!!");
                }
                else{
                    System.out.println("User already present");
                }
            }
        });
        return signInPane;
    }
    private GridPane loginPage(){
        Label userLable = new Label("User_name :");
        userLable.setFont(Font.font(20));
        userLable.setStyle("-fx-background-color: #F0E68C;");

        Label passLable = new Label("Password   :");
        passLable.setFont(Font.font(20));
        passLable.setStyle("-fx-background-color: #F0E68C;");

        TextField userName = new TextField();
        userName.setPromptText("Enter User Name");
        userName.setFont(Font.font(15));

        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");
        password.setFont(Font.font(15));

        Button loginButton = new Button("Login");
        loginButton. setStyle("-fx-background-color: #6495ED;");

        Label messageLable = new Label("Login - Message       ");
        messageLable.setFont(Font.font(20));
        messageLable.setStyle("-fx-background-color: #F0E68C;");



        GridPane loginPane = new GridPane();
        loginPane.setHgap(5);
        loginPane.setVgap(5);
        loginPane.setTranslateY(130);
        loginPane.setTranslateX(130);
        loginPane.add(userLable, 0,0);
        loginPane.add(userName,1,0);
        loginPane.add(passLable,0,1);
        loginPane.add(password,1,1);
        loginPane.add(loginButton,0,2);
        loginPane.add(messageLable,1,2);



        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    String user = userName.getText();
                    String pass = password.getText();
                    loggedInCustomer = Login.customerLogin(user, pass);
                if(loggedInCustomer != null){
                    messageLable.setText("Login Successfully!!");
                    welcomeLable.setText("Welcome " + loggedInCustomer.getName());
                    root.getChildren().clear();
                    root.getChildren().addAll(bodyPane,footerBar(),headerBar());
                }
                else{
                    messageLable.setText("Invalid User");
                }
            }
        });


        return loginPane;
    }

    private Pane createcontent(){
        root.setPrefSize(width ,height+2*headerLine);

        bodyPane = new Pane();
        bodyPane.setStyle("-fx-background-color: #000000;");
        bodyPane.setPrefSize(width,height);
        bodyPane.setTranslateY(headerLine);
        bodyPane.getChildren().addAll(loginPage());

        root.getChildren().addAll(bodyPane,headerBar());
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(Ecommerce.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createcontent());
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}