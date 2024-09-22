public class Person {
    private String name="";
    private String surname="";
    private String email="";

    //constructor
    public Person(String name_person,String surname_person, String email_person){
        this.name=name_person;
        this.surname=surname_person;
        this.email=email_person;

    }

    //Setting all the variables (Setters)

    public void set_name(String new_name){
        this.name=new_name;
    }

    public void set_surname(String new_surname){
        this.surname=new_surname;
    }

    public void set_email(String new_email){
        this.surname=new_email;
    }

    //Getting all the variables (Getters)

    public String get_name(){
        return name;
    }

    public String get_surname(){
        return surname;
    }

    public String get_email(){
        return email;
    }

    //Printing all the information

    public void print_all_info(){
        System.out.println("Name    : "+ name );
        System.out.println("Surname : "+ surname );
        System.out.println("Email   : "+ email );
        System.out.println();
    }

    



    
}


