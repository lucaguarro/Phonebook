
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class PhoneBook
{

    HashMap<Integer, Contact> contacts;
    ArrayList<Call> callHistory;
    private int[] favorites;
    
    public PhoneBook()
    {
        this.contacts = new HashMap<Integer, Contact>();
        this.callHistory = new ArrayList<Call>();
        this.favorites = new int[5];
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
    
    /**
     * 
     * @param priority - Which favorite to return from 1 - 5 (Does not start at 0)
     * @return Contact - favorite contact at this location
     */
    public Contact getFavorite(int priority)
    {
    	return getContact(favorites[priority - 1]);
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
    
    public void displayAllContacts()
    {
    	ArrayList<Contact> mapValues = new ArrayList<>(contacts.values());
    	Collections.sort(mapValues);
    	for (Contact contact : mapValues)
    	{
    		System.out.println(contact);
    	}
    }
    
    public void addCall(Call call)
    {
    	callHistory.add(call);
    }

}

