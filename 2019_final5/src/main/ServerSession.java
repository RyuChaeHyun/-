package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.SocketException;

public class ServerSession extends Thread{
	
	private Socket clientSocket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public void initialize(Socket clientSocket) {
	      try {
	         this.clientSocket = clientSocket;   
	         this.oos = new ObjectOutputStream(this.clientSocket.getOutputStream());
	         this.ois = new ObjectInputStream(this.clientSocket.getInputStream());
			System.out.println("Server::initialize");
			
	      } catch (SocketException e) {
	    	  System.out.println("Close");
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
	   
	   public void finalize() {
	     
	   }
	   
	   public void run() {
		   try {
			   String classname = (String) this.ois.readObject();
			   String methodname = (String) this.ois.readObject();
			   Object parameter = this.ois.readObject();
			   if (classname.equals("Quit")) {
				   this.quitServer();
			   } else {


				   Class Management = Class.forName("control." + classname);
				   Object instance;
				   try {
					   instance = Management.newInstance();
					   Method method = Management.getMethod(methodname, Object.class);
					   Object result = method.invoke(instance, parameter);
					   this.oos.writeObject(result);
					   this.oos.flush();

				   } catch (SocketException e) {
					   System.out.println("Close");
				   } catch (InstantiationException e1) {
					   // TODO Auto-generated catch block
					   e1.printStackTrace();
				   }


				   try {
					   this.oos.close();
					   this.ois.close();
					   this.clientSocket.close();

				   } catch (SocketException e) {
					   System.out.println("Close");
				   } catch (IOException e) {
					   e.printStackTrace();
				   }
				   System.out.println("Server::finalize");
			   }
		   } catch (SocketException e) {
			   System.out.println("Close");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	   public void quitServer() {
		   try {
			   this.oos.close();
			   this.ois.close();
			   this.clientSocket.close();
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		   System.exit(0);
	   }
}

