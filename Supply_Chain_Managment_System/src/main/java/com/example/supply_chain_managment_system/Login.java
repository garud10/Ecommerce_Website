package com.example.supply_chain_managment_system;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {
    private static byte[] getSha(String input){
        try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String getEncryptedPassword(String password){
        try {
            BigInteger num = new BigInteger(1, getSha(password));
            StringBuilder hexString = new StringBuilder(num.toString(16));
            return hexString.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Customer customerLogin(String userEmail , String password){
        String encryptedPassword = getEncryptedPassword(password);
        String Query = "SELECT * FROM customers WHERE email = '"+userEmail+"' AND password = '"+encryptedPassword+"'";
        Database_Connection DBconn = new Database_Connection();
        try {
            ResultSet rs = DBconn.getQueryTable(Query);
            if(rs != null && rs.next()){
                return new Customer(rs.getInt("cid"),
                                    rs.getString("name"),
                                    rs.getString("email"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean insertUserDetail(String name, String userEmail , int mobileNo, String password){
        password = getEncryptedPassword(password);
       String Query = "INSERT INTO customers(name, email, mobile_no, password)"+
        "VALUES('"+ name +"', ' " + userEmail +"', "+ mobileNo +", '"+ password +"')";
        Database_Connection DBconn = new Database_Connection();
       boolean result =  DBconn.insertUpdate(Query);
       return result;
   }
    public static void main(String[] args){
        //System.out.println(customerLogin("sanketgarud10@gmail.com", "sssgarud"));
       // System.out.println(getEncryptedPassword("sssgarud"));
    }
}
