///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package CA2Project;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
///**
// *
// * @author patri
// */
//public class MainApp
//{
//    static Scanner kb = new Scanner(System.in);
//    
//    public static void main(String args[])
//    {
//        System.out.println("\n-----testing------\n\n");
//        Book b1 = new Book("45681az", "McGoolager", 564, 2.32);
//        Film f1 = new Film("spielberh", 18, 120, "paramount", "tom selleck");
//        
//        System.out.println("b1 : " + b1);
//        System.out.println("f1 : " + f1);
//        
//        ArrayList<Item> arr = new ArrayList<>();
//        
//        arr.add(b1);
//        arr.add(f1);
//        
//        for(Item a : arr)
//        {
//            System.out.println(a);            
//        }
//        System.out.println("\n\n------------------------\n\n");
//        
//        int userInput = -1;
//        while(userInput != 3)
//        {
//            printMainMenu();
//            userInput = getInt("enter vale: ", 1    , 3);
//        }
//        
//    }
//    
//    
//    public static void printMainMenu()
//    {
//        System.out.println("Main menu");
//        System.out.println("1)View Library");
//        System.out.println("2)search fo an item");
//        System.out.println("3) Exit");
//    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    /**
//     * Returns an int entered from the keyboard, limited to range [min - max]
//     *
//     * @param prompt String to prompt the user
//     * @param min Minimum accepted input value
//     * @param max Maximum accepted input value
//     * @return user-entered integer value
//     */
//    public static int getInt(String prompt, int min, int max)
//    {
//        System.out.print(prompt);
//        int value = kb.nextInt();
//        while ((value < min) || (value > max))  // while outside range, re-enter
//        {
//            System.out.println("Invalid - [" + min + "," + max + "] only");
//            System.out.print(prompt);
//            value = kb.nextInt();
//        }
//        kb.nextLine();
//        return value;
//    }
//
//    /**
//     * Returns an int entered from the keyboard, value cant be lower than 0]
//     *
//     * @param prompt String to prompt the user
//     * @return user-entered integer value
//     */
//    public static int getInt(String prompt)
//    {
//        System.out.print(prompt);
//        int value = kb.nextInt();
//        while (value <= 0)  // while outside range, re-enter
//        {
////            System.out.println("Invalid - [" + min + "," + max + "] only");
//            System.out.print("Invalid - needs to be greater than 0 - " + prompt);
//            value = kb.nextInt();
//        }
//        kb.nextLine();
//        return value;
//    }
//
//    /**
//     * Returns an double entered from the keyboard, value greater than 0
//     *
//     * @param prompt String to prompt the user
//     * @return user-entered integer value
//     */
//    public static double getDouble(String prompt)
//    {
//        System.out.print(prompt);
//        double value = kb.nextDouble();
//        while (value <= 0)  // while outside range, re-enter
//        {
////            System.out.println("Invalid - [" + min + "," + max + "] only");
//            System.out.print("Invalid - needs to be greater than 0 - " + prompt);
//            value = kb.nextDouble();
//        }
//        kb.nextLine();
//        return value;
//    }
//
//    /**
//     * Returns an string entered from the keyboard, cant be a blank entry
//     *
//     * @param prompt String to prompt the user
//     * @return user-entered integer value
//     */
//    public static String getString(String prompt)
//    {
//        System.out.print(prompt);
//        String value = kb.nextLine();
//        while (value.equals(""))  // while outside range, re-enter
//        {
////            System.out.println("Invalid - [" + min + "," + max + "] only");
//            System.out.print("Invalid - cant be a blank entry - " + prompt);
//            value = kb.nextLine();
//        }
//        return value;
//    }
//    
//}
