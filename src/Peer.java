import java.io.*;
import java.net.*;

public class Peer {
	//private String peerID;
	//private Socket socket;

	private int port;
	private ServerSocket serverSocket;

	public Peer(int port) throws IOException {
		this.port = port;
		this.serverSocket = new ServerSocket(port);
	}

	public void start() throws IOException {
		System.out.println("Peer started on port " + port);

		while (true) {
			Socket clientSocket = serverSocket.accept();
			new Thread(new ClientHandler(clientSocket)).start();
		}
	}


	public void connectToPeer(String host, int port) throws IOException {
		try (Socket socket = new Socket(host, port);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			out.println("Hello form Peer on port " + this.port);
			String response = in.readLine();
			System.out.println("Received form another Peer: " + response);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Peer <port>");
			return;
		}

		int port = Integer.parseInt(args[0]);
		try {
			Peer peer = new Peer(port);
			//peer.start();
			if (port == 5000) {
				System.out.println("COnnecting");
				peer.connectToPeer("localhost", 5001);
			} else {
				peer.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/*

	public void sendMessage(String Message) throws IOException {
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(message);
	}

	public String receiveMEssage() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		return in.readLine();
*/
}

class ClientHandler implements Runnable {
	private Socket socket;

	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try (BufferedReader in = new BufferedReader(new 
		InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
			String message;
			while ((message = in.readLine()) != null) {
				System.out.println("Received: " + message);
				out.println(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Quiting");
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}


