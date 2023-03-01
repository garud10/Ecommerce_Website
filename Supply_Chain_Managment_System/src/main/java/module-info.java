module com.example.supply_chain_managment_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supply_chain_managment_system to javafx.fxml;
    exports com.example.supply_chain_managment_system;
}