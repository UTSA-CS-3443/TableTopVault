
package edu.utsa.cs3443.tabletopvault.group6;

import edu.utsa.cs3443.tabletopvault.group6.model.Customer;
import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;
import edu.utsa.cs3443.tabletopvault.group6.model.Staff;
import edu.utsa.cs3443.tabletopvault.group6.model.Rental;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static GameRentalManager menu;

    public static void main(String[] args) throws FileNotFoundException {

        menu = new GameRentalManager();
        menu.loadCustomersDataFromFile();
        menu.loadGamesDataFromFile();
        menu.loadRentalDataFromFile();




        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n*************************** [TABLETOP VAULT] *******************************");
            System.out.println("1. The Shelf (game collection)");
            System.out.println("2. Checkout Counter");
            System.out.println("3. Add/Update Customer");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    showGameList(scanner);
                    break;

                case "2":
                    showRentalList(scanner);
                    break;

                case "3":
                    showCustomerList(scanner);
                    break;

                case "4":
                    running = false;
                    System.out.println("Exiting TableTop Vault.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    public static void showGameList(Scanner scanner) {
        boolean checkoutMenuRunning = true;
        while (checkoutMenuRunning) {

            System.out.println("\n****************************** [THE SHELF] *********************************");
            System.out.println("1. View Games");
            System.out.println("2. Add new game to inventory");
            System.out.println("3. Remove game from inventory");
            System.out.println("4. Update status of game");
            System.out.println("5. Exit back to main menu");
            System.out.print("Choose an option: ");
            System.out.println();

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                System.out.println(menu.showGameList());
                break;
                    case  "2":
                    System.out.println("Here is where you add a new game");
                    break;
                    case "3":
                        System.out.println("Here is where you remove a new game");
                        break;
                        case "4":
                            System.out.println("Here is where you update status of game");
                            break;
                            case "5":
                                checkoutMenuRunning = false;
                                break;

                default:
                    System.out.println("Invalid choice.");
            }

        }

    }



    public static void showRentalList(Scanner scanner) {
        boolean checkoutMenuRunning = true;

        while (checkoutMenuRunning) {


        System.out.println("\n****************************** [Checkout Desk] *********************************");
        System.out.println("1. View rentals");

        System.out.println("2. Checkout new game");
        System.out.println("3. Return game");
        System.out.println("4. Update rental status of game");
        System.out.println("5. Exit back to main menu");
        System.out.print("Choose an option: ");
            System.out.println();

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
               System.out.println(menu.showRentalList());
                break;

            case "2":
            System.out.println("Here is where you put the Checkout new game");
            break;

            case "3":
                System.out.println("Here is where you put the Return game");
                break;

            case "4":
                System.out.println("Here is where you put the Update rental status of game");
                break;
            case "5":
                checkoutMenuRunning = false;
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

}


    public static void showCustomerList(Scanner scanner) {
        boolean checkoutMenuRunning = true;

        while (checkoutMenuRunning) {
            System.out.println("\n****************************** [Add / Update Customer] *********************************");
            System.out.println("1. View current customer listings");
            System.out.println("2. Add new customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Remove Customer");
            System.out.println("5. Exit back to main menu");
            System.out.print("Choose an option: ");
            System.out.println();

            String choice = scanner.nextLine();
            switch(choice) {
                case "1":
                    System.out.println(menu.showCustomerList());
                    break;
                    case "2":
                        System.out.println("Here is where you add new customer");
                        break;
                        case "3":
                            System.out.println("Here is where you update Customer");
                            break;
                            case "4":
                                System.out.println("Here is where you remove Customer");
                                break;
                                case "5":
                                    checkoutMenuRunning = false;
                                    break;
            }

        }


    }

}


