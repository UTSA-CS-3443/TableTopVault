package edu.utsa.cs3443.tabletopvault.group6;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);



        //a switch would be good here
        System.out.println("\n_____________________________________ TABLETOP VAULT _____________________________________");
        System.out.println("1. The Shelf (game collection)");
        System.out.println("2. Checkout Counter");
        System.out.println("4. Adding/Update a Customer Screen");
        System.out.println("5. Exit");

        System.out.print("Choose an option: ");
        System.out.println("\n_____________________________________ BoardGame Rental _____________________________________");

        int choice = scanner.nextInt();

        /* ////////////////////Change from client to Game\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                showUserList();
                break;
            case "2":
                System.out.println("Show game collection screen");
                break;
            case "3":
                System.out.println("Checkout Counter screen");
                break;
            case "4":
                System.out.println("Adding a new customer screen");
                break;
            case "5":
                running = false;
                System.out.println("Exiting application.");
                break;
            default:System.out.println("Invalid choice.");
        }
    }
        scanner.close();
}
*/


        System.out.println("You selected: " + choice);
    }
}
