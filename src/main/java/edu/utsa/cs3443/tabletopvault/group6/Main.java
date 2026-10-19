
package edu.utsa.cs3443.tabletopvault.group6;

import edu.utsa.cs3443.tabletopvault.group6.model.Game;
import edu.utsa.cs3443.tabletopvault.group6.model.GameRentalManager;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static GameRentalManager menu;

    public static void main(String[] args) throws FileNotFoundException {

        menu = new GameRentalManager();
        menu.loadGamesDataFromFile();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n****************************** [TABLETOP VAULT] ******************************");
            System.out.println("1. The Shelf (game collection)");
            System.out.println("2. Checkout Counter");
            System.out.println("3. Add/Update Customer");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    showGameList();
                    break;

                case "2":
                    System.out.println("Time to checkout");
                    break;

                case "3":
                    System.out.println("Let's add a new customer");
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

    public static void showGameList() {
        ArrayList<Game> gameList = menu.getGamesList();

        System.out.println("\n****************************** [THE SHELF] *********************************");
        System.out.println(menu.showGameList());
    }
}
