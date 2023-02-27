import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ContactApplication {
    public static void main(String[] args){
        Path contactsPath = Paths.get("data", "contacts.txt");
        List<String> contactList;
        try {
            contactList = Files.readAllLines(contactsPath);
            for (int i = 0; i < contactList.size(); i += 1) {
                System.out.println((i + 1) + ": " + contactList.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
