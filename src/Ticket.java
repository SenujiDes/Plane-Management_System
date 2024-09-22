import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    public static final char[] length = null;
    private char row;
    private int seat;
    private double price;
    private Person person;

    //creating the constructor
    public Ticket(char ticket_row,int ticket_seat,double ticket_price,Person ticket_person){
        this.row=ticket_row;
        this.seat=ticket_seat;
        this.price=ticket_price;
        this.person=ticket_person;

    }

    //Setting all the variables


    public void set_row(char new_row){

        this.row=new_row;
    }

    public void set_seat(int new_seat){

        this.seat=new_seat;
    }

    public void set_price(double new_price){

        this.price=new_price;
    }

    public void set_person(Person new_person){

        this.person=new_person;
    }

    //Getting all the variables
    public char get_row(){

        return row;
    }

    public int get_seat(){
        return seat;
    }

    public double get_price(){

        return price;
    }

    public Person get_person(){
        return person;
    }


    //Print all information
    public void print_all_info(){
        System.out.println("Row     : "+ row);
        System.out.println("Seat    : "+ seat );
        System.out.println("Price   : £"+ price);
        System.out.println();
        System.out.println("Datails of the person " );
        System.out.println();
        person.print_all_info();
        System.out.println("------------------------------------------------------------------");
        
    }

    public void save() {
        String filename = row +""+ seat + ".txt"; //creating text file name, according to the row lettter and seat number
        try {
            FileWriter file = new FileWriter(filename); //initializing the file variable according to the filename
            //writing the information inside the txt file
            file.write("Row        : " + row + "\nSeat Number: " + seat +"\nPrice      : £" + price + "\n");
            file.write("Person     : " + person.get_name() + "\nSurname    : " + person.get_surname() +  "\nEmail      : " + person.get_email() + "\n");
            file.close(); //closing the file
            System.out.println("---Ticket information saved to " + filename+"---"); 
        } catch (IOException e) {
            System.out.println("An error occurred while saving the ticket information to " + filename); 
            e.printStackTrace();
        }
    }

    public void removeinformation(){
        //removing the details from a txt file , if the ticket was cancled 
        String filename = row +""+ seat + ".txt";
        try {
            FileWriter file = new FileWriter(filename);
            file.write("Row        :\nSeat Number:\nPrice      :\n"); //details were emptied
            file.write("Person     :\nSurname    :\nEmail      :\n");
            file.close();
            System.out.println("---Ticket information removed from the file " + filename+"---");
        } catch (IOException e) {
            System.out.println("An error occurred while removing the ticket information from " + filename);
            e.printStackTrace();
        }
    }


}
