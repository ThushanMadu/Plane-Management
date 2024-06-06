# Plane Management Application

## Overview

This Plane Management Application is a simple console-based program for managing seat bookings on a plane. It allows users to perform various operations such as buying a seat, canceling a seat, finding the first available seat, showing the seating plan, printing ticket information, and searching for specific ticket details.

## Features

1. **Buy a Seat**: Users can purchase a seat by providing the row letter and seat number.
2. **Cancel a Seat**: Users can cancel a previously purchased seat.
3. **Find First Available Seat**: The application will find and display the first available seat.
4. **Show Seating Plan**: Displays the current seating arrangement with available and occupied seats.
5. **Print Ticket Information and Total Sales**: Displays all sold tickets and the total sales amount.
6. **Search Ticket**: Users can search for ticket details by providing the row letter and seat number.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your machine.
- A code editor or an Integrated Development Environment (IDE) such as IntelliJ IDEA, Eclipse, or Visual Studio Code.

### Running the Application

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/your-username/plane-management.git
    cd plane-management
    ```

2. **Compile the Application**:
    ```bash
    javac w2053174_PlaneManagement.java
    ```

3. **Run the Application**:
    ```bash
    java w2053174_PlaneManagement
    ```

## Usage

Upon running the application, a menu will be displayed with the following options:

```
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
```

### Menu Options

1. **Buy a Seat**:
   - Prompts for the row letter and seat number.
   - Prompts for ticket holder's name, surname, and email.
   - Displays a confirmation message if the seat is successfully purchased.

2. **Cancel a Seat**:
   - Prompts for the row letter and seat number.
   - Displays a confirmation message if the seat is successfully canceled.

3. **Find First Available Seat**:
   - Displays the first available seat.

4. **Show Seating Plan**:
   - Displays the current seating arrangement with `O` for available seats and `X` for occupied seats.

5. **Print Ticket Information and Total Sales**:
   - Displays all sold tickets and the total sales amount.

6. **Search Ticket**:
   - Prompts for the row letter and seat number.
   - Displays the ticket details if the seat is found.

7. **Quit**:
   - Exits the application.

## Classes

### `w2053174_PlaneManagement`
This is the main class containing the main method and the core functionalities of the application.

### `Ticket`
Represents a ticket with attributes for row letter, seat number, price, and the ticket holder's information.

### `Person`
Represents a person with attributes for name, surname, and email.

## Code Structure

- **Main Method**: Initializes the seats and displays the menu for user interaction.
- **displayMenu**: Displays the menu options.
- **buySeat**: Handles the seat purchase process.
- **cancelSeat**: Handles the seat cancellation process.
- **findFirstAvailable**: Finds and displays the first available seat.
- **showSeatingPlan**: Displays the current seating plan.
- **printTicketsInfo**: Displays sold tickets and total sales.
- **searchTicket**: Searches and displays ticket details based on the seat number and row letter.

## Contributing

Feel free to contribute to the project by submitting pull requests. Please ensure your code follows the existing coding style and includes relevant comments and documentation.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgments

- This project is a simple educational example for managing plane seat bookings using Java.

---

Feel free to reach out with any questions or suggestions!

Happy Coding!
