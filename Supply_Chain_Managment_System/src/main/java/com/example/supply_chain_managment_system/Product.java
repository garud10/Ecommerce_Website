package com.example.supply_chain_managment_system;

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

    public int getId(){
        return id.get();
    }

    public String getName(){
        return name.get();
    }

    public Double getPrice(){
        return price.get();
    }

    public Product(int id, String name, Double price){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public static ObservableList<Product> getAllProducts(){
        String Query = "SELECT * FROM products";
        return getProducts(Query);
    }

    public static ObservableList<Product> getAddProduct(int id){
        String query = "SELECT p.pid,p.name, p.price FROM products as p INNER JOIN cart AS c ON p.pid = c.productId AND c.customerId =" + id;
        return getProducts(query);
    }
    public static ObservableList<Product> getProducts(String Query){
        Database_Connection DBconn = new Database_Connection();
        ResultSet rs = DBconn.getQueryTable(Query);
        ObservableList<Product> result = FXCollections.observableArrayList();

        try {
            if(rs != null){
                while (rs.next()){
                    result.add(
                            new Product(
                                    rs.getInt("pid"),
                                    rs.getString("name"),
                                    rs.getDouble("price")
                            )
                    );
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
