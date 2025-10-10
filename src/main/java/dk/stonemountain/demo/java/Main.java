package dk.stonemountain.demo.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static Logger LOG = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		LOG.info("Starting up: command = {}, command line = {}", ProcessHandle.current().info().command(), ProcessHandle.current().info().commandLine());
		HelloWorld.main(new String[0]);
	}
}