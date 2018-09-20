/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question3;


/**
 *
 * @author patri
 */
public class PartA {

    public static void main(String[] args) {
        int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int num = 0;
        int min = 99999;
        for (int i = 0; i < intArray.length - 1; i++) {
            num = intArray[i];
            min = findMin(min, num);
        }
        System.out.println("min: " + min);

    }

    public static int findMin(int min, int num) {
        if (num < min) {
            System.out.println("less");
            min = num;
            findMin(min, num);
        }

        return min;
    }

//    public static int getFib(x) {
//        switch (x) {
//            case 0:
//            case 1:
//                return x;
//            default:
//                return getFib(x-1) + getFib(x-2);
//        }
//    }
}
