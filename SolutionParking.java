package com.company.example;

import java.util.*;

public class SolutionParking {

    public static void main(String[] args) {
        /*
        Entering the amount cars
         */
        Scanner sc = new Scanner(System.in);
        int n=0;
        int k=0;
        System.out.println("Enter the amount of cars on the parking lot:");
        while (sc.hasNext()){
            if(sc.hasNextInt()){
                n=sc.nextInt();
                if (n >= 1 && n < Math.pow(10,5)) {
                    break;
                }
                else {
                    System.out.println("Invalid amount of cars has been submitted. Please try again");
                }
            }
        }
          /*
        Entering the amount of cars that need to be covered
         */
        System.out.println("Enter the amount of cars needed to be covered:");
        while (sc.hasNext()){
            if(sc.hasNextInt()){
                k=sc.nextInt();
                if (k >= 1 && k < n) {
                    break;
                }
                else {
                    System.out.println("Invalid amount of cars has been submitted. Please try again");
                }
            }
        }
        /*
        Entering the locations of cars
         */
        System.out.println("Enter the spots where the cars have been parked:");
        int[] cars = new int[n];
        for (int i = 0; i < n; i++) {
            while (sc.hasNext()){
                if(sc.hasNextInt()){
                    cars[i]=sc.nextInt();
                    if ( cars[i] >= 1 &&  cars[i] < Math.pow(10,14)) {
                        break;
                    }
                    else {
                        System.out.println("Car parked at an invalid location. Please try again");
                    }
                }

            }
        }
        /*
          Printing out the results
         */
        System.out.println("The minimum length of a roof needed for the completion of this task:"+carParkingRoof(cars, k));}

    public static int carParkingRoof(int cars[],int k){
        /*
        This function sorts the given array.
        Then calculates the space between the first and the last integer of the given array.
        After that a for loop is used to find the minimal number of of spaces required to complete the given task.
        The space between the current integer in the for loop (i) is compared with the integer that is supposedly k steps ahead.
        The minimal between this number and the MaxSpaces is found.
        Then MaxSpaces are initialized with the given n number.
        This is repeated until i+k-1 doesn't surpass the given arrays length.
        By the end of the loop the minimal length required to fulfill the task is found and is returned by the function
         */
        Arrays.sort(cars);
        int MaxSpaces=cars[cars.length-1]-cars[0]+1;
        int returnable=0;
        for (int i=0;i+k-1<cars.length;i++){
            int n=cars[i+k-1]-cars[i]+1;
            returnable=Math.min(MaxSpaces,n);
            MaxSpaces=returnable;
        }

        return returnable;
    }


}

    /*
    Task 2
     */
