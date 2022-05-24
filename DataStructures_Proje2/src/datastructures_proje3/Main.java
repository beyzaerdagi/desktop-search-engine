/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures_proje3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author beyza
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Heap heap = new Heap();
        Scanner sc = new Scanner(System.in);
        //Create a menu for searching
        System.out.println("********MENU********");
        String input;
        boolean isExit = false;
        while (!isExit) {
            System.out.println("1.Search word");
            System.out.println("2.Exit");
            System.out.print("Selection: ");
            input = sc.nextLine();
            if (isNumber(input)) {
                if (Integer.parseInt(input) == 1) {
                    System.out.print("Enter string: ");
                    input = sc.nextLine();
                    //Send input to the method
                    heap.searchFromDocs(input);
                    System.out.println();
                } else if (Integer.parseInt(input) == 2) {
                    System.out.println("Have a good day");
                    isExit = true;
                } else {
                    System.out.println("Please select 1 or 2");
                }
            } else {
                System.out.println("Please select 1 or 2");
            }
        }
    }
    
    //Check if user selected input equal to number
    public static boolean isNumber(String str) {

        for (int i = 0; i < str.length(); i++) {
            if (!(str.charAt(i) >= 48 && str.charAt(i) <= 57)) {
                return false;
            }
        }
        return true;
    }

}
