

public class Contact implements Comparable<Contact>{
    
    private String name;
    private long phoneNumber;
    private String email;
    private String notes;
    private String imagePath;
    
    public Contact()
    {
    	this.name = "";
    	this.phoneNumber = -1;
    	this.email = "";
    	this.notes = "";
    }
        
    public Contact(String name, long phoneNumber, String email, String notes) 
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.notes = notes;
    }
    
    public static String formatPhoneNumber(long phoneNumber)
    {
    	String number = "" + phoneNumber; //that swag conversion
    	if(number.length() == 10)
    	{
    		return String.format("(%s) %s-%s", number.substring(0, 3), number.substring(3, 6), number.substring(6));
    	}
    	if(number.length() == 7)
    	{
    		return String.format("%s-%s", number.substring(0, 3), number.substring(3));
    	}
    	return number;
    }
    
    /**
     * Convert the contact to a string containing all sorts of information about it
     * @return String all that contact information
     */
    public String toString()
    {
    	String text = "------------------------\n";
    	text += "Name: " + name + "\n";
    	text += "Phone Number: " + formatPhoneNumber(phoneNumber) + "\n";
    	text += "Email: " + email + "\n";
    	if (!notes.equals(""))
    	{
    		text += "Notes:\n" + notes + "\n";
    	}
    	return text;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
    		this.name = name;
    }
    
    public long getPhoneNumber()
    {
    		return this.phoneNumber;
    }
    
    public void setPhoneNumber(long number)
    {
    		this.phoneNumber = number;
    }
    
    public String getEmail()
    {
    		return this.email;
    }
    
    public void setEmail(String email)
    {
    		this.email = email;
    }
    
    public String getNotes()
    {
    		return this.notes;
    }
    
    public void setNotes(String notes)
    {
    		this.notes = notes;
    }

	@Override
	public int compareTo(Contact otherContact) 
	{
		return this.name.compareTo(otherContact.name);
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
    



