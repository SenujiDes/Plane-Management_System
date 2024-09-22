import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
public class w2051689_PlaneManagement {
    public int[] A = new int[14]; //initializing the seat row array with the relevant amount of seat numbers
    public int[] B = new int[12];
    public int[] C = new int[12];
    public int[] D= new int[14];
    public int[][] seats = {A,B,C,D}; //creating an 2D array called seats(integer array) and inserting the relevant seat amounts 
    public Ticket[] tickets = new Ticket[0]; //Creating an array called tickets , using Ticket data type

    public int seat_row_validate(){

        Scanner input1=new Scanner(System.in);  
        char row_letter='z'; 
        String rowString;
        
        while(true){
            System.out.print("Enter the row letter : ");
            rowString=input1.next();
            if (rowString.length()>1){
                System.out.println("Please enter a valid row letter");
            }
            else{
                row_letter = rowString.charAt(0);
                if(row_letter== 'A' || row_letter== 'B' || row_letter== 'C' || row_letter== 'D' || row_letter== 'a' || row_letter== 'b' || row_letter== 'c' || row_letter== 'd'){ //Checking whether the user input a correct seat letter
                    break;
                }
                else{
                    System.out.println("Please enter a valid row letter");
                    System.out.println();
                }
            }
        }
        int row; //converting the row letter to int (using ASCII values)
        if (row_letter<='D'){ //As 65 is the ASCII value of A, therefore we can define the row letters as A=0, B=1, C=2, D=3
            row = row_letter - 65; 
        }
        else{
            row = row_letter - 97; //As 97 is the ASCII value of a, therefore we can define the row letters as a=0, b=1, c=2, d=3
        }
        
        return row; //returning the row number
    }

