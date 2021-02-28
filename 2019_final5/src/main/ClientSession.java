package main;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientSession {
	
	private Socket socket;
	// streams
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	public final static int PORTNUMBER = 10001;
	public final static String IP="localhost";


	public ClientSession() {
		// intialize connection
		// create Socket

		try {
			this.socket = new Socket(IP, PORTNUMBER);
			this.oos = new ObjectOutputStream(this.socket.getOutputStream());
			this.ois = new ObjectInputStream(this.socket.getInputStream());
			
		} catch (SocketException e) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initialize(Socket clientSocket) {
	}

	public void finalize() {
		
	}

	public Object invoke(String object, String methodName, Object parameter) {
		Object result = null;
		try {
			//write object
			this.oos.writeObject(object);
			this.oos.flush();
			this.oos.writeObject(methodName);
			this.oos.flush();
			this.oos.writeObject(parameter);
			this.oos.flush();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			//read result
			result = ois.readObject();
		} catch(EOFException e) {
			System.exit(0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.ois.close();
			this.oos.close();
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}
}

