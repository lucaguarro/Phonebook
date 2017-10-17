
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class PhoneBook
{

    HashMap<Long, Contact> contacts;
    ArrayList<Call> callHistory;
    private long[] favorites;
    
    static final String DIVIDER = "--------------------\n";
    
    public PhoneBook()
    {
        this.contacts = new HashMap<Long, Contact>();
        this.callHistory = new ArrayList<Call>();
        this.favorites = new long[5];
    }
    
    /**
     * Get a contact object by its number
     * @param favorites2 Integer representing a contact's phone number
     * @return Contact The contact object
     */
    public Contact getContact(long favorites2)
    {
    	return contacts.get(favorites2);
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
     * Delete a contact from the phonebook
     * @param number - Phonenumber of contact to remove
     */
    public void deleteContact(long number)
    {
    	contacts.remove(number);
    }
    
    /**
     * Delete a contact from the phonebook
     * @param name - Name of contact to remove
     */
    public void deleteContact(String name)
    {
    	deleteContact(getContact(name).getPhoneNumber());
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
    public boolean isContact(long number)
    {
    	return getContact(number) != null;
    }
    
    /**
     * Add a contact to a specific favorite spot and add a path to their image
     * @param phoneNumber - Phone number of contact to add
     * @param priority - What slot to add them to
     * @return
     */
    public boolean addFavorite(long phoneNumber, int priority, String filePath)
    {
    	if(isContact(phoneNumber) && favorites[priority] <= 0)
    	{
    		favorites[priority - 1] = phoneNumber;
    		getContact(phoneNumber).setImagePath(filePath);
    		return true;
    	}
    	return false;
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
    	long number1 = favorites[pos1 - 1];
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
    	for(long number : favorites)
    	{
    		if (number > 0)
    			toString += index + ". " + getContact(number).getName() + "\n";
    		index++;
    	}
    	return toString;
    }
    
    /**
     * Determines whether there is a favorite contact at a given slot
     * @param justDigits - integer 1-5 representing slot in favorites
     * @return boolean - True if a favorite exists at this location, false otherwise
     */
    public boolean isFavorite(int justDigits)
    {
    	if(favorites[justDigits - 1] > 0)
    	{
    		return getContact(favorites[justDigits - 1]) != null;
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
     * @param justDigits Phone number in integer form
     * @param outgoing Boolean whether or not its outgoing
     */
    public void doCall(long justDigits, boolean outgoing)
    {
    	for (Call call : callHistory)
    	{
    		if(call.getNumber() == justDigits)
    		{
    			//TODO: check if date is within 24 hours
    			call.addHistory(outgoing);
    			return;
    		}
    	}
    	//Only executes if there isn't already a logged call
    	Call newCall = new Call(justDigits, outgoing);
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
    			history += DIVIDER + "Number: " + Contact.formatPhoneNumber(call.getNumber()) + "\n";
    		}
    		history += call.callInfoToString();
    	}
    	return history;
    }
   

}

