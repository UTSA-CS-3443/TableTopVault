package edu.utsa.cs3443.tabletopvault.group6.model;

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
}