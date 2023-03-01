package com.example.supply_chain_managment_system;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Order {
    TableView<Product> orderTable;
    public Pane getOrders(Customer customer){
        String query = "select orders.oid, products.name, products.price from orders inner join products on orders.product_id=products.pid where customer_id="+customer.getId();
        Database_Connection dbConn = new Database_Connection();
        ResultSet rs = dbConn.getQueryTable(query);
        ObservableList<Product> res = FXCollections.observableArrayList();
        try{
            while(rs.next()){
                res.add(new Product(rs.getInt("oid"),rs.getString("name"),rs.getDouble("price")));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return tableFromList(res);
    }
    public  Pane tableFromList(ObservableList<Product> productList){

        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        orderTable = new TableView();
        orderTable.setItems(productList);
        orderTable.getColumns().addAll(id,name,price);

        Pane TablePane = new Pane();
        TablePane.getChildren().add(orderTable);

        return TablePane;
    }
    public static boolean placeOrder(Customer customer, Product product){
            try {
                String placeOrder = "INSERT INTO ORDERS (Customer_id,product_id,status) VALUES (" + customer.getId() +"," + product.getId() +",'Ordered')";
                Database_Connection DBconn = new Database_Connection();
                return DBconn.insertUpdate(placeOrder);
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;
    }

    public static boolean addToCart(Customer customer, Product product){
        try {
            String addCart = "INSERT INTO CART (customerId, productId) VALUES ("+customer.getId()+","+product.getId()+")";
            Database_Connection DBconn = new Database_Connection();
            return DBconn.insertUpdate(addCart);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
