package handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServer implements Runnable {
	
	private static HTTPServer INSTANCE = null;
	private final ExecutorService threadPool;
	private final ServerSocket serverSocket;
	private final ConcurrentHashMap<String, ConcurrentHashMap<String, Handler>> handlers = new ConcurrentHashMap<>();
	
	public HTTPServer(int port, int pool) throws IOException {
		serverSocket = new ServerSocket(port);
		threadPool = Executors.newFixedThreadPool(pool);
	}
	
	public static synchronized HTTPServer getInstance(int port, int threadPool) throws IOException {
		if (INSTANCE == null) {
			synchronized (HTTPServer.class) {
				if (INSTANCE == null) {
					INSTANCE = new HTTPServer(port, threadPool);
				}
			}
		}
		return INSTANCE;
	}
	
	public ConcurrentHashMap<String, ConcurrentHashMap<String, Handler>> getHandlers() {
		return handlers;
	}
	
	public void addHandler(String method, String path, Handler handler) {
		var methodMap = handlers.get(method);
		
		if (methodMap == null) {
			methodMap = new ConcurrentHashMap<>();
			handlers.put(method, methodMap);
		}
		
		if (!methodMap.contains(path)) {
			methodMap.put(path, handler);
		}
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				threadPool.execute(new ConnectionHandler(serverSocket.accept(), this));
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
