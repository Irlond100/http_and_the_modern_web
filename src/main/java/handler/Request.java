package handler;

import java.io.BufferedReader;
import java.io.IOException;

public class Request {
	
	private final String method;
	private final String path;
	
	public Request(String method, String path) {
		this.method = method;
		this.path = path;
	}
	
	public static Request parse(BufferedReader in) throws IOException {
		final var requestLine = in.readLine();
		final var parts = requestLine.split(" ");
		if (parts.length != 3) {
			// just close socket
			return null;
		}
		
		return new Request(parts[0], parts[1]);
	}
	
	public String getMethod() {
		return method;
	}
	
	public String getPath() {
		return path;
	}
	
}
