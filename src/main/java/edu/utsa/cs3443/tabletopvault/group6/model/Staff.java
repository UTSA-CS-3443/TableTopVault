package edu.utsa.cs3443.tabletopvault.group6.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Model class representing a TableTop Vault staff member.
 * Extends Person and includes staff login/schedule metadata and credential verification.
 */
public class Staff extends Person {

    private String username;
    private String passwordHash;

    /**
     * Constructs a Staff member instance.
     *
     * @param fullName     the staff member's full name
     * @param email        the staff member's email address
     * @param phoneNumber  the staff member's phone number
     * @param username     the staff member's username or shift note
     * @param passwordHash the staff member's password credential
     */
    public Staff(String fullName, String email, String phoneNumber, String username, String passwordHash) {
        super(fullName, email, phoneNumber);
        this.username = username;
        this.passwordHash = passwordHash;
    }

    /**
     * Gets the staff member's username or shift note.
     *
     * @return username string
     */
    public String getUsername() {
        return username;
    }

    /**
     * Updates the staff member's username or shift note.
     *
     * @param username new username string
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the stored password hash/credential.
     *
     * @return password credential string
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Updates the stored password hash/credential.
     *
     * @param passwordHash new password credential string
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Verifies whether a provided password matches the staff member's stored credential.
     *
     * @param inputPassword password to verify
     * @return true if the password matches, false otherwise
     */
    public boolean verifyPassword(String inputPassword) {
        return passwordHash != null && passwordHash.equals(inputPassword);
    }

    /**
     * Formats the staff record for console display (omitting sensitive password data).
     *
     * @return formatted staff string
     */
    @Override
    public String toString() {
        return getFullName() + " | " + getEmail() + " | " + getPhoneNumber() + " | Info: " + username;
    }


    public static Staff getNewStaffObject(String username) {return new Staff("", "", "", "", "");}



    public static Staff login(String username, String password) throws FileNotFoundException {
        File file = new File("data/staff.txt");

        try(Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] data = line.split(",");
                if(data.length != 5) {
                    continue;
                }
                if (data[3].equals(username) && data[4].equals(password)) {

                    return new Staff(data[0], data[1], data[2], data[3], data[4]);
                }

            }

        }
        return null;
    }
}