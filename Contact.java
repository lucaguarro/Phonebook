

public class Contact implements Comparable<Contact>{
    
    private String name;
    private int phoneNumber;
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
        
    public Contact(String name, int phoneNumber, String email, String notes) 
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.notes = notes;
    }
    
    public String toString()
    {
    	String text = "------------------------\n";
    	text += "Name: " + name + "\n";
    	text += "Phone Number: " + phoneNumber + "\n";
    	text += "Email: " + email + "\n";
    	if (!notes.equals(""))
    	{
    		text += "Notes:\n" + notes + "\n";
    	}
    	text += "------------------------\n";
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
    
    public int getPhoneNumber()
    {
    		return this.phoneNumber;
    }
    
    public void setPhoneNumber(int phoneNumber)
    {
    		this.phoneNumber = phoneNumber;
    }
    
    public String email()
    {
    		return this.email;
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
    



