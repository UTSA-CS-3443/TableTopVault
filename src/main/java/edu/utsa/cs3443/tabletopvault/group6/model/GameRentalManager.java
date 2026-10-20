package edu.utsa.cs3443.tabletopvault.group6.model;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class GameRentalManager {

    private ArrayList<Customer> customersDataList; //array hold customers.txt
    private ArrayList<Game> gamesDataList;  //array hold games.txt
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
    public String showRentalList() {
        String result = "";
        result += "Game Title | Customer Name | Checkout Date | Return Date | Returned\n";
        for (int i = 0; i < rentalDataList.size(); i++) {
            result += rentalDataList.get(i).toString();
            result += "\n";
        }
        return result;
    }









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

        File file = new File("data/games.txt");
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
            PrintWriter writer = new PrintWriter("data/games.txt");
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
    ///////////////////////////////Rental methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    /////////////////////////////////////////////////////////////////////////////////////////////////


public void addRental(Rental rental) {
        if(rental != null) {
            rentalDataList.add(rental);
        }
}


    public void loadRentalsDataFromFile() throws FileNotFoundException {

        File file = new File("data/rentals.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Rental rental = convertLineToRental(line);
            addRental(rental);
        }
        scan.close();
    }



public ArrayList<Rental> getRentalList() {return  rentalDataList;}

    public void setRentalDataList(ArrayList<Rental> rentalDataList) {
        if(rentalDataList != null) {
            this.rentalDataList = rentalDataList;
        }
    }
        private void saveRentalDataToFile() throws FileNotFoundException {
            PrintWriter writer = new PrintWriter("data/rentals.txt");
            for(int i=0;i<rentalDataList.size();i++){
                Rental rental = rentalDataList.get(i);
                String line = convertRentalToLine(rental);
                writer.println(line);
            }
            writer.close();
        }

    private Rental convertLineToRental(String line) {
        Scanner scan = new Scanner(line);
        scan.useDelimiter(",");

        String gameTitle = scan.next().trim();
        String customerName = scan.next().trim();
        String checkoutDate = scan.next().trim();
        String returnedDate = scan.next().trim();
        boolean returned = Boolean.parseBoolean(scan.next().trim());

        scan.close();


        Game game = findGame(gameTitle);
        Customer customer = findCustomerByName(customerName);

        if (game == null || customer == null) {
            System.out.println("Rental not found.");
            return null;
        }


        Rental rental = new Rental(game,customer);
        LocalDate checkout = LocalDate.parse(checkoutDate);
        LocalDate returnDate;

        if(returnedDate.isEmpty() || returnedDate.equalsIgnoreCase("Not returned")) {
            returnDate =null;
        }else {
            returnDate = LocalDate.parse(returnedDate);
        }
        rental.setRentalDates(checkout, returnDate, returned);
        return rental;

    }
        private String convertRentalToLine(Rental rental) {
        String line = "";
        line += rental.getGameTitle();
        line += ",";

        line += rental.getCustomer();
        line += ",";

        line += rental.getCheckoutDate();
        line += ",";

        if (rental.getReturnDate() == null) {
                line += "Not returned";
        } else {
            line += rental.getReturnDate();
        }

        line += ",";
        line += rental.isReturned();

        return line;
        }




    public ArrayList<Rental> getRentalDataList() {return rentalDataList;}

    public Rental findRental(String gameTitle) {
        for(int i=0;i<rentalDataList.size();i++){
            Rental rental = rentalDataList.get(i);
            if(rental.getGameTitle().equalsIgnoreCase(gameTitle)) {

                return rental;
            }
        }
        return null;
    }

    public ArrayList<Customer> getCustomerDataList() {
        return customersDataList;
    }

    public void setCustomerDataList(ArrayList<Customer> customerDataList) {
        if(customerDataList != null){
            this.customersDataList = customerDataList;
        }
    }


    public void loadRentalDataFromFile() throws FileNotFoundException {
        File file = new File("data/rentals.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()){
            String line = scan.nextLine();
            Rental rental = convertLineToRental(line);
            addRental(rental);
        }
        scan.close();
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
        File file = new File("data/customers.txt");
        Scanner scan = new Scanner(file);

        while(scan.hasNextLine()){
            String line = scan.nextLine();
            Customer customer = convertLineToCustomer(line);
            addCustomer(customer);
        }
        scan.close();
    }


    public void loadCustomersDataFromFile() throws FileNotFoundException {

        File file = new File("data/customers.txt");
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
     * @param fullName
     * @return
     */

    public Customer findCustomerByName(String fullName) {

        for (Customer customer : customersDataList) {

            if (customer.getFullName().trim().equalsIgnoreCase(fullName.trim())) {
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


   // public boolean isCustomerExists(String phoneNumber) {return findCustomer(phoneNumber) != null;}

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
            PrintWriter out = new PrintWriter("data/customers.txt");
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
            line+=customer.getNotes()+",";
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


}

