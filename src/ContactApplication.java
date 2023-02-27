import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactApplication {
    public static void main(String[] args) {
        Path contactsPath = Paths.get("data", "contacts.txt");
        List<String> contactFile;
        ArrayList<Contact> contactList = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        int userInput;

        try {
            contactFile = Files.readAllLines(contactsPath);
        } catch (IOException e) {
            System.out.println("File not read correctly");
            throw new RuntimeException(e);
        }

        ContactApplication.readFile(contactFile, contactList);

        while (true){
            try {
                userInput = ContactApplication.callMenu(scan);
            }
            catch (Exception e){
                System.out.println("That's not even a Int!. Enter an Int!!!!!!");
                continue;
            }

            if (userInput == 1){
                ContactApplication.viewContacts(contactList);
            } else if (userInput == 2) {
                ContactApplication.addContact(contactList, scan, contactFile);
            } else if (userInput == 3) {
                ContactApplication.searchContact(scan, contactList);
            }else if (userInput == 4) {
                ContactApplication.deleteContact(scan, contactList, contactFile);
            }else if (userInput == 5) {
                ContactApplication.exitMenu(contactsPath, contactFile);
                break;
            }
            else {
                System.out.println("This is not a correct input. Try again punk!");
            }
        }
    }//End of Main method

    /**
     * The callMenu method takes in a Scanner object and prints the contact menu.
     * This method returns an int that was obtained from the user input.
     **/
    public static int callMenu(Scanner scan){
        System.out.print("""
                    
                    1. View contacts.
                    2. Add a new contact.
                    3. Search a contact by name.
                    4. Delete an existing contact.
                    5. Exit.
                    Enter an option (1, 2, 3, 4 or 5):
                    """);
        return Integer.parseInt(scan.nextLine());
    }

    //The viewContacts method takes in and prints out our contacts lists.
    public static void viewContacts(ArrayList<Contact> contactList){
        System.out.println("Name | Phone Number");
        System.out.println("----------------------------");
        for (Contact contact : contactList){
            System.out.println(contact.getName() + " | " + contact.getPhoneNumber());
        }
    }

    /**
     * The addContact method prompts the user for a new contact name and phone number.
     * The user input is checked for the correct phone number length.  The user will be prompted until the correct length is inputted.
     * The new contact and number is added to the contact list.
     **/
    public static void addContact(ArrayList<Contact> contactList, Scanner scan, List<String> contactFile){
        System.out.println("Enter your name");
        String name = scan.nextLine();
        System.out.println("Enter your 10 digit phone number");
        String phoneNumber = "";

        while(true){
            phoneNumber = scan.nextLine();
            if (phoneNumber.length() != 10){
                System.out.println("Incorrect phone number length. Please enter a 10 digit number");
                continue;
            }
            break;
        }

        String phoneNumDashes = phoneNumber.substring(0,3) + "-" + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6);
        System.out.println(name);
        Contact contact = new Contact(name, phoneNumDashes);
        contactList.add(contact);
        String tempString = contact.getName() + "," + contact.getPhoneNumber();
        contactFile.add(tempString);
    }

    /**
     * The searchContact method searches the contact list for the users entered contact.
     * If the user inputs a contact that isn't listed, they are notified that the contact doesn't exist.
     **/
    public static void searchContact(Scanner scan, ArrayList<Contact> contactList){
        int exists = 1;
        System.out.println("Enter the name you want to search for");
        String nameSearch = scan.nextLine();
        for (Contact nameTemp : contactList){
            if (nameSearch.equals(nameTemp.getName()) ) {
                System.out.println("The phone number for " + nameTemp.getName() + " is " + nameTemp.getPhoneNumber());
                exists = 0;
                break;
            }
        }
        if (exists == 1){
            System.out.println("The contact " + nameSearch + " does not exist.");
        }
    }

    /**
     * The deleteContact method prompts the user for a name of a contact to be deleted.
     * If the contact provided is in the contact list, we delete the contact.
     * If the user inputs a contact that isn't listed, they are notified that the contact doesn't exist.
     **/
    public static void deleteContact(Scanner scan, ArrayList<Contact> contactList, List<String> contactFile){
        int exists = 1;
        int index = 0;
        System.out.println("Enter the name you want to delete");
        String nameSearch = scan.nextLine();
        for (Contact nameTemp : contactList){
            if (nameSearch.equals(nameTemp.getName()) ) {
                contactList.remove(nameTemp);
                contactFile.remove(index);
                System.out.println("The contact " + nameTemp.getName() + " has been deleted.");
                exists = 0;
                break;
            }
            index++;
        }
        if (exists == 1){
            System.out.println("The contact " + nameSearch + " does not exist.");
        }
    }


    //The exitMenu Method will exit the program when selected, and it will write to the contacts.txt data file.
    public static void exitMenu(Path contactsPath, List<String> contactFile){
        System.out.println("You are exiting the program. Goodbye!");

        try {
            Files.write(contactsPath, contactFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The readFile Method takes in the contacts' data,
     * parses the data into Contact objects,
     * and stores the contacts into the contacts list.
     **/
    public static void readFile(List<String> contactFile, ArrayList<Contact> contactList){
        for (String contactInfo : contactFile){
            String [] result = contactInfo.split(",");
            Contact contactTemp = new Contact(result[0], result[1]);
            contactList.add(contactTemp);
        }
    }
}
