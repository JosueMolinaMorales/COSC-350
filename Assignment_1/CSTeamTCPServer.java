import java.io.*;
import java.net.*;
import java.util.*;

public class CSTeamTCPServer {
    public static void main(String[] args){
        try{

            // Get webpage from the user
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter a webpage to make a GET request to: ");
            String userWebPage = scan.nextLine();
            
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
            toClient.writeUTF(userWebPage);
            toClient.flush();

            // Get the information from the client about the webpage
            String webPage = (String)dis.readUTF();
            String size_inBytes = (String)dis.readUTF();
            String duration_request = (String)dis.readUTF();
            
            System.out.println(webPage + "\n" + size_inBytes + "\n" + duration_request);

            toClient.close();
            serverSocket.close();
            scan.close();
        
        }catch(Exception e){
            System.out.println("An error occurred. Please reset the connection...");
        }
    }
}
