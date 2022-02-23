import java.io.*;
import java.net.*;

public class CSTeamTCPClient {
    public static void main(String[] args) {
        try{	
            /**
             * 1.1.1
             * Makes a TCP connection to the localhost server on port 6789
             */
            Socket socket = new Socket("localhost",6789);
            
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            
            dout.writeUTF("Hello Server");
            dout.flush();
            
            // Accept the web server name from the server
            DataInputStream serverInput = new DataInputStream(socket.getInputStream());
            String webPage = (String)serverInput.readUTF();
            System.out.println("Webpage sent from the server: " + webPage);

            // Connect to the webpage that was sent from the server
            System.out.printf("Connecting to %s\n", webPage);
            
            // Make a HTTP GET request to the web page
            URL url = new URL(webPage);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream webPageInput = connection.getInputStream();

            byte[] input = webPageInput.readAllBytes();
            System.out.println("Size: " + input.length);
            System.out.println(new String(input));
            

            dout.close();
            socket.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}