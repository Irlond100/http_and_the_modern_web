package handler;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
	
	private final Socket socket;
	private final HTTPServer server;
	
	ConnectionHandler(Socket socket, HTTPServer server) {
		this.socket = socket;
		this.server = server;
	}
	
	@Override
	public void run() {
		
		try (
			final var in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			final var out = new BufferedOutputStream(this.socket.getOutputStream());
		)
		{
			
			final var request = Request.parse(in);
			
			if (request == null) {
				out.write((
					"""
						HTTP/1.1 400 Bad Request \r
						Content-Type: 0\r
						Connection: close\r
						\r
						"""
				).getBytes());
				out.flush();
				return;
			}
			
			var methodMap = (server.getHandlers().get(request.getMethod()));
			if (methodMap == null) {
				out.write((
					"""
						HTTP/1.1 404 Not Fount \r
						Content-Type: 0\r
						Connection: close\r
						\r
						"""
				).getBytes());
				out.flush();
				return;
			}
			
			var handler = methodMap.get(request.getPath());
			if (handler == null) {
				out.write((
					"""
						HTTP/1.1 404 Not Fount \r
						Content-Type: 0\r
						Connection: close\r
						\r
						"""
				).getBytes());
				out.flush();
				return;
			}
			
			handler.handle(request, out);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

