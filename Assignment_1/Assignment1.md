COSC 350			       Assignment 1					  

Due Tuesday Sep. 30(11:59 pm). Upload the files F1, F2, F3, F4 for your group (named exactly as indicated with your group name) via Blackboard:

F1. GroupNameA1TCPClient.java

F2. GroupNameA1TCPServer.java

F3. GroupNameA1WSAnswers.pdf

F4. GroupNameA1WS.pcap

1 Write a Java TCP socket program to do the following (modify the relevant client and server code on Blackboard). Submit your socket code in the files F1 and F2 (named as above). 

1.1 The client 

 1.1.1 makes a TCP connection to the localhost server (LS) on port 6789. <br>
 1.1.2 reads the string with the Web server name W sent to it by the server LS. <br>
 1.1.3 makes a TCP connection on port 80 to the Web server W.<br>
 1.1.4 creates a correct HTTP Get request automatically and sends it to the Web server W.<br>
 1.1.5 prints the text (as is) from the initial page that is received from the Web server W (it should not be an error message since the Get request is correct).<br>
 1.1.6 finds and prints the total number of bytes N received from the Web server W.<br>
 1.1.7 finds and prints the total time T in milliseconds between sending the Get request and receiving all the text in the page<br>
 1.1.8 sends the string W, and the values of N and T to LS over the TCP connection on port 6789.<br>
 1.1.9 closes the connection to LS.<br>

1.2 The localhost server (LS) 
  1.2.1 asks the user to enter a single string with the server name W of a Web server to connect to (for example: www.towson.edu). User will only enter the preceding input (exactly as specified).<br>
  1.2.2 reads the string with the Web server name W entered by the user.<br>
  1.2.3 waits for the client to connect to it on TCP port 6789.<br>
  1.2.4 sends the string with the Web server name W to the client when the client connects.<br>
  1.2.5 reads and prints the string W, and the values of N and T sent to it by the client. <br> 

2 Run Wireshark (WS) and use your browser to make a request to http://www.towson.edu. Identify the packets in the WS capture that are needed to answer the questions below (click on parts of packets to get information if needed). Submit a file F3 (named as above) that has all your answers. Submit an actual WS .pcap file F4 (named as above) that includes all the relevant packets. In your answers, indicate the WS frame numbers for the specific packets you use. <br>
2.1 Find the packet that has the first HTTP GET request due to this browser request.<br>
2.1.1 Indicate the destination IP address and destination port number in the packet. Explain why this specific address and port number are used.<br>
2.1.2 Indicate each protocol in the packet and the size of its protocol header (excluding HTTP).<br>
2.1.3 Find the total number of bytes of HTTP data only (exclude the sizes of the other protocol headers). <br>
2.1.4 List the actual bytes (hex values) in the packet that indicate the HTTP request method is GET. <br> 
2.2 Find the packet that has the first DNS response due to this browser request.<br>
2.2.1 Indicate the source IP address and source port number in the packet. Explain why this specific address and port number are used.<br>
2.2.2 Indicate each protocol in the packet and the size of its protocol header (excluding DNS).<br>
2.2.3 Find the total number of bytes in the part of the packet that corresponds to the authoritative nameservers.<br>
2.2.4 The data length field in the answers part of the packet has value 4. Why is this value 4, and how many bytes are used to indicate this data length value? <br>
