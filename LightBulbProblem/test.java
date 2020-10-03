
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        String size;
        int size1;
        Scanner input = new Scanner(new File("input.txt")); // read the input text file

        size = input.next(); // receive the size of array in type string
        size1 = Integer.parseInt(size); // convert size of array to type integer
        int arra[] = new int[size1]; // initialize an empty array of type integer with size
        String arr[] = new String[size1]; // initialize an empty array of type string with size

        int counter = 0; // an index for incrementing use

        while (input.hasNext())

        {
            arr[counter++] = input.next(); // write the next line of input into string array and increment counter
        } // repeat until there is no line left, i.e empty spaces

        for (int i = 0; i < size1; i++) {
            arra[i] = Integer.parseInt(arr[i]); // convert string array into integer array
        }

        boolean check = true; // check if the input file is valid
        for (int i = 1; i < arra.length; i++) { // first integer is array size
            if (arra[i] != 0 && arra[i] != 1) {
                System.out.println("Exception Found! non-light bulb element found in lineup, please re-insert input");
                check = false;
                break;
            }
        }

        if (check == true) {
            System.out.println("Printing out the original light bulb lineup");
            for (int i = 0; i < arra.length; i++) {
                System.out.print("  " + arra[i]); // print out original array of lightbulbs
            }

            System.out.println("\n\n\n");

            yarn fD = new yarn(); // declare an object of class findDefect
            fD.findDefective(arra); // object calls method findDefective with the integer array as argument
        }
        input.close(); // close scanner at the end

    }
}