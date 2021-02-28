package main;

import UI.LoginFrame;
import UI.MainFrame;

//실행함수 main(String[] args)을 포함하는 클래스
public class ClientMain {
	private MainFrame mainFrame;
	private LoginFrame loginFrame;

	public ClientMain() {
		this.loginFrame = new LoginFrame();
		
	}
	
	public void run() {
		
		this.loginFrame.setVisible(true);
	}

	public static void main(String[] args) {
		//memory allocation & invoke constructor
		ClientMain main = new ClientMain();
		main.run();
		Client client = new Client();
		client.initialize();
		client.run();
		client.finalize();
	}
}
