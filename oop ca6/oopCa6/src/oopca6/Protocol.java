/*
*Patrick McDonnell D00006968
 */
package oopca6;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author patri
 */
public class Protocol {

    public String processInput(String theInput) {
        String outputJson = null;
//        System.out.println(theInput);
        ArrayList<Integer> listOfNumbers = new ArrayList<>();
        java.lang.reflect.Type type = new TypeToken<List<Integer>>() {
        }.getType();
        java.lang.reflect.Type typeDouble = new TypeToken<List<Double>>() {
        }.getType();
        Gson gson = new Gson();
        
        double min = 99999;
        double max = 0;
        double average = 0;
        ArrayList<Double> results = new ArrayList<>();
        
        listOfNumbers = gson.fromJson(theInput, type);
        for(int num : listOfNumbers){
//            System.out.println(num);
            if(num > max){
                max = num;
            }
            if(num<min){
                min = num;
            }
            average+=num;
        }
        average = average / listOfNumbers.size();
        System.out.println("max: " + max);
        System.out.println("min: " + min);
        System.out.println("avg: " + average);
        results.add(max);
        results.add(min);
        results.add(average);
        outputJson = gson.toJson(results, type);
        
//        output = theInput;
//        ArrayList<String> input = new ArrayList<>();
//        StringTokenizer st = new StringTokenizer(theInput, ",");
//        while (st.hasMoreTokens()) {
//            input.add(st.nextToken());
//        }
////        System.out.println("array 0 : " + input.get(0));
////        output = input.get(0);
//        if (input.get(0).equalsIgnoreCase("sumprod")) {
//            output = productSum(input);
//        } else {
//            output = "error with command";
//        }
        return outputJson;

    }

//    public String productSum(ArrayList<String> theInput) {
//        String output = "";
//        int num = Integer.parseInt(theInput.get(1));
//        int sumTotal = 0;
//        int productTotal = 1;
//        for (int i = 1; i <= num; i++) {
//            sumTotal += i;
//            productTotal *= i;
//        }
//        output = sumTotal + "," + productTotal;
//        return output;
//    }

//    java.lang.reflect.Type type = new TypeToken<List<Integer>>(){                
//            }.getType();
//            Gson gson = new Gson();
//            json = 
}
