
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class PhoneBook
{

    HashMap<Integer, Contact> contacts;
    ArrayList<Call> callHistory;
    private int[] favorites;
    
    static final String DIVIDER = "--------------------\n";
    
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
     * @param name - String name of contact
     * @return True if it is, false if it isnt
     */
    public boolean isContact(String name)
    {
    	return getContact(name) != null;
    }
    
    /**
     * Check if a contact is in the phonebook
     * @param number - int phone number of contact
     * @return True if it is, false if it isnt
     */
    public boolean isContact(int number)
    {
    	return getContact(number) != null;
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
     * Swaps the positions of two favorites in the hierarchy
     * @param pos1 First position to swap
     * @param pos2 Second position to swap
     */
    public void swapFavorite(int pos1, int pos2)
    {
    	int number1 = favorites[pos1 - 1];
    	favorites[pos1 - 1] = favorites[pos2 - 1];
    	favorites[pos2 - 1] = number1;
    }
    
    /**
     * Removes the favorite at a given position in hierarchy
     */
    public void removeFavorite(int priority)
    {
    	favorites[priority - 1] = 0;
    }
    
    /**
     * Generate a string containing all the favorites
     * @return
     */
    public String favoritesToString()
    {
    	String toString = "";
    	int index = 1;
    	for(int number : favorites)
    	{
    		if (number > 0)
    			toString += index + ". " + getContact(number).getName() + "\n";
    	}
    	return toString;
    }
    
    /**
     * Determines whether there is a favorite contact at a given slot
     * @param priority - integer 1-5 representing slot in favorites
     * @return boolean - True if a favorite exists at this location, false otherwise
     */
    public boolean isFavorite(int priority)
    {
    	if(favorites[priority] > 0)
    	{
    		return getContact(favorites[priority]) != null;
    	}
    	return false;
    }

    /**
     * Adds a contact to the phonebook
     * @param contact - Contact object you wish to add
     */
    public void addContact(Contact contact)
    {
        this.contacts.put(contact.getPhoneNumber(), contact);
    }

    /**
     * Prints the contact to console
     * @param name - String name of contact to print
     */
    public void displayContact(String name)
    {
    	System.out.println(getContact(name));
    }
    
    /**
     * Prints the contact to console
     * @param number - Int number of contact to print
     */
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
     * @param outgoing Boolean whether or not its outgoing
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
    
    /**
     * Adds a call to callHistory, unless there already was a recent call, then it adds to to that call's info
     * @param contact - Contact you want to call
     * @param outgoing Boolean whether or not its outgoing
     */
    public void doCall(Contact contact, boolean outgoing)
    {
    	doCall(contact.getPhoneNumber(), outgoing);
    }
    
    /**
     * Generate the call history in text format
     * @return String containing entire call history
     */
    public String getCallHistory()
    {
    	String history = "";
    	for (Call call : callHistory)
    	{
    		//Check if there's a contact so we can think about displaying it
    		if (isContact(call.getNumber()))
    		{
    			history += getContact(call.getNumber()).toString();
    		}
    		else
    		{
    			history += DIVIDER + call.getNumber() + "\n";
    		}
    		history += call.callInfoToString();
    	}
    	return history;
    }
   

}

