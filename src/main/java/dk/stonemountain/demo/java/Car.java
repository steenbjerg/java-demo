package dk.stonemountain.demo.java;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;

public class Car {
    public enum Type {
        Sedan,
        Coupe,
        Hatchback,
        Convertible;

        public static class DisplayConverter extends StringConverter<Type> {
            @Override
            public String toString(Type type) {
                if (type == null) return null;
                return type.name().toLowerCase();
            }

            @Override
            public Type fromString(String s) {
                return null;
            }
        }
    }

    private StringProperty brand = new SimpleStringProperty();   
    private StringProperty name = new SimpleStringProperty();
    private ObjectProperty<Type> type = new SimpleObjectProperty<>();
    

    public Car() {
    }
    
    public Car(String brand, String name, Type type) {
        this.brand.set(brand);
        this.name.set(name);
        this.type.set(type);
    }

    public Car(Car other) {
        this.brand.set(other.brand.get());
        this.name.set(other.name.get());
        this.type.set(other.type.get());
    }

    public void clear() {
        this.brand.set("");
        this.name.set("");
        this.type.set(Type.Sedan);
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
    
    public final ObjectProperty<Type> typeProperty() {
        return type;
    }
    
    public final Type getType() {
        return typeProperty().get();
    }
    
    public final void setType(Type value) {
        typeProperty().set(value);
    }
    
    @Override
    public String toString() {
        return "Car{" + "brand=" + getBrand() + ", name=" + getName() + ", type=" + getType() + '}';
    }

    public void update(Car other) {
        this.brand.set(other.brand.get());
        this.name.set(other.name.get());
        this.type.set(other.type.get());
    }

}
