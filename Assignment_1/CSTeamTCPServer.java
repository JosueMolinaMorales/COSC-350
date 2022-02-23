import java.io.*;
import java.net.*;

public class CSTeamTCPServer {
    public static void main(String[] args){
        try{
            // Create a socket object bound to port 6789
            ServerSocket serverSocket = new ServerSocket(6789);

            // Listens for a connection to be made to this socket and accepts it.
            // This returns a new socket
            Socket clientSocket = serverSocket.accept();//establishes connection 
            
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            
            String str = (String)dis.readUTF();
            System.out.println("message from client is: " + str);
            
            // Send client a web server name
            DataOutputStream toClient = new DataOutputStream(clientSocket.getOutputStream());
            toClient.writeUTF("https://github.com");
            toClient.flush();

            toClient.close();
            serverSocket.close();
        
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
