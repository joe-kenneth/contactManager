import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactApplication {
    public static void main(String[] args){
        Path contactsPath = Paths.get("data", "contacts.txt");
        List<String> contactFile;
        ArrayList<Contact> contactList = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        int userInput = 0;

        try {
            contactFile = Files.readAllLines(contactsPath);

            for (String contactInfo : contactFile){
                String [] result = contactInfo.split(",");
                Contact contactTemp = new Contact(result[0], result[1]);
                contactList.add(contactTemp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true){
            System.out.print("""
                    
                    1. View contacts.
                    2. Add a new contact.
                    3. Search a contact by name.
                    4. Delete an existing contact.
                    5. Exit.
                    Enter an option (1, 2, 3, 4 or 5):
                    """);

            try {
                userInput = Integer.parseInt(scan.nextLine());
            }
            catch (Exception e){
                System.out.println("That's not even a Int!. Enter an Int!!!!!!");
                continue;
            }
            if (userInput == 1){
                System.out.println("Name | Phone Number");
                System.out.println("----------------------------");
                for (Contact contact : contactList){
                    System.out.println(contact.getName() + " | " + contact.getPhoneNumber());
                }
            } else if (userInput == 2) {
                System.out.println("Enter your name");
                String name = scan.nextLine();
                System.out.println("Enter your 10 digit phone number");
                String phoneNumber = scan.nextLine();
                String phoneNumDashes = phoneNumber.substring(0,3) + "-" + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6);
                System.out.println(name);
                Contact contact = new Contact(name, phoneNumDashes);
                contactList.add(contact);
            } else if (userInput == 3) {

            }else if (userInput == 4) {

            }else if (userInput == 5) {
                System.out.println("You are exiting the program. Goodbye!");
                break;
            }
            else {
                System.out.println("This is not a correct input. Try again punk!");
            }
        }
    }
}
