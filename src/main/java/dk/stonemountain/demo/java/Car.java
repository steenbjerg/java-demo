package dk.stonemountain.demo.java;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Car {
    private StringProperty brand = new SimpleStringProperty();   
    private StringProperty name = new SimpleStringProperty();
    private StringProperty type = new SimpleStringProperty();
    

    public Car() {
    }
    
    public Car(String brand, String name, String type) {
        this.brand.set(brand);
        this.name.set(name);
        this.type.set(type);
    }

    public final StringProperty brandProperty() {
        return brand;
    }
    
    public final String getBrand() {
        return brandProperty().get();
    }
    
    public final void setBrand(String value) {
        brandProperty().set(value);
    }
    
    public final StringProperty nameProperty() {
        return name;
    }
    
    public final String getName() {
        return nameProperty().get();
    }
    
    public final void setName(String value) {
        nameProperty().set(value);
    }
    
    public final StringProperty typeProperty() {
        return type;
    }
    
    public final String getType() {
        return typeProperty().get();
    }
    
    public final void setType(String value) {
        typeProperty().set(value);
    }
    
    @Override
    public String toString() {
        return "Car{" + "brand=" + getBrand() + ", name=" + getName() + ", type=" + getType() + '}';
    }

}
