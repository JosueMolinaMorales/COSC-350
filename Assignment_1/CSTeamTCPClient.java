import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class CSTeamTCPClient {
    private static String HOST = "localhost";
    private static int PORT = 6789;

    public static void main(String[] args) {
        try{	
            /**
             * 1.1.1
             * Makes a TCP connection to the localhost server on port 6789
             */
            Socket socket = new Socket(HOST,PORT);
            
            DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
            
            toServer.writeUTF("Hello Server");
            toServer.flush();
            
            String webPage = getWebPageFromServer(socket);

            // Connect to the webpage that was sent from the server
            System.out.printf("Connecting to %s\n", webPage);
            
            // Make a HTTP GET request to the web page
            long startTime = System.nanoTime();
            byte[] input = makeGetRequest(webPage);
            long endTime = System.nanoTime();

            long duration = TimeUnit.NANOSECONDS.toMillis(endTime-startTime);
            System.out.printf("Time it took for the GET request in milliseconds is: %dms\n", duration);

            // Send over the WebPage String, Number of bytes and the time back to the server.
            toServer.writeUTF("Webpage: " + webPage);
            toServer.writeUTF("Size of the response: " + input.length + " bytes");
            toServer.writeUTF("Time it took to make the request: " + duration + "ms");
            toServer.flush();

            toServer.close();
            socket.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Attempts to read a webpage url from the server, that socket is connected to
     * @param socket The socket
     * @return the webpage
     */
    public static String getWebPageFromServer(Socket socket) {
        if (!socket.isConnected()){
            throw new RuntimeException("Socket is not connected");
        }
        // Accept the web server name from the server
        String webPage = "";
        try{
            DataInputStream serverInput = new DataInputStream(socket.getInputStream());
            webPage = (String)serverInput.readUTF();
            System.out.println("Webpage sent from the server: " + webPage);
        } catch (Exception e) {
            System.out.println("Error attempting to read from the server");
            System.exit(-1);
        }
        return webPage;
    }

    public static byte[] makeGetRequest(String webPage) {
        byte[] input = null;

        try{
            //Make a URL connection to the webpage
            URL url = new URL("https", webPage, "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            // set the request method to GET
            connection.setRequestMethod("GET");

            // Get input stream 
            InputStream webPageInput = connection.getInputStream();

            // Print what was received from the get request
            input = webPageInput.readAllBytes();
            //System.out.println(new String(input)); //Uncomment this to see the HTML

            // Print the number of bytes of the response
            System.out.println("The size (in bytes) of response of the GET request was: " + input.length);

        } catch (Exception e) {
            System.out.println("Error making the GET request");
            System.exit(-1);
        }
        return input;

    }
    
}