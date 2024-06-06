import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class w2053174_PlaneManagement {
    private static final int NUM_ROWS = 4;
    private static final int[] SEATS_PER_ROW = {14, 12, 12, 14}; // A1 to A14, B1 to B12, C1 to C12, D1 to D14
    private static int[] seats = new int[NUM_ROWS * 14]; // Assuming the maximum number of seats is 14 per row
    private static ArrayList<Ticket> ticketsSold = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize all seats to 0 (available)
        for (int i = 0; i < seats.length; i++) {
            seats[i] = 0;
        }
        // Display welcome message
        System.out.println("Welcome to the Plane Management application");

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Display the menu and handle user input
        while (true) {
            displayMenu();
            int option = 0;
            while (true) {
                System.out.print("Please enter your option: ");
                try {
                    option = scanner.nextInt();
                    break; // Exit the loop if a valid number is entered
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Option . Please enter a Correct Option Number.");
                    scanner.next(); // Consume the invalid input
                }
            }

            switch (option) {
                case 1:
                    buySeat(scanner);
                    break;
                case 2:
                    cancelSeat(scanner);
                    break;
                case 3:
                    findFirstAvailable(scanner);
                    break;
                case 4:
                    showSeatingPlan();
                    break;
                case 5:
                    printTicketsInfo();
                    break;
                case 6:
                    searchTicket(scanner);
                    break;
                case 0:
                    System.out.println("""
                            * Thank you for using the Plane Management application.*
                            * We look forward to welcoming you on board.*\s
                            * Have a pleasant journey.*""");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("""       
                ****************************************************
                *                    Menu Option                   *
                ****************************************************
                 1) Buy a Seat
                 2) Cancel a Seat
                 3) Find First Available Seat
                 4) Show Seating Plan
                 5) Print Ticket Information and Total Sales
                 6) Search Ticket
                 0) Quit
                ****************************************************
                """);
    }

    public static void buySeat(Scanner scanner) {
        System.out.println("*************** Buy Your Seat ***************");
        String rowLetter;
        int seatNumber;
        do {
            System.out.print("Enter the row letter: ");
            rowLetter = scanner.next().toUpperCase();
            System.out.print("Enter the seat number: ");
            try {
                seatNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid seat number. Please enter a number.");
                scanner.next(); // Consume the invalid input
                seatNumber = 0; // Reset seatNumber to trigger the loop again
            }

            if (rowLetter.length() != 1 || !Character.isLetter(rowLetter.charAt(0)) || rowLetter.charAt(0) < 'A' || rowLetter.charAt(0) > 'D' || seatNumber < 1 || seatNumber > SEATS_PER_ROW[rowLetter.charAt(0) - 'A']) {
                System.out.println("Invalid row letter or seat number. Please try again.");
                rowLetter = ""; // Reset rowLetter to trigger the loop again
                seatNumber = 0; // Reset seatNumber to trigger the loop again
            }
        } while (rowLetter.isEmpty() || seatNumber == 0);



        int rowIndex = rowLetter.charAt(0) - 'A'; // Convert row letter to index (0-3)
        int seatIndex = (rowIndex * 14) + seatNumber - 1; // Calculate seat index (0-55)

        // Determine the price based on the seat number
        int price;
        if (seatNumber >= 1 && seatNumber <= 5) {
            price = 200;
        } else if (seatNumber >= 6 && seatNumber <= 9) {
            price = 150;
        } else {
            price = 180;
        }

        // Get person's information
        System.out.print("Please Enter Ticket Holder Name : ");
        String name = scanner.next();
        System.out.print("Please Enter Ticket Holder Surname : ");
        String surname = scanner.next();
        System.out.print("Please Enter Ticket Holder Email : ");
        String email = scanner.next();

        // Create a new Person object
        Person person = new Person(name, surname, email);

        // Create a new Ticket object
        Ticket ticket = new Ticket(rowLetter, seatNumber, price, person);

        // Add the ticket to the list of tickets sold
        ticketsSold.add(ticket);

        if (seats[seatIndex] == 0) {
            seats[seatIndex] = 1; // Mark seat as sold
            System.out.println("Seat " + rowLetter + seatNumber +" has been successfully purchased for " + price + " €." +
                    "\nThank you for your purchase! Enjoy your flight");

            ticket.save(); // Save the ticket information to a file
        } else {
            System.out.println("Seat " + rowLetter + seatNumber + " is already Purchesed. Try Another seat");
        }
    }

    public static void cancelSeat(Scanner scanner) {
        System.out.println("************** Cancel Your Seat **************");
        String rowLetter;
        int seatNumber;
        do {
            System.out.print("Please Enter the row letter: ");
            rowLetter = scanner.next().toUpperCase();
            System.out.print("Please Enter the seat number: ");
            try {
                seatNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid seat number. Please enter a number.");
                scanner.next(); // Consume the invalid input
                seatNumber = 0; // Reset seatNumber to trigger the loop again
            }

            if (rowLetter.length() != 1 || !Character.isLetter(rowLetter.charAt(0)) || rowLetter.charAt(0) < 'A' || rowLetter.charAt(0) > 'D' || seatNumber < 1 || seatNumber > SEATS_PER_ROW[rowLetter.charAt(0) - 'A']) {
                System.out.println("Invalid row letter or seat number. Please try again.");
                rowLetter = ""; // Reset rowLetter to trigger the loop again
                seatNumber = 0; // Reset seatNumber to trigger the loop again
            }
        } while (rowLetter.isEmpty() || seatNumber == 0);

        int rowIndex = rowLetter.charAt(0) - 'A';
        int seatIndex = (rowIndex * 14) + seatNumber - 1;

        if (seats[seatIndex] == 1) {
            seats[seatIndex] = 0; // Mark seat as available
            System.out.println("Seat " + rowLetter + seatNumber + " has been successfully canceled.");
        }
        else {
            System.out.println("Seat " + rowLetter + seatNumber + " is already available.");
            return; // Exit the method if the seat is already available
        }

        // Find the ticket to cancel
        Ticket ticketToCancel = null;
        for (Ticket ticket : ticketsSold) {
            if (ticket.getRow().equals(rowLetter) && ticket.getSeat() == seatNumber) {
                ticketToCancel = ticket;
                break;
            }
        }

        // If the ticket is found, remove the ticket
        if (ticketToCancel != null) {
            ticketsSold.remove(ticketToCancel);
            // Removed the duplicate print statement here
        } else {
            // This else block is not necessary since I have already handled this in Line 178 - 181
        }
    }


    public static void findFirstAvailable(Scanner scanner) {
        for (char row = 'A'; row <= 'D'; row++) {
            int rowIndex = row - 'A';
            for (int seatNumber = 1; seatNumber <= SEATS_PER_ROW[rowIndex]; seatNumber++) {
                int seatIndex = (rowIndex * 14) + seatNumber - 1;
                if (seats[seatIndex] == 0) {
                    System.out.println("The first available seat is " + row + seatNumber + ".");
                    return; // Exit the method once the first available seat is found
                }
            }
        }
        System.out.println("No available seats found.");
    }

    public static void showSeatingPlan() {
        System.out.println("**************** Show Seating Plan ****************");
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int seat = 0; seat < SEATS_PER_ROW[row]; seat++) {
                int seatIndex = (row * 14) + seat;
                if (seats[seatIndex] == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
            }
            // Print spaces at the end of rows B and C
            if (row == 1 || row == 2) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public static void printTicketsInfo() {
        System.out.println("**************** Print Ticket Info ****************");
        double totalPrice = 0.0;
        System.out.println("Tickets Sold:");
        for (Ticket ticket : ticketsSold) {
            System.out.println("Row: " + ticket.getRow() + ", Seat: " + ticket.getSeat() + ", Price: " + ticket.getPrice() + " €");
            totalPrice += ticket.getPrice();
        }
        System.out.println("Total Price: " + totalPrice + " €");
    }
    public static void searchTicket(Scanner scanner) {
        System.out.println("**************** Search Ticket Details ****************");
        String rowLetter;
        int seatNumber;
        do {
            System.out.print("Please Enter the row letter: ");
            rowLetter = scanner.next().toUpperCase();
            System.out.print("Please Enter the seat number: ");
            try {
                seatNumber = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid seat number. Please enter a number.");
                scanner.next(); // Consume the invalid input
                seatNumber = 0; // Reset seatNumber to trigger the loop again
            }

            if (rowLetter.length() != 1 || !Character.isLetter(rowLetter.charAt(0)) || rowLetter.charAt(0) < 'A' || rowLetter.charAt(0) > 'D' || seatNumber < 1 || seatNumber > SEATS_PER_ROW[rowLetter.charAt(0) - 'A']) {
                System.out.println("Invalid row letter or seat number. Please try again.");
                rowLetter = ""; // Reset rowLetter to trigger the loop again
                seatNumber = 0; // Reset seatNumber to trigger the loop again
            }
        } while (rowLetter.isEmpty() || seatNumber == 0);

        boolean seatFound = false;
        for (Ticket ticket : ticketsSold) {
            if (ticket.getRow().equals(rowLetter) && ticket.getSeat() == seatNumber) {
                System.out.println("Ticket Information:");
                System.out.println("Row: " + ticket.getRow() + ", Seat: " + ticket.getSeat() + ", Price: " + ticket.getPrice() + " €");
                System.out.println("Person Information:");
                System.out.println("Name: " + ticket.getPerson().getName());
                System.out.println("Surname: " + ticket.getPerson().getSurname());
                System.out.println("Email: " + ticket.getPerson().getEmail());
                seatFound = true;
                break;
            }
        }
        if (!seatFound) {
            System.out.println("This seat is not Purchesed.");
        }
    }
}
