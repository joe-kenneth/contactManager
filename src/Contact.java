public class Contact {
    private String  name;
    private String phoneNumber;

    /** getName
     * method returns the name stored in the object
     */
    public String getName() {
        return name;
    }

    /** setName
     * sets the object name provided by the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /** getPhoneNumber
     * method returns the phone number stored in the object
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /** setPhoneNumber
     * set the phone number with the phone number provided
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /** Contact
     * constructor sets the name and the phone number
     */
    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
