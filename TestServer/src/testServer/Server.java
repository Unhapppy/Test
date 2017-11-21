package testServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.text.AbstractDocument.Content;

public class Server {
	public static ArrayList<Socket> socketList = new ArrayList<Socket>();
	public static String content = "";

	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(8888);
		
		while (true) {
			Socket s = ss.accept();
			System.out.println("connect success!");
			socketList.add(s);

			new Thread(new ServerThread(s)).start();
		}
	}
}
