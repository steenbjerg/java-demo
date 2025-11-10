package dk.stonemountain.demo.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ApplicationController {
    @FXML
    private TableView<Car> carTable; // The TableView from FXML
    
    @FXML
    private TableColumn<Car, String> brandColumn;
    
    @FXML
    private TableColumn<Car, String> nameColumn;
    
    @FXML
    private TableColumn<Car, String> typeColumn;
    
    ObservableList<Car> carList = FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
        // Initialize the table columns
        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        
        // Initialize the table
        carTable.setItems(carList);
    }

    @FXML
    private void doAddRows() {
        carList.add(new Car("Toyota", "Corolla", "Sedan"));
        carList.add(new Car("Honda", "Civic", "Sedan"));
        carList.add(new Car("Ford", "Mustang", "Coupe"));
        carList.add(new Car("Chevrolet", "Camaro", "Coupe"));
        carList.add(new Car("Dodge", "Charger", "Coupe"));
        carList.add(new Car("Chevrolet", "Camaro", "Coupe"));
        carList.add(new Car("Chevrolet", "Camaro", "Coupe"));
        carList.add(new Car("Chevrolet", "Camaro", "Coupe"));
        carList.add(new Car("Porsche", "911", "Coupe"));
        carList.add(new Car("Porsche", "911", "Coupe"));
        carList.add(new Car("Porsche", "911", "Coupe"));
    }

    @FXML
    private void doQuit() {
        // Get the current stage from any node in the scene
        Stage stage = (Stage) carTable.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void doAbout() {
        // Implementation for About action
        System.out.println("About action triggered");
    }
}