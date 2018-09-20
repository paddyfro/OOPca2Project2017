/*

*Patrick McDonnell D00006968
 */
package oopca6;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author patri
 */
public class MainApp {

    public static void main(String[] args) throws IOException {
        Scanner kb = new Scanner(System.in);
        String hostName = "localhost";
        int portNumber = 4444;
        System.out.println("Port number: " + portNumber);
        System.out.println("this is teh client: ");
        System.out.println("connecting to severer....");

        try (
                Socket kkScoket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkScoket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(kkScoket.getInputStream()))) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer = "";
            String userInput = "";

            ArrayList<Integer> listOfNumbers = new ArrayList<>();
            java.lang.reflect.Type type = new TypeToken<List<Integer>>() {
            }.getType();
            Gson gson = new Gson();

            System.out.println("list of numbers, end with negative number:");
//            System.out.println("sumprod,\"number\"");
            int userNum = kb.nextInt();
            while (userNum >= 0) {
                listOfNumbers.add(userNum);
//                System.out.println("list of numbers, end with negative number:");
//            System.out.println("sumprod,\"number\"");
                userNum = kb.nextInt();
            }
            String json = gson.toJson(listOfNumbers, type);
//            userInput = kb.nextLine();
            System.out.println("Sending (Client-> Server) : " + json);
            //output data to sevrer
            out.println(json);

            //reads in data from servere
            fromServer = in.readLine();

            System.out.println("received from server: " + fromServer);
            java.lang.reflect.Type typeDouble = new TypeToken<List<Double>>() {
        }.getType();
            ArrayList<Double> resultsFromServer = gson.fromJson(fromServer, typeDouble);
            System.out.println("max: " + resultsFromServer.get(0));
            System.out.println("min: " + resultsFromServer.get(1));
            System.out.println("avg: " + resultsFromServer.get(2));

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to "
                    + hostName);
            System.exit(1);
        }

    }

}
