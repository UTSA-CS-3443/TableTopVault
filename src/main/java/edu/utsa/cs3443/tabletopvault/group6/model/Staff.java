package edu.utsa.cs3443.tabletopvault.group6.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Staff class representing administrative users in the TableTop Vault system.
 * Extends the abstract Person base class and implements secure SHA-256 password hashing.
 *
 * Java Notes:
 * - Utilizes java.security.MessageDigest for cryptographic hashing without external library dependencies.
 * - Encapsulates username and passwordHash fields behind private access modifiers.
 */
public class Staff extends Person {

    private String username;
    private String passwordHash;

    public Staff(String fullName, String email, String phoneNumber, String username, String passwordHash) {
        super(fullName, email, phoneNumber);
        this.username = username;
        this.passwordHash = passwordHash;
    }

    /**
     * Hashes a plaintext password string using the SHA-256 algorithm.
     * @param plainPassword The raw password entered by the user.
     * @return The resulting 64-character hexadecimal hash string.
     */
    public static String hashPassword(String plainPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(plainPassword.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing staff password", e);
        }
    }

    public boolean authenticate(String inputPassword) {
        return this.passwordHash.equals(hashPassword(inputPassword));
    }

    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
}