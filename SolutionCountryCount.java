package com.company.example;



import java.util.*;

public class SolutionCountryCount {

    private static int N=0;
    private static int M=0;
    /*
    The rows and columns of the map are initialized here for an easier access for all the functions
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        /*
        Adding the number of rows
         */
        System.out.println("Enter the amount of rows:");
        while (sc.hasNext()){
            if(sc.hasNextInt()){
                N=sc.nextInt();
                if (N >= 1 && N < 300000) {
                    break;
                }
                else {
                    System.out.println("Invalid amount of rows has been submitted. Please try again");
                }
            }
        }
         /*
        Adding the number of columns
         */
        System.out.println("Enter the amount of columns:");
        while (sc.hasNext()){
            if(sc.hasNextInt()){
                M=sc.nextInt();
                if (M >= 1 && M < 300000) {
                    break;
                }
                else {
                    System.out.println("Invalid amount of columns has been submitted. Please try again");
                }
            }
        }
         /*
        Filling up the map
         */
        int[][] matrix=new int[N][M];
        System.out.println("Enter the numbers into the map");
        for(int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                while (sc.hasNext()){
                    if(sc.hasNextInt()){
                        matrix[i][j]=sc.nextInt();
                        if (N >= -1000000000 && N < 1000000000) {
                            break;
                        }
                        else {
                            System.out.println("Invalid number has been submitted. Please try again");
                        }
                    }
                }
            }
            System.out.println("========");
        }
        /*
        Printing out the solution
         */
        System.out.println("The amount of countries in the given map:"+countryMap(matrix));

    }
    public static int countryMap(int[][]A){
        /*
        The elements of the given map are added into a set to get all the unique numbers inside of it
         */
        TreeSet<Integer> tempSet= new TreeSet<>();
        for(int row=0;row<A.length;row++){
            for (int col=0;col<A[0].length;col++) {
                tempSet.add(A[row][col]);
            }
        }
        /*
        Then it is transferred into an ArrayList to be able to access its largest number.
        Afterwards an integer named replacer is initialized while looping through the map.
        It holds a number that equals to the largest_number_in_the_map+1.

        Afterwards this number is added to the ArrayList of uniqueNums so it doesn't get repeated and so
        every country could have a unique number.
        Filling up the country with a unique number is performed by using the floodFill algorithm that takes the Array
        the current row and column,and the replacer.
        (Explanation of that algorithm is written inside the floodFill function itself)

         */
        List<Integer> uniqueNums= new ArrayList<>(tempSet);
        for(int row=0;row<A.length;row++){
            for (int col=0;col<A[0].length;col++) {
                int replacer;
                while (true){
                    replacer=uniqueNums.get(uniqueNums.size()-1)+1;
                    if(!uniqueNums.contains(replacer)){
                        uniqueNums.add(replacer);
                        Collections.sort(uniqueNums);
                        break;
                    }

                }
                floodFill(A,row,col,replacer);
            }
        }
        /*

        Loop below was used to check the numbers.
        Will be kept here if needed.
         */
//        for (int i = 0; i < A.length; i++)
//        {
//            for (int j = 0; j < A[0].length; j++)
//                System.out.print(A[i][j] + " ");
//            System.out.println();
//        }

        /*
          After performing floodFill on the given array and giving all the countries unique numbers a new Set
        is created and then its size is then returned by the function.
        (TreeSet is not mandatory for the final part but it I made it so it was sorted just in case.)
         */
        Set<Integer> result= new TreeSet<>();
        for(int row=0;row<A.length;row++){
            for (int col=0;col<A[0].length;col++) {
                result.add(A[row][col]);
            }
        }
        return result.size();
    }
    static void floodFillUtil(int screen[][], int x, int y, int prevC, int newC)
    {
        /*
        In here the main task is fulfilled. Orthogonally checking the numbers and changing them to the number
        given by the replacer.
        This takes a starting position, checks its number then orthogonally continues until there are no more
        options it can continue with.
        If the current position is no longer suitable, meaning that the new position holds a different number it stops.
        If otherwise it initializes the current position with the number given for replacement and then it uses
        recursion to check the positions  left, right, up and down.
        Looping this causes each country to have a unique number. Which after can bu used in a Set to easily calculate
        the overall number of countries on the map.
         */
        if (screen[x][y] != prevC)
            return;
        screen[x][y] = newC;
        if (x>0) floodFillUtil(screen,x-1,y, prevC, newC);
        if (x<screen.length-1) floodFillUtil(screen,x+1,y, prevC, newC);
        if (y>0)               floodFillUtil(screen,x,y-1, prevC, newC);
        if (y<screen[0].length-1) floodFillUtil(screen,x,y+1, prevC, newC);
    }
    static void floodFill(int screen[][], int x, int y, int newC)
    {
        /*
        This is the function that is called in the primary method that is used to calculate the countries.
        This one is just to start the recursion. Testing prevC==newC isn’t necessary but done just in case.
         */
        int prevC = screen[x][y];
        if(prevC==newC) return;
        floodFillUtil(screen, x, y, prevC, newC);
    }
}
