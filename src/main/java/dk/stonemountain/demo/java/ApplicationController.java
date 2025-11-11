package dk.stonemountain.demo.java;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ApplicationController {
    @FXML private TableView<Car> carTable;
    @FXML private TableColumn<Car, String> brandColumn;
    @FXML private TableColumn<Car, String> nameColumn;
    @FXML private TableColumn<Car, String> typeColumn;
    @FXML private TextField entityBrand;
    @FXML private TextField entityName;
    @FXML private ChoiceBox<Car.Type> entityType;
    
    ObservableList<Car> carList = FXCollections.observableArrayList();
    Car editableCar = new Car();
    
    @FXML
    private void initialize() {
        // Initialize the table and the columns
        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        typeColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getType().name(), cellData.getValue().typeProperty()));

        carTable.setItems(carList);

        // Initialize the form
        entityType.setItems(FXCollections.observableArrayList(Car.Type.values()));
        entityType.setConverter(new Car.Type.DisplayConverter());
        entityType.setValue(Car.Type.Sedan);
        
        entityBrand.textProperty().bindBidirectional(editableCar.brandProperty());
        entityName.textProperty().bindBidirectional(editableCar.nameProperty());

        entityType.valueProperty().bindBidirectional(editableCar.typeProperty());

        carTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            editableCar.update(newSelection);
        });
    }

    @FXML
    private void doAddRows() {
        carList.add(new Car("Toyota", "Corolla", Car.Type.Sedan));
        carList.add(new Car("Honda", "Civic", Car.Type.Sedan));
        carList.add(new Car("Ford", "Mustang", Car.Type.Coupe));
        carList.add(new Car("Chevrolet", "Camaro", Car.Type.Coupe));
        carList.add(new Car("Dodge", "Charger", Car.Type.Coupe));
        carList.add(new Car("Chevrolet", "Camaro", Car.Type.Coupe));
        carList.add(new Car("Chevrolet", "Camaro", Car.Type.Coupe));
        carList.add(new Car("Chevrolet", "Camaro", Car.Type.Coupe));
        carList.add(new Car("Porsche", "911", Car.Type.Coupe));
        carList.add(new Car("Porsche", "911", Car.Type.Coupe));
        carList.add(new Car("Porsche", "911", Car.Type.Coupe));
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

    @FXML
    private void doCancel() {
        editableCar.update(carTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void doSave() {
        Car car = carTable.getSelectionModel().getSelectedItem(); 
        car.update(editableCar);
    }
}