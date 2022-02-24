import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class CSTeamTCPClient {
    public static void main(String[] args) {
        try{	
            /**
             * 1.1.1
             * Makes a TCP connection to the localhost server on port 6789
             */
            Socket socket = new Socket("localhost",6789);
            
            DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
            
            toServer.writeUTF("Hello Server");
            toServer.flush();
            
            // Accept the web server name from the server
            DataInputStream serverInput = new DataInputStream(socket.getInputStream());
            String webPage = (String)serverInput.readUTF();
            System.out.println("Webpage sent from the server: " + webPage);

            // Connect to the webpage that was sent from the server
            System.out.printf("Connecting to %s\n", webPage);
            
            // Make a HTTP GET request to the web page
            URL url = new URL("https", webPage, "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            long startTime = System.nanoTime();
            InputStream webPageInput = connection.getInputStream();
            long endTime = System.nanoTime();

            // Print what was received from the get request
            byte[] input = webPageInput.readAllBytes();
            System.out.println(new String(input));

            // Print the number of bytes of the response
            System.out.println("The size (in bytes) of response of the GET request was: " + input.length);

            // Print the time it took to make the get request
            long duration = TimeUnit.NANOSECONDS.toMillis(endTime-startTime);
            System.out.printf("Time it took for the GET request in milliseconds is: %dms", duration);

            // Send over the WebPage String, Number of bytes and the time back to the server.
            toServer.writeUTF("Webpage: " + webPage);
            toServer.writeUTF("Size of the response: " + input.length + " bytes");
            toServer.writeUTF("Time it took to make the request: " + duration + "ms");
            toServer.flush();

            toServer.close();
            socket.close();
            serverInput.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}