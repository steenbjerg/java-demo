package dk.stonemountain.demo.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {
    private static final Logger LOG = LoggerFactory.getLogger(JavaFXApplication.class);
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        LOG.info("Application starting up");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/application.fxml"));
        Parent root = loader.load();
        
        // Optional: Access the controller if needed
        // ApplicationController controller = loader.getController();
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}