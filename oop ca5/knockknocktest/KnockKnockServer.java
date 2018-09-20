/** SERVER                                           OOP 12-03-2018
 * KnockKnockServer - Server side of (TCP) client/server interaction
 * (Deals with one client only )
 *
 * Reference:  (based on)
 * https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 * 

 */
package knockknocktest;
/*
 * Name: Patrick McDonnell
 * ID: D00006968
 * Course: Bachelor of Science (Honours) in Computing in DKIT
 * Subject:Object Orientated Programming
 * 25/04/2018
 */
import Exceptions.DaoException;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KnockKnockServer implements Runnable {
    private int port;

    public KnockKnockServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        int portNumber = this.port;

        System.out.println("This is the Server program...");
        System.out.println("Waiting for client to connect ...");

        try ( // try-with-resources style - will autoclose sockets

                // create a (TCP) ServerSocket that will wait for a connection 
                // request from a client on specified port.
                ServerSocket serverSocket = new ServerSocket(portNumber);
                // listen at the port; block/wait until a connection is received.
                // When a connection is received, create a NEW Socket that 
                // establishes a connection between the new socket and the client.
                Socket clientSocket = serverSocket.accept();
                // get Output and Input streams from the socket for 
                // communicating with the Client
                PrintWriter out
                = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())); // connection is now established, using the socket,
                // with input and output streams
                ) {

            String inputLine, outputLine;

            // Initiate conversation with client
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            //outputLine = kkp.processInput(null);
            //out.println(outputLine);  // will send "Knock, Knock" message

            // in.readline() waits for (and gathers) input from the socket. 
            // It returns when a newline ('\n') is encountered, and the
            // while loop is executed using the returned data.
            //  
            // readline() will return 'null' when the stream is ended,
            // which happens when the socket is closed (by the client).
            // 
            while ((inputLine = in.readLine()) != null) {  // get data until socket is closed
                System.out.println("Server: Received request: " + inputLine);

                outputLine = kkp.processInput(inputLine);
//                System.out.println(outputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye.")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } catch (DaoException ex) {
            Logger.getLogger(KnockKnockServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
