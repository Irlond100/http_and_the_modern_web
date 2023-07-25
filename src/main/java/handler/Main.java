package handler;

public class Main {
	
	private static final int port = 9999;
	private static final int poolSize = 64;
	
	public static void main(String[] args) {
		
		// final var server = new HTTPServer().getInstance(port, poolSize);
		//
		// server.addHandler("GET", "/messages",  new Handler() {
		// 	public void handle(Request request, BufferedOutputStream responseStream) throws IOException {
		// 		responseStream.write(("HTTP/1.1 200 ok" + "/r/n" +
		// 			"Content-Type: " + "text/plain" + "/r/n" +
		// 			"Content-Length: " + "hi" + "/r/n" +
		// 			"/r/n").getBytes());
		// 	}
		// });
		// server.addHandler("POST", "/messages", new Handler() {
		// 	public void handle(Request request, BufferedOutputStream responseStream) {
		// 		// TODO: handlers code
		// 	}
		// });
	}
	
}
