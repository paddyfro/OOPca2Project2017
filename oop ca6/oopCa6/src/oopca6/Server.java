/*
*Patrick McDonnell D00006968
 */
package oopca6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author patri
 */
public class Server {

    public static void main(String[] args) throws IOException {
        int portNumber = 4444;
        System.out.println("This is the server..");
        System.out.println("waiting for connection...");

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
            String inputLine, outputLine;
            Protocol kkp = new Protocol();

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Server: recieved request: " + inputLine);
                outputLine = kkp.processInput(inputLine);
                System.out.println("sending server-> client:  " + outputLine);
                out.println(outputLine);
                if (outputLine.equals("bye.")) {
                    break;
                }

            }
        } catch (IOException e) {
            System.out.println("exception caught while trying to listen on port " + portNumber + " or lstening for a connection");
            System.out.println(e.getMessage());
        }
    }

}
