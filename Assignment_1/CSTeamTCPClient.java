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
            
            dout.close();
            socket.close();
        
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}