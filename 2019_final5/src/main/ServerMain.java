package main;

public class ServerMain {
	
	public static void main(String[] args) {
		Server server = new Server();
		server.initialize();
		server.run();
//		server.finalize();
	}
}
