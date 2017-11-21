package testServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;

public class ServerThread implements Runnable {
	private Socket mSocket = null;
	private BufferedReader mBufferedReader = null;

	public ServerThread(Socket socket) throws UnsupportedEncodingException, IOException {
		mSocket = socket;
		mBufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream(), "utf-8"));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String content = null;

			while ((content = mBufferedReader.readLine()) != null) {
				System.out.println(content);

				for (Iterator<Socket> it = Server.socketList.iterator(); it.hasNext();) {
					Socket s = it.next();

					try {
						OutputStream os = s.getOutputStream();
						os.write((content + "\n").getBytes("utf-8"));
					} catch (SocketException e) {
						e.printStackTrace();
						it.remove();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			Server.socketList.remove(mSocket);
		}
	}
}