    public int seat_column_validate(int amount){ //validating the seat column number

        Scanner input2=new Scanner(System.in); 
        int column_number = 15;

        while (true) {     
            System.out.print("Enter the column number : ");
            try{
                column_number=input2.nextInt();
                if(column_number>0 && column_number <= amount){ //Checking whether the user has entered a valid column number using error handling
                    break;
                }
                else{
                    System.out.println("Please enter a valid seat number ");
                    System.out.println();
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a valid integer.");
                input2.nextLine();  //Invalid input is being cleared from the scanner buffet
                System.out.println();
            }  
        }
        
        return(column_number);
    }

    public void buy_seat(){

        Scanner input3=new Scanner(System.in);  
        Ticket obj_Ticket; //creating an object using ticket class
        Person obj_Person; //creating an object using person class

        int row = seat_row_validate();
        int seat_amount = 14;
        if (row == 1 || row == 2){ //setting the exact seat amount accordingly to the row number
            seat_amount = 12;
        }
        int column = seat_column_validate(seat_amount);

        if (seats[row][column-1]==1){ //Checking whether the seat is already booked or not
            System.out.println("\nThe seat is already booked.Please try another seat \n");
        }
        else{
            //asking for user details
            System.out.print("\nEnter your name   :");
            String enter_name=input3.nextLine();
            System.out.print("Enter your surname:");
            String enter_surname=input3.nextLine();
            System.out.print("Enter your email  :");
            String enter_email=input3.nextLine();
           

            //selecting the price of the ticket accordinly to the seat number
            double price=0;
            if (column<=5){
                price=200;
            }
            else if(column<=9){
                    price=150;
                }
                else{
                    price=180;
                }
            
            char row_letter = (char) ('A' + row); //converting the row number to a letter(A,B,C,D)
            obj_Person = new Person(enter_name, enter_surname, enter_email); //calling the constructor 
            obj_Ticket = new Ticket(row_letter, column, price, obj_Person);

            Ticket[] array_ticket = Arrays.copyOf(tickets,tickets.length+1); //creating a new array called array_ticket with an additional space and copyinh all the elements in the origanal array(tickets)
            array_ticket[array_ticket.length-1]=obj_Ticket; //add the new element of obj_Ticket at the end
            tickets = array_ticket; //assinging the new array to the original array

            seats[row][column-1]=1;
            System.out.println("\nYour booking is successfully  confirmed.\n");
            obj_Ticket.save();
        }
       
    }

    public void cancel_seat(){
        int row = seat_row_validate();
        int seat_amount = 14;
        if (row == 1 || row == 2){  //setting the exact seat amount accordingly to the row number
            seat_amount = 12;
        }
        int column = seat_column_validate(seat_amount);

        if (seats[row][column-1]==0){ //Checking whether the seat is already booked or not
            System.out.println("\nThe seat has not been reserved, hence cancellation is not applicable at this moment. \n");
        }
        else{
            Ticket[] cancel_Tickets = new Ticket[tickets.length-1]; //creating a new Ticket array called cancel_Tickets and making sure, that array's length is one less than the original array's length
            char row_letter = (char) ('A' + row);
            int arrayindex =0; //index of the cancel_Tickets' array
            for (int a=0; a<tickets.length; a++){ 
                if (tickets[a].get_row() != row_letter || tickets[a].get_seat() != column){ //checking whether the row and column is not equal to the removing ticket
                    cancel_Tickets[arrayindex] = tickets[a];
                    arrayindex++;
                }
                else{
                    tickets[a].removeinformation(); //if the ticket was canceled it'll remove all the information from the text file
                }
            }
            tickets = cancel_Tickets;
            seats[row][column-1]=0; //switching the current booked seat status into a empty seat 
            System.out.println("\nSeat cancelation is successsful\n");

        }
    }

    public void show_seating_plan(){
        
        // Printing the grid with a space between each character
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j]==0){
                    System.out.print('O' + " ");
                }
                else{
                    System.out.print('X' + " ");
                }
            }
            System.out.println(); // Move to the next line after each row
            System.out.println();
        }
        System.out.println();
    }

    public void find_first_available(){
        int count=0;
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++){
                if (seats[row][col]==0){
                    char rowLetter = (char) ('A' + row); //convert row number to row letter.(A=65,B=65+1,C=65+2,D=65+3)~in ASCII vals as A is 65 in ASCII
                    System.out.println("Seat : " + rowLetter + (col+1) + " " + "is available");
                    count++;
                    break;
                }
            }
            if (count==1){ //if an available seat was found in the inner for loop,then it'll break the outter for loop
                break;
            }
        }
        if (count==0){ //if all seats are booked , it'll come to this if statement and state there ain't any seats available
            System.out.println("There are no seats available");
        }
    }

    public void print_tickets_info(){ //printing the all  booked tickets information and the total price of booked tickets
        int j;
        double total_price=0;
        for(j=0; j<tickets.length; j++){
            tickets[j].print_all_info();
            total_price = tickets[j].get_price() + total_price;  
        }
        System.out.println("\n Total price is : £"+ total_price + "\n");

    }

    public void search_ticket(){
        int row = seat_row_validate();
        int seat_amount = 14;
        if (row == 1 || row == 2){  //setting the exact seat amount accordingly to the row number
            seat_amount = 12;
        }
        int column = seat_column_validate(seat_amount);

        if (seats[row][column-1]==0){ //Checking whether the seat is already booked or not
            System.out.println("\nThis seat is available \n");
        }
        else{
            
            //searching the ticket array according to the given information(row,column), and printing all the information regarding the booked ticket
            for (int b=0; b<tickets.length; b++){ 
                char row_letter = (char) ('A' + row);
                if (tickets[b].get_row() == row_letter && tickets[b].get_seat() == column){ //checking whether the row and column is equal to the searching ticket
                    System.out.println("\nThis seat is already booked. Given shows the further details of the booking :\n");
                    tickets[b].print_all_info();
                    break;
                }
            }
        }   

    }

    public static void main(String[] args){
        
        w2051689_PlaneManagement my_obj= new w2051689_PlaneManagement(); //creating an object using the own class
        //setting all the seats to the available status
       
        Arrays.fill(my_obj.A, 0);
        Arrays.fill(my_obj.B, 0);
        Arrays.fill(my_obj.C, 0);
        Arrays.fill(my_obj.D, 0);

        System.out.println("Welcome to the plane management application");
        boolean quit =  false;
        while(!quit){
            System.out.println("*******************************************************************");
            System.out.println("*                      MENU OPTIONS                               *");
            System.out.println("*******************************************************************");
        
            System.out.println("1)Buy a seat"); 
            System.out.println("2)Cancel a seat"); 
            System.out.println("3)Find first available seat"); 
            System.out.println("4)Show seating plan"); 
            System.out.println("5)Print tickets information and total sales");       
            System.out.println("6)Search ticket"); 
            System.out.println("0)Quit"); 
            System.out.println("*******************************************************************");

            System.out.println();
            Scanner input=new Scanner(System.in);
            //System.out.print("Please select an option:");
            int option;
            while (true) {     
                System.out.print("Please select an option:");
                try{
                    option=input.nextInt();
                    if(option >= 0 && option <= 6){ //Checking whether the user has entered a valid option using error handling
                        break;
                    }
                    else{
                        System.out.println("Please enter a valid option ");
                        System.out.println();
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Invalid input. Please enter a valid option.");
                    input.nextLine();  //Invalid input is being cleared from the scanner buffet
                    System.out.println();

            
                }  
            }
           
            

            switch (option) {
                case 1:
                    my_obj.buy_seat();
                    break;
                case 2:
                    my_obj.cancel_seat();
                    break;
                case 3:
                    my_obj.find_first_available();
                    break;
                case 4:
                    my_obj.show_seating_plan();  
                    break;
                case 5:
                    my_obj.print_tickets_info();
                    break;
                case 6:
                    my_obj.search_ticket(); 
                    break;
                case 0:
                    System.out.println("System Existing");
                    quit = true;
                    break;
                    
                default:
                    System.out.println("Invalid input.Please enter a correct number");
                    break;


            }
        }
    }
}

