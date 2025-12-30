package dk.stonemountain.demo.java;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXApplication extends Application {
    private static final Logger LOG = Logger.getLogger(JavaFXApplication.class.getName());
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        LOG.info(() -> String.format("Application starting up: %s", getParameters().getRaw()));
        var parameters = getParameters();
		// parse command line arguments
		Optional<Command> command = Optional.empty();
		if (parameters.getUnnamed().size() == 1) {
			var cmd = readCommand(parameters.getUnnamed().getFirst());
			LOG.info(() -> String.format("Command: %s", cmd));
			command = Optional.of(cmd);
		}

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/application.fxml"));
        Parent root = loader.load();
        
        ApplicationController controller = loader.getController();
        controller.handleCommand(command);
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static Command readCommand(String url) {
		LOG.info(() -> String.format("Reading url: %s", url));
		if (url.startsWith("fs-java-demo://view?")) {
			var queryParametersString = url.substring("fs-java-demo://view?".length());
			return parseViewCommand(queryParametersString);
		}

		throw new IllegalArgumentException("Unknown command: " + url);
	}

	private static ViewCommand parseViewCommand(String parameters) {
		var queryParameters = parameters.split("&");
		Optional<String> brand = Optional.empty();
		Optional<String> name = Optional.empty();

		for (var queryParameter : queryParameters) {
			var key = queryParameter.split("=")[0];
			var value = queryParameter.split("=")[1];
			LOG.info(() -> String.format("Query parameter: %s = %s", key, value));
			
			if ("brand".equals(key)) {
				brand = Optional.ofNullable(value);
			} else if ("name".equals(key)) {
				name = Optional.ofNullable(value);
			} else {
				LOG.warning(() -> String.format("Unknown query parameter: %s", key));
			}
		}

		var command = new ViewCommand(brand, name);
		return command;
	} 
}