package dk.stonemountain.demo.java;

import java.util.logging.Logger;

public class Main {	
	private static final Logger LOG = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		LOG.info(() -> String.format("Starting up: command = {0}, command line = {1}", ProcessHandle.current().info().command(), ProcessHandle.current().info().commandLine()));
		HelloWorld.main(new String[0]);
	}
}