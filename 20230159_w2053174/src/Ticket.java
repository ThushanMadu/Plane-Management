import java.io.FileWriter;
import java.io.IOException;
public class Ticket {
    private String row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(String row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Getters
    public String getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    // Setters
    public void setRow(String row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    // Method to print ticket's information
    public void printTicketInfo() {
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: " + price);
        System.out.println("Person's Information:");
        person.printPersonInfo();
    }
    public void save() {
        String fileName = getRow() + getSeat() + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Row: " + getRow() + "\n");
            writer.write("Seat: " + getSeat() + "\n");
            writer.write("Price: " + getPrice() + " €\n");
            writer.write("Person's Information:\n");
            writer.write("Name: " + getPerson().getName() + "\n");
            writer.write("Surname: " + getPerson().getSurname() + "\n");
            writer.write("Email: " + getPerson().getEmail() + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the ticket information.");
            e.printStackTrace();
        }
    }
}
