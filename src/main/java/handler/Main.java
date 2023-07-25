package handler;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class Main {
	
	public static final int threadPool = 64;
	public static final int port = 9999;
	
	public static void main(String[] args) throws IOException {
		
		final HTTPServer server = HTTPServer.getInstance(port, threadPool);
		
		server.addHandler("GET", "/messages", new Handler() {
			public void handle(Request request, BufferedOutputStream responseStream) throws IOException {
				String hello = "hi";
				responseStream.write(("HTTP/1.1 200 ok" + "/r/n" +
					"Content-Type: " + "text/plain" + "/r/n" +
					"Content-Length: " + hello.length() + "/r/n" +
					"Connection: close/r/n" +
					"/r/n").getBytes());
			}
		});
		server.addHandler("POST", "/messages", new Handler() {
			public void handle(Request request, BufferedOutputStream responseStream) {
				// TODO: handlers code
			}
		});
		
	}
	
}
