package com.example.supply_chain_managment_system;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class productList {

    public TableView<Product> productTable;

    public TableView<Product> cartProductTable;

    public Pane getAllProducts(){
        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Product> productList = Product.getAllProducts();
        productTable = new TableView<>();
        productTable.setItems(productList);
        productTable.getColumns().addAll(id, name, price);

        Pane tablePane = new Pane();
        tablePane.getChildren().add(productTable);

        return tablePane;
    }

    public Pane getAddProduct(int customerId){
        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Product> productList = Product.getAddProduct(customerId);
        cartProductTable = new TableView<>();
        cartProductTable.setItems(productList);
        cartProductTable.getColumns().addAll(id, name, price);

        Pane tablePane = new Pane();
        tablePane.getChildren().add(cartProductTable);

        return tablePane;
    }
    public Product getSelectedProduct(){
        // getting selected product
        return productTable.getSelectionModel().getSelectedItem();
    }
}
