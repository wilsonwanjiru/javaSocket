import java.io.*;
import java.net.*;

public class javaSocket {
   public static void main(String[] args) {
		int serverPort = 3000;
		ServerSocket serverSocket = null;
		ObjectOutputStream toClient = null;
		ObjectInputStream fromClient = null;
        ObjectInputStream msgRequest = null;
		try {
			serverSocket = new ServerSocket(serverPort);
// delay thread f0r 100 miliseconds and cause the run() methods to execute
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("Just connected to " + 
					socket.getRemoteSocketAddress());
				toClient = new ObjectOutputStream(
					new BufferedOutputStream(socket.getOutputStream()));
				fromClient = new ObjectInputStream(
					new BufferedInputStream(socket.getInputStream()));
				 Message msgRequest =(Message) fromClient.readObject();
				int number = msgRequest.number;
				toClient.writeObject(new Message(number*number));
				toClient.flush();
			}
		}
// interrupted excecutions must be caught while sleep is called
		catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
   }
}