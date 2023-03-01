package com.example.supply_chain_managment_system;
import java.sql.*;
public class Database_Connection {

    String dbURL = "jdbc:mysql://localhost:3306/ecommerce";

    String username = "root";

    String password = "SSSgarud10@";


    private Statement getStatement(){
        try{
            Connection conn =  DriverManager.getConnection(dbURL, username, password);
            return conn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getQueryTable(String Query){
        Statement statement = getStatement();
        try {
            return statement.executeQuery(Query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean insertUpdate(String Query) {
        Statement statement = getStatement();
        try {
             statement.executeUpdate(Query);
             return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

      /*  public static void main(String[] args) {

            String query = "SELECT * FROM products";
            Database_Connection dbConn = new Database_Connection();
            boolean rs = dbConn.insertUpdate(query);
            if(rs != false){
                System.out.println("Connected To Database");
            }
        }*/
}
