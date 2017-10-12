
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
    
    /**
     * Get a contact object by its number
     * @param number Integer representing a contact's phone number
     * @return Contact The contact object
     */
    public Contact getContact(int number)
    {
    	return contacts.get(number);
    }
    
    /**
     * Get a contact object by its name
     * @param name Must be exact name of contact
     * @return Contact The contact object
     */
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
     * Check if a contact is in the phonebook
     * @param name
     * @return True if it is, false if it isnt
     */
    public boolean isContact(String name)
    {
    	return getContact(name) != null;
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

    /**
     * Adds a contact to the phonebook
     * @param contact - Contact object you wish to add
     */
    public void addContact(Contact contact)
    {
        this.contacts.put(contact.getPhoneNumber(), contact);
    }

    public void displayContact(String name)
    {
    	System.out.println(getContact(name));
    }
    
    public void displayContact(int number)
    {
    	System.out.println(getContact(number));
    }
    
    /**
     * Sorts the hashMap of contacts and prints them all to console
     */
    public void displayAllContacts()
    {
    	ArrayList<Contact> mapValues = new ArrayList<>(contacts.values());
    	Collections.sort(mapValues);
    	for (Contact contact : mapValues)
    	{
    		System.out.println(contact);
    	}
    }
    
    /**
     * Adds a call to callHistory, unless there already was a recent call, then it adds to to that call's info
     * @param number Phone number in integer form
     * @param outgoing Boolean
     */
    public void doCall(int number, boolean outgoing)
    {
    	for (Call call : callHistory)
    	{
    		if(call.getNumber() == number)
    		{
    			//TODO: check if date is within 24 hours
    			call.addHistory(outgoing);
    			return;
    		}
    	}
    	//Only executes if there isn't already a logged call
    	Call newCall = new Call(number, outgoing);
    	callHistory.add(newCall);
    }
   

}

