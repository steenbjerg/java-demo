package dk.stonemountain.demo.java;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {	
	private static final Logger LOG = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		LOG.log(Level.INFO, "Starting up: command = {0}, command line = {1}", new Object[] { ProcessHandle.current().info().command(), ProcessHandle.current().info().commandLine() });
		HelloWorld.main(new String[0]);
	}
}