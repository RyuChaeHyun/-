package main;

import java.awt.FileDialog;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import UI.Memo;


public class Server {
	private ServerSocket serverSocket;
	public final static int PORTNUMBER = 10001;
	public final static String IP="localhost";


	public void initialize() {
	      try {
	         this.serverSocket = new ServerSocket(this.PORTNUMBER); 
	      } catch (SocketException e) {
	    	  System.out.println("Close");
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
	   
	   public void finalize() {
	      try {
	         this.serverSocket.close();
	      } catch (SocketException e) {
	    	  System.out.println("Close");
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
	   public void run() {
		      try {
		         //wait & create session
		         boolean bRunning = true;         
		         while(bRunning) {
		            //client request for connection
		            Socket clientSocket = serverSocket.accept();
		            //create service for client
		            ServerSession serverSession = new ServerSession();
		            serverSession.initialize(clientSocket);
		            serverSession.start();
		            //close connection
//		            session.finish();
		         }
		      } catch (SocketException e) {
		    	  System.out.println("Close");
		      } catch (IOException e) {
		         e.printStackTrace();
		      }
}
	   
}
