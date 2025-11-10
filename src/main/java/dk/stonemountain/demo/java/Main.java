package dk.stonemountain.demo.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.application.Application;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) {
        LOG.info("Starting up: command = {}, command line = {}", 
                ProcessHandle.current().info().command(), 
                ProcessHandle.current().info().commandLine().orElse(""));
        
        // Launch the JavaFX application
        Application.launch(JavaFXApplication.class, args);
    }
}