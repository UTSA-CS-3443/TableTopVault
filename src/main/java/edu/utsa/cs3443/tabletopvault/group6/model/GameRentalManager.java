package edu.utsa.cs3443.tabletopvault.group6.model;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class GameRentalManager {

    private ArrayList<Customer> customersDataList; //array hold customers
    private ArrayList<Game> gamesDataList;  //array hold games
    private ArrayList<Rental> rentalDataList;

    public GameRentalManager() {
        customersDataList = new ArrayList<>();
        gamesDataList = new ArrayList<>();
        rentalDataList = new ArrayList<>();
    }

    /**
     * methods for showGameList()
     * @return
     */
    public String showGameList() {
        String result = "";

        result += "Game Title | Min Players | Max Players | Category | Available | Game Notes\n ";



        for (int i = 0; i < gamesDataList.size(); i++) {
            result += gamesDataList.get(i).toString();
            result += "\n";
        }

        return result;
    }


    public String showCustomerList() {
        String result = "";
        result += "Full Name | Email | Phone Number | Preferred Contact | Customer Notes\n";

        for (int i = 0; i < customersDataList.size(); i++) {
            result += customersDataList.get(i).toString();
            result += "\n";
        }
        return result;
    }



    /**
     * method for showCustomerList()
     * @return
     */









//////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////Game methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    /// .//////////////////////////////////////////////////////////////////////////////////

    public void addGame(Game game) {
        if(game != null) {
            gamesDataList.add(game);
        }
    }


    /**loadGames() method
     *
     * @throws FileNotFoundException
     */
    public void loadGamesDataFromFile() throws FileNotFoundException {

        File file = new File("data/games");
        Scanner scan = new Scanner(file);



        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Game game = convertLineToGame(line);
            addGame(game);
        }

        scan.close();
    }

    /**
     *
     * @return
     */

    public ArrayList<Game> getGamesList() {return gamesDataList; }


    public Game findGame(String gameTitle){
        for(int i=0;i<gamesDataList.size();i++){
            Game game = gamesDataList.get(i);
            if(game.getGameTitle().equalsIgnoreCase(String.valueOf(gameTitle))){
                return game;
            }
        }
        return null;
    }

    /**
     * updateGame method
     * @param differentGame
     * @return
     * @throws IOException
     */

    public boolean updateGame(Game differentGame) throws IOException{
        if(differentGame == null) {
            return false;
        }
        for(int i=0;i<gamesDataList.size();i++){
            Game game = gamesDataList.get(i);
            if(game.getGameTitle().equalsIgnoreCase(String.valueOf(differentGame.getGameTitle()))){
                gamesDataList.set(i, differentGame);
                saveDataToFile();
                return true;
            }
        }
        return false;
    }
    public boolean isGameExists(String gameTitle) {return findGame(gameTitle) != null; }

    public boolean deleteGame(Game game) throws FileNotFoundException {
        boolean deleted = gamesDataList.remove(game);
        if(deleted){
            saveDataToFile();
        }
        return deleted;
    }


    /**
     * saveDataToFile method
     * @throws FileNotFoundException
     */
    private void saveDataToFile() throws FileNotFoundException{
            PrintWriter writer = new PrintWriter("data/games");
            for(int i=0;i<gamesDataList.size();i++){
                Game game = gamesDataList.get(i);
                String line = convertGameToLine(game);
                writer.println(line);
            }
            writer.close();

        }

    /**
     * convertLineToGame() method
     * @param line
     * @return
     */


    private Game convertLineToGame(String line) {
        Scanner scan = new Scanner(line);
          scan.useDelimiter(",");

            String gameTitle = scan.next();
             int minPlayers = Integer.parseInt(scan.next());
             int maxPlayers = Integer.parseInt(scan.next());
                String gameType = scan.next();
                        Boolean available = Boolean.parseBoolean(scan.next());
                    String gameNotes = scan.next();

    scan.close();

    Game game = new Game(gameTitle, minPlayers, maxPlayers, gameType, available, gameNotes);
    return game;

    }

    /**
     * convertGameToLine() method
     * @param game
     * @return
     */


    private String convertGameToLine(Game game) {
        String line = "";

        line += game.getGameTitle();
        line += ",";

        line += game.getMinPlayers();
        line += ",";

        line += game.getMaxPlayers();
        line += ",";

        line += game.getCategory();
        line += ",";

        line += game.isAvailable();
        line += ",";

        line += game.getGameNotes();
        return line;

    }








/////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////Customer methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
/////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * An addCustomer() method, which adds an AidShip object to the group of aid ships.
     * @param customer
     */


public void addCustomer(Customer customer){
    if(customer != null) {
        customersDataList.add(customer);
    }
}

    /**
     *  loadAidShips() method, which reads the data from text files and adds a new AidShip object for each aid ship
     *      *      * found in the file. This method must throw exceptions related to file I/O.
     * @throws FileNotFoundException
     */
    public void loadCustomers() throws FileNotFoundException {
        File file = new File("data/customers");
        Scanner scan = new Scanner(file);

        while(scan.hasNextLine()){
            String line = scan.nextLine();
            Customer customer = convertLineToCustomer(line);
            addCustomer(customer);
        }
        scan.close();
    }


    public void loadCustomersDataFromFile() throws FileNotFoundException {

        File file = new File("data/customers");
        Scanner scan = new Scanner(file);



        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Customer customer = convertLineToCustomer(line);
            addCustomer(customer);
        }

        scan.close();
    }

    public ArrayList<Customer> getCustomersList() {return customersDataList; }

    /**
     * A findAidShip() method, which takes a string representing the registration number of an aid ship,
     *      *  searches for the aid ship in the group of aid ships, and returns that aid ship if found.
     *      *  If the aid ship is not found, the method returns null.
     * @param phoneNumber
     * @return
     */
    public Customer findCustomer(String phoneNumber){
        for(int i=0;i<customersDataList.size();i++){
            Customer customer = customersDataList.get(i);
            if(customer.getPhoneNumber().equalsIgnoreCase(String.valueOf(phoneNumber))){
                return customer;
            }
        }
        return null;
    }

    /**
     * An BOOLEAN!! isAidShipExists() method, which takes a string representing the registration number
     *      *  of an aid ship and returns whether the ship is found in the group of ships.
     * @param phoneNumber
     * @return
     */


    public boolean isCustomerExists(String phoneNumber) {return findCustomer(phoneNumber) != null;}

    /**
     * An updateAidShip() method, which takes an AidShip object, and replaces the existing aid ship with the this object.
     *      * This method must throw exceptions related to file I/O
     * @param phoneNumber
     * @return
     * @throws IOException
     */


        public boolean updateCustomer(Customer phoneNumber) throws IOException {
        if(phoneNumber == null) {
            return false;
        }
        for(int i=0;i<customersDataList.size();i++){
            Customer customer = customersDataList.get(i);
            if(customer.getPhoneNumber().equalsIgnoreCase(String.valueOf(phoneNumber))){
                customersDataList.set(i,phoneNumber);
                saveDataToFile();
                return true;
            }
        }
        return false;
        }


        public boolean deleteCustomer(Customer customer) throws FileNotFoundException {
            boolean deleted = customersDataList.remove(customer);
            if(deleted){
                saveDataToFile();
            }
            return deleted;
        }


    /**
     * A saveDataToFile() method, which saves the data to the data file after an update or delete.
     *      * This method must throw exceptions related to file I/O
     * @throws IOException
     */

    public void saveDatatoFile() throws IOException {
            PrintWriter out = new PrintWriter("data/customers");
            for(int i=0;i<customersDataList.size();i++){
                Customer customer = customersDataList.get(i);

                String line = convertCustomerToLine(customer);
                out.println(line);
            }
            out.close();
        }

    /**
     *  A convertLinetoCustomer() method, which takes a line read from a file and
     *      * returns a  Customer object representing the data in the line.
     * @param line
     * @return
     */


        private Customer convertLineToCustomer(String line){
        Scanner scan = new Scanner(line);
        scan.useDelimiter(",");

        String fullName = scan.next();
        String email = scan.next();
        String  phoneNumber = scan.next();
        String preferredContact = scan.next();
        String customerNotes = scan.next();

        scan.close();

        Customer customer = new Customer(fullName,email,phoneNumber,preferredContact,customerNotes);
        return customer;
        }


    /**
     * A convertCustomerToLine() method, which takes a customer object and returns a string representing the object as a line in the file
     * @param customer
     * @return
     */

    private String convertCustomerToLine(Customer customer){
            String line = "";

            line+=customer.getFullName()+",";
            line += ",";
            line+=customer.getEmail()+",";
            line += ",";
            line+=customer.getPhoneNumber()+",";
            line += ",";
            line+=customer.getPreferredContact()+",";
            line += ",";
            line+=customer.getCustomerNotes()+",";
            return line;

        }


    /**
     * A toString() method, returns a string representation of all aid ships.
     * @return
     */




    /**
     *
     * @return
     */

    public ArrayList<Customer> getCustomerDataList() {
        return customersDataList;
        }

    public void setCustomerDataList(ArrayList<Customer> customerDataList) {
        if(customerDataList != null){
            this.customersDataList = customerDataList;
        }
    }




}

