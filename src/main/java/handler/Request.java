package handler;

import java.io.BufferedReader;
import java.io.IOException;

public class Request {
	
	private String method;
	private String path;
	private String version;
	
	public Request(String method, String path, String version) {
		this.method = method;
		this.path = path;
		this.version = version;
	}
	
	public static Request parse(BufferedReader in) throws IOException {
		final var requestLine = in.readLine();
		final var parts = requestLine.split(" ");
		if (parts.length != 3) {
			return null;
		}
		return new Request(parts[0], parts[1], parts[2]);
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
}
