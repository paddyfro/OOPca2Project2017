/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author corma
 */
public class utility
{
    public static double sumOfArray(double[] array)
    {
        double total = 0;
        for(int i = 0 ; i <=array.length;i++){
            total += array[i];
        }
        return total;
    }
    
    
    public static int[] fillArray()
    {
        Random rnd = new Random();
        int[] array = new int[10];

        for (int i = 0; i < 10; i++) {
           array[i] = rnd.nextInt(100);
        } 
        return array;
    }

    public static void displayArray(int[] array)
    {
        for (int i = 0; i < array.length; i++) {
           System.out.print("\narray[" + i + "] = " + array[i] + ", ");
        }
    }
    
    public static void displayArray(double[] array)
    {
        for (int i = 0; i < array.length; i++) {
           System.out.print(array[i]+",");
        }
    }
    
    
    public static void displayArray(int[] array , int[] array2)
    {
        for (int i = 0; i < array.length; i++) {
           System.out.print("\narray[" + i + "] = " + array[i] + ", ");
        }
        System.out.println("\n\n");
        for (int i = 0; i < array.length; i++) {
           System.out.print("\narray[" + i + "] = " + array2[i] + ", ");
        }
    }
   
    public static void displayArray(int[] array , int[] array2 , int[] array3)
    {
        for (int i = 0; i < array.length; i++) {
           System.out.print("\narray[" + i + "] = " + array[i] + ", ");
        }
        
        System.out.println("\n\n");
        
        for (int i = 0; i < array.length; i++) {
           System.out.print("\narray[" + i + "] = " + array2[i] + ", ");
        }
        
        System.out.println("\n\n");
        
        for (int i = 0; i < array.length; i++) {
           System.out.print("\narray[" + i + "] = " + array3[i] + ", ");
        }
    }
    
    
    public static void twoArraysEqualPrint(int[] array1 , int[] array2){
        
        Arrays.sort(array1);
        Arrays.sort(array2);
        if(Arrays.equals(array1, array2)==true){
            utility.displayArray(array1);
            System.out.println("\n\nIs Equal To\n\n");
            utility.displayArray(array2);
        } else {
            utility.displayArray(array1);
            System.out.println("\n\nIs Not Equal To\n\n");
            utility.displayArray(array2);
        }
    }
    
    public static double[] fillArrayDouble(int size)
    {
        Random rnd = new Random();
        double[] array = new double[size];

        for (int i = 0; i < array.length; i++) {
           array[i] = rnd.nextInt(100);
        } 
        return array;
    }
    
    public static void displayArray(double[] array , double[] array2)
    {
        for (int i = 0; i < array.length; i++) {
           System.out.print("\narray[" + i + "] = " + array[i] + ", ");
        }
        System.out.println("\n\n");
        for (int i = 0; i < array.length; i++) {
           System.out.print("\narray[" + i + "] = " + array2[i] + ", ");
        }
    }
   
    public static void displayArray(double[] array , double[] array2 , double[] array3)
    {
        for (int i = 0; i < array.length; i++) {
           System.out.print("\narray[" + i + "] = " + array[i] + ", ");
        }
        
        System.out.println("\n\n");
        
        for (int i = 0; i < array.length; i++) {
           System.out.print("\narray[" + i + "] = " + array2[i] + ", ");
        }
        
        System.out.println("\n\n");
        
        for (int i = 0; i < array.length; i++) {
           System.out.print("\narray[" + i + "] = " + array3[i] + ", ");
        }
    }
    
    public static void displayReverseArray(int[] array)
    {
        for (int i = array.length - 1; i >=0; i--)
        {
            System.out.println(array[i]);
        }
    }
    
    
    public static void twoArraysEqualPrint(double[] array1 , double[] array2){
        
        Arrays.sort(array1);
        Arrays.sort(array2);
        if(Arrays.equals(array1, array2)==true){
            utility.displayArray(array1);
            System.out.println("\n\nIs Equal To\n\n");
            utility.displayArray(array2);
        } else {
            utility.displayArray(array1);
            System.out.println("\n\nIs Not Equal To\n\n");
            utility.displayArray(array2);
        }
    }
    
    public static boolean ifPrime(int x)
    {
        for(int i=2;i<x;i++) {
        if(x%i==0)
            return false;
        }
        return true;
    }
    
    public static int sumOfDigits(int n)
    {
        int sum = 0;
        int num = n;
        while (num > 0) {
            int lastDigit = num % 10;
            sum =  sum + lastDigit;
            num = num/10;
        }
        
        return sum;
    }
    
    
    public static void swapFirstLastElementArray(int[] array)
    {
       int firstElement = array[0];
       
       array[0] = array[array.length-1];
       
       array[9] = firstElement;
       
    }
    
    
    public static void moveElementsUpArray(int[] array)
    {
        int lastElement = array[array.length - 1];
        
        for(int i = array.length - 1; i>0;i--){
            array[i] = array[i - 1];
        }
        
        array[0] = lastElement;
    }
    
