
import java.util.HashMap;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PhoneBook
{

    HashMap<Integer, Contact> contacts;
    ArrayList<Call> callHistory;
    
    public PhoneBook()
    {
        this.contacts = new HashMap<Integer, Contact>();
        this.callHistory = new ArrayList<Call>();
    }
    
    public Contact getContact(int number)
    {
    	return contacts.get(number);
    }
    
    public Contact getContact(String name)
    {
    	for (Contact contact : contacts.values())
    	{
    		if (contact.getName().equals(name))
    		{
    			return contact;
    		}
    	}
    	return null;
    }


    public void addContact(Integer number, Contact contact)
    {
        this.contacts.put(number, contact);
    }

    public void displayContact(String name)
    {
    	System.out.println(getContact(name));
    }
    
    public void displayContact(int number)
    {
    	System.out.println(getContact(number));
    }

}

