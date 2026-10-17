package edu.utsa.cs3443.tabletopvault.group6;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Tabletop Vault");
        System.out.println("1. The Shelf");
        System.out.println("2. Checkout Counter");
        System.out.println("3. Exit");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        System.out.println("You selected: " + choice);
    }
}