    public static void printSmallLargeNumArray(int[] array)
    {
        int largestNum = 0;
        int smallestNum = array[0];
        for(int i : array){
            if(i>largestNum){
                largestNum = i;    
            } else if(i<smallestNum){               
                smallestNum = i; 
            }
        }
        
        System.out.println("\n\nLargest Number = "+largestNum);
        System.out.println("\nSmallest Number = "+smallestNum);
    }
    
        public static void printSmallLargeNumArray(double[] array)
    {
        double largestNum = 0;
        double smallestNum = array[0];
        for(double i : array){
            if(i>largestNum){
                largestNum = i;    
            } else if(i<smallestNum){               
                smallestNum = i; 
            }
        }
        
        System.out.println("\n\nLargest Number = "+largestNum);
        System.out.println("\nSmallest Number = "+smallestNum);
    }
    
    public static int[] returnSmallLargeNumArray(int[] array)
    {
        int largestNum = 0;
        int smallestNum = array[0];
        for(int i : array){
            if(i>largestNum){
                largestNum = i;    
            } else if(i<smallestNum){               
                smallestNum = i; 
            }
        }
        int[] nums = {largestNum, smallestNum};
        
        return nums;
    }
    
        public static double[] returnSmallLargeNumArray(double[] array)
    {
        double largestNum = 0;
        double smallestNum = array[0];
        for(double i : array){
            if(i>largestNum){
                largestNum = i;    
            } else if(i<smallestNum){               
                smallestNum = i; 
            }
        }
        double[] nums = {largestNum, smallestNum};
        
        return nums;
    }
        
    
    public static double averageOfArray(int[] array)
    {
        int total = 0;
        for (int i = 0; i < array.length; i++){  
            total = total + array[i];
        }
        return(double)total / (double) array.length;
    }
        
    public static double averageOfArray(double[] array)
    {
        double total = 0;
        for (int i = 0; i < array.length; i++){  
            total = total + array[i];
        }
        return total / (double) array.length;
    }
    
        
    public static int indexOfMinArray(int[] n)
    {
        int index = 0;
        int smallestNum = n[0];
        for (int i = 0; i < n.length; i++){
            if(n[i]<smallestNum){               
                smallestNum = n[i]; 
            }
        }
        for (int i = 0; i < n.length; i++){
            if(n[i]==smallestNum){               
                index = i;
            } 
        }
        return index;
    }
    
    public static double indexOfMinArray(double[] n)
    {
        int index = 0;
        double smallestNum = n[0];
        for (int i = 0; i < n.length; i++){
            if(n[i]<smallestNum){               
                smallestNum = n[i]; 
            }
        }
        for (int i = 0; i < n.length; i++){
            if(n[i]==smallestNum){               
                index = i;
            } 
        }
        return index;
    }
    
    public static int indexOfMaxArray(double[] n)
    {
        int index = 0;
        double largestNum = n[0];
        for (int i = 0; i < n.length; i++){
            if(n[i]>largestNum){               
                largestNum = n[i]; 
            }
        }
        for (int i = 0; i < n.length; i++){
            if(n[i]==largestNum){               
                index = i;
            } 
        }
        return index;
    }
    
    public static ArrayList<Integer> fillArrayList(int numberOfValues)
    {
        Random rnd = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0;i<+numberOfValues;i++){
           list.add(rnd.nextInt(100)); 
        }
        return list;   
    }
    
    
    public static void displayArrayList(ArrayList<Integer> list)
    {
        System.out.println(list);
    }
    
        public static ArrayList<Integer> oddList(ArrayList<Integer> list)
    {
        ArrayList<Integer> oddList = new ArrayList<>();
        
        for(int i : list){
            if(i%2>0){
               oddList.add(i); 
            }
        }
        return oddList;
    }
    
    public static ArrayList<Integer> evenList(ArrayList<Integer> list)
    {
        ArrayList<Integer> evenList = new ArrayList<>();
        
        for(int i : list){
            if(i%2==0){
               evenList.add(i); 
            }
        }
        return evenList;
    }
    
    public static int sumArrayList(ArrayList<Integer> list)
    {
        int sum = 0;
        for(int i : list){ 
           sum+=i;
        }
        return sum;
    }
    
    public static ArrayList<Integer> append(ArrayList<Integer> list1 , ArrayList<Integer> list2)
    {
        ArrayList<Integer> list1AndList2 = new ArrayList<>();
        for(int i : list1){
            list1AndList2.add(i);
        }
        for(int i:list2){
            list1AndList2.add(i);
        }
        
        return list1AndList2;
    }
    
}












