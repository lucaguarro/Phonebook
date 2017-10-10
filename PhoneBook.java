
import java.util.HashMap;

public class PhoneBook
{

    HashMap<Integer, Contact> contacts;

    
    public PhoneBook()
    {
        this.contacts = new HashMap<Integer, Contact>();
    }
    
    private Contact getContact(int number)
    {
    	return contacts.get(number);
    }
    
    private Contact getContact(String name)
    {
    	for (Contact contact : contacts.values())
    	{
    		if (contact.getName().equals(name))
    		{
    			return contact;
    		}
    	}
    	return new Contact();
    }


    public void addContact(Integer number, Contact contact)
    {
        this.contacts.put(number, contact);
    }

    public void displayContact(String name)
    {
    	System.out.println(getContact(name));
    }

}

