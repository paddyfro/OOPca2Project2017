/*
*Patrick McDonnell D00006968
 */
package oopca6.question1;

import java.util.Scanner;

/**
 *
 * @author patri
 */
public class MainApp {
    public static void main(String[] args) {
        
   //issue with very alrge numbers, long is used, but BigINteger would be optimal
        
        Scanner kb = new Scanner(System.in);
        System.out.println("Enter number to calcuate: ");
        int userNumber = kb.nextInt();
        System.out.println("");
        System.out.println("Number: " + userNumber);
        
        try{
        System.out.println("Thread time baby!");
        Product productNum = new Product(userNumber);
        Sum SumNum = new Sum(userNumber);
        
        Thread t1 = new Thread(productNum);
        Thread t2 = new Thread(SumNum);
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
            System.out.println("threads have finished");
            System.out.println("sum ("+userNumber + "): " + SumNum.getSum());
            System.out.println("product ("+userNumber + "): " + productNum.getProduct());
        }catch(InterruptedException ex){
            //do nothing
        }
        
    }
    
}
