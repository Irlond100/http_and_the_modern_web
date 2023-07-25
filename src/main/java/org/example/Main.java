package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	
	public static final ExecutorService threadPool = Executors.newFixedThreadPool(64);
	private static final int port = 9999;
	
	public static void main(String[] args) {
		Server.start(threadPool, port);
	}
	
}
