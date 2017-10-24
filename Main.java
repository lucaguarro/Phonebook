import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	private static final Exception Exception = null;
	static Scanner reader = new Scanner(System.in);
	static PhoneBook myPhonebook = new PhoneBook();
	static String INVALIDINPUT = "You did not enter a valid input. Please try again.";
	
	public static void main(String[] args) {
		myPhonebook.addContact(new Contact("Josh", 6617066161l, "josh@gmail.com", "Cool guy"));
		myPhonebook.addFavorite(6617066161l, 1, "/Users/LucaGuarro/Documents/joshpeck.jpg");
		myPhonebook.addContact(new Contact("Luca", 6615556161l, "luca@gmail.com", "QA Master"));
		myPhonebook.addFavorite(6615556161l, 2, "/Users/LucaGuarro/Documents/joshpeck.jpg\"");
		myPhonebook.addContact(new Contact("Gene", 5555555555l, "gene@gmail.com", "Deadpool is cool"));
		showMainMenu();
		reader.close();
	}

	private static void showMainMenu() {
		boolean endProgram = false;
		String input = "";
		String[] options = new String[]{"1: Make a call","2: Receive a call","3: See more options"};
		System.out.println("Welcome to your phone. Please choose an option");
		System.out.println(Arrays.toString(options));
		//System.out.println("Welcome to your phone. Enter a 10-digit phonenumber, name, or"
		//		+ " 1-5 to choose a preset favorite");
		System.out.println("At any time type \"DONE\" to exit");
		
		do {
			input = reader.next();
			if(input.equals("1")) {
				endProgram = doCall(true);
			}
			else if (input.equals("2")) {
				endProgram = doCall(false);
			}
			else if (input.equals("3")) {
				endProgram = showOptions();
			}
			else if (input.equals("DONE")) {
				endProgram = true;
			}
			else {
				System.out.println(INVALIDINPUT);
				System.out.println(Arrays.toString(options));
			}
			if(!endProgram) {
				System.out.println("Welcome to your phone. Please choose an option");
				System.out.println(Arrays.toString(options));				
			}
		}while(!endProgram);
		System.out.println("App turned off.");
	}

	private static boolean doCall(boolean outgoing) {
		String input = "";
		long justDigits = -1;
		if(outgoing) {
			System.out.println("Enter the 10-digit phonenumber, name, or preset favorite "
					+ "of the contact you would like to call");		
		}
		else {
			System.out.println("Enter the 10-digit phonenumber or name of the person who is calling you");
		}
		System.out.println("Type \"<\" to go back to main menu");

		do {
			
			input = reader.next();
			if(!input.replaceAll("\\D", "").equals("")) {
				String phoneNumber = input.replaceAll("[^0-9]", "");
				justDigits = Long.parseLong(phoneNumber);
			}
			if(justDigits <= 5 && justDigits > 0) {
				if(myPhonebook.isFavorite((int) justDigits)) {
					myPhonebook.doCall(myPhonebook.getFavorite((int)justDigits), outgoing);
					System.out.println("Phone call ended.");
					return false;
				}
				System.out.println("Favorite not found");
				return false;
			}
			else if(input.replaceAll("\\D", "").length() == 10) {
				myPhonebook.doCall(justDigits, outgoing);
				System.out.println("Phone call ended.");
				return false;
			}
			else if(myPhonebook.isContact(input)) {
				//If the contact was found
				myPhonebook.doCall(myPhonebook.getContact(input), outgoing);
				System.out.println("Phone call ended.");
				return false;
			}
			else if(input.equals("<")) {
				return false;
			}
			else{
				System.out.println(INVALIDINPUT);
			}
		}while(!input.equals("DONE"));
		return false;
	}

	private static boolean showOptions() {
		 //this boolean is used for the do-while loop
		boolean endProgram = false;
		String input = "";
		//System.out.println("Please choose an option");
		String[] options = new String[]{"1: Get Call History","2: View Phonebook","3: View Favorites", "<: Go Back"};
		//System.out.println(Arrays.toString(options));
		
		boolean inputValid;
		do {
			inputValid = true;
			System.out.println("Please choose an option");
			System.out.println(Arrays.toString(options));
			input = reader.next();
			if(input.equals("DONE")) {
				System.out.println("Exiting...");
				return true;
			}
			else if(input.equals("1")) {
				endProgram = getCallHistory();
			}
			else if(input.equals("2")) {
				endProgram = editPhonebookMenu();
			}
			else if(input.equals("3")) {
				endProgram = editFavorites();
			}
			else if(input.equals("<")) {
				return false;
			}
			else {
				inputValid = false;
			}
		}while(!inputValid || !endProgram);
		return endProgram;
	}

	private static boolean getCallHistory() {
		System.out.println("Please enter the name or number of the contact whose call history you want to display. "
				+ "Or enter 1 to display all contacts.");
		boolean validInput;
		long justDigits = -1;
		String input;
		do {
			validInput = true;
			input = reader.next();
			if(input.equals("<")) {
				return false;
			}
			else if(input.equals("DONE")) {
				return true;
			}
			if(input.replaceAll("\\D", "").length() == 10) {
				justDigits = Long.parseLong(input);
				System.out.println(myPhonebook.contactCallsToString(justDigits));
			}
			else if(input.equals("1")) {
				System.out.println(myPhonebook.getCallHistory());
			}
			else if(myPhonebook.isContact(input)) {
				System.out.println(myPhonebook.contactCallsToString(input));
				return false;
			}	
			else {
				System.out.println("That was not a valid number or contact name");
				System.out.println("Try again or enter 'DONE' to quit or '<' to go back");
				validInput = false;
			}
		} while (!validInput);

		return false;
	}

	private static boolean editFavorites() {
		String input = "";
		String[] options = new String[]{"1: Add Favorite","2: Swap Favorites","3: Delete Favorite","4: Show Favorites", "<: Go Back"};
		boolean endProgram = false;
		
		boolean inputValid;
		do {
			
			System.out.println("Please choose an option");
			System.out.println(Arrays.toString(options));
			input = reader.next();
			inputValid = true;
			if(input.equals("DONE")) {
				System.out.println("Exiting...");
				return true;
			}
			else if(input.equals("1")) {
				endProgram = addFavoriteDialog();
			}
			else if(input.equals("2")) {
				endProgram = swapFavoriteDialog();
			}
			else if(input.equals("3")) {
				endProgram = deleteFavoriteDialog();
			}
			else if(input.equals("4")) {
				endProgram = specificFavToShow();
			}
			else if(input.equals("<")) {
				return false;
			}
			else {
				inputValid = false;
			}
		}while(!inputValid);
		return endProgram;
	}

	private static boolean specificFavToShow() {
		System.out.println("Please enter the favorite you want to display");
		boolean validInput;
		int priority = -1;
		String input;
		do {
			validInput = true;
			input = reader.next();
			if(input.equals("<")) {
				return false;
			}
			else if(input.equals("DONE")) {
				return true;
			}
			try {
				priority = Integer.parseInt(input);
				if(priority < 1 && priority > 5) {
					throw Exception;
				} 
			}
			catch(Exception e) {
				System.out.println("That was not a number between 1 and 5.");
				System.out.println("Try again or enter 'DONE' to quit or '<' to go back");
				validInput = false;;
			}	
			if(priority > -1 && myPhonebook.isFavorite(priority)) {
				myPhonebook.favoriteFrame(priority);
			}
			else {
				System.out.println("You do not have that favorite stored");
				System.out.println("Try again or enter 'DONE' to quit or '<' to go back");
				validInput = false;
			}
		} while (!validInput);

		
		return false;
	}

	private static boolean deleteFavoriteDialog() {
		System.out.println("Please enter the favorite you want to delete");
		boolean validInput;
		int priority = -1;
		String input;
		do {
			validInput = true;
			input = reader.next();
			if(input.equals("<")) {
				return false;
			}
			else if(input.equals("DONE")) {
				return true;
			}
			try {
				priority = Integer.parseInt(input);
				if(priority < 1 && priority > 5) {
					throw Exception;
				} 
			}
			catch(Exception e) {
				System.out.println("That was not a number between 1 and 5.");
				System.out.println("Try again or enter 'DONE' to quit or '<' to go back");
				validInput = false;;
			}	
			if(priority > -1 && myPhonebook.isFavorite(priority)) {
				myPhonebook.removeFavorite(priority);
			}
			else {
				System.out.println("You do not have that favorite stored");
				System.out.println("Try again or enter 'DONE' to quit or '<' to go back");
				validInput = false;
			}
		} while (!validInput);

		
		return false;
	}

	private static boolean swapFavoriteDialog() {
		System.out.println("Please enter the favorites you want to swap");
		int swap[] = new int[2];
		int priority;
		String input;
		for(int i = 0; i < 2; i++) {
			if(i == 0) {
				System.out.println("First favorite");
			} else if (i == 1) {
				System.out.println("Second favorite");
			}
			input = reader.next();
			if(input.equals("DONE")) {
				return true;
			}
			else if(input.equals("<")) {
				return false;
			}
			try {
				priority = Integer.parseInt(input);
				if(priority >= 1 && priority <= 5) {
					swap[i] = priority;
				} else {
					throw Exception;
				}
				
			}
			catch(Exception e) {
				System.out.println("That was not a number between 1 and 5. Try again.");
				i--;
			}
		}
		myPhonebook.swapFavorite(swap[0], swap[1]);
		return false;
	}

	private static boolean addFavoriteDialog() {
		
		int priority = -1;
		String input;
		Contact c = new Contact();
		String[] favPrompts = new String [] {"Please enter the favorite you want to add", "Please enter the name of the contact"};
		int number;
		for(int i = 0; i < 2; i++) {
			System.out.println(favPrompts[i]);
			if(i == 0) {
				System.out.println("At any time you can enter '<' to go back or 'DONE' to exit");
			}
			input = reader.next();
			if(input.equals("<")) {
				return false;
			}
			else if(input.equals("DONE")){
				return true;
			}
			switch(i) {
				case 0:
					try {
						number = Integer.parseInt(input);
						if (number <= 0 || number > 5)
							throw new RuntimeException("Not between 1 and 5");
						priority = number;
					}
					catch(Exception e) {
						System.out.println("That is not a number between 1 and 5");
						i--;
					}
					break;
				case 1:
					if(myPhonebook.isContact(input)) {
						c = myPhonebook.getContact(input);
						System.out.println("Please enter the image path");
						String imagePath;
						imagePath = reader.next();
						c.setImagePath(imagePath);
					} else {
						System.out.println("That is not a contact in your phonebook");
						i--;
					}
					break;
			}
		}
		myPhonebook.addFavorite(c.getPhoneNumber(), priority, "");
		return false;
	}

	private static boolean editPhonebookMenu() {
		String input = "";
		String[] options = new String[]{"1: Add Contact","2: Edit Contact","3: Delete Contact", "4: Display Contacts", "<: Go Back"};
		boolean endProgram = false;
		
		boolean inputValid;
		do {
			System.out.println("Please choose an option");
			System.out.println(Arrays.toString(options));
			input = reader.next();
			inputValid = true;
			if(input.equals("DONE")) {
				System.out.println("Exiting...");
				return true;
			}
			else if(input.equals("1")) {
				endProgram = addContactDialog();
			}
			else if(input.equals("2")) {
				endProgram = editContactDialog();
			}
			else if(input.equals("3")) {
				endProgram = deleteContactDialog();
			}
			else if(input.equals("4")) {
				endProgram = displayContactsMenu();
			}
			else if(input.equals("<")) {
				return false;
			}
			else {
				inputValid = false;
			}
		}while(!inputValid || !endProgram);
		return endProgram;
	}

	private static boolean displayContactsMenu() {
		// TODO Auto-generated method stub
		String input = "";
		String options = "Enter 1 to display all contacts or the name of the contact you wish to display";
		boolean endProgram = false;
		boolean inputValid;
		do {
			inputValid = true;
			System.out.println(options);
			System.out.println("At any time you can type DONE to quit or < to go back");
			input = reader.next();
			if(input.equals("DONE")) {
				return true;
			}
			else if(input.equals("<")) {
				return false;
			}
			else if(input.equals("1")) {
				myPhonebook.displayAllContacts();
			}
			else if(myPhonebook.isContact(input)) {
				String name = input;
				myPhonebook.displayContact(name);
				System.out.println("Do you want to call this contact? Enter y or n.");
				input = reader.next();
				if(input.equals("y")) {
					myPhonebook.doCall(myPhonebook.getContact(name), true);
				}
				
				
			}
			else {
				System.out.println("That was not a name in your contacts");
				inputValid = false;
			}
		}while(!inputValid);
		return false;
	}

	private static boolean deleteContactDialog() {
		// TODO Auto-generated method stub
		boolean inputValid;
		System.out.println("Please type in the name of the contact you would like to delete");
		System.out.println("At any time you can enter '<' to go back or 'DONE' to exit");
		String input;
		do {
			input = reader.next();
			inputValid = true;
			if(input.equals("<")) {
				return false;
			}
			else if(input.equals("DONE")){
				return true;
			}
			if(myPhonebook.isContact(input)) {
				myPhonebook.deleteContact(input);
			}else {
				inputValid = false;
				System.out.println("That contact does not exist. Try again.");
				
			}
		}while(!inputValid);
		return false;
	}

	private static boolean editContactDialog() {
		// TODO Auto-generated method stub
		boolean inputValid;
		System.out.println("Please type in the name of the contact you would like to edit");
		System.out.println("At any time you can enter '<' to go back or 'DONE' to exit");
		String input;
		do {
			input = reader.next();
			inputValid = true;
			if(input.equals("<")) {
				return false;
			}
			else if(input.equals("DONE")){
				return true;
			}
			if(myPhonebook.isContact(input)) {
				return editingContact(myPhonebook.getContact(input));
			}else {
				inputValid = false;
				System.out.println("That contact does not exist. Try again.");
			}
		}while(!inputValid);		
		return false;
	}

	private static boolean editingContact(Contact contact) {
		// TODO Auto-generated method stub
		String[] options = new String[]{"1: Edit Name","2: Edit Number","3: Edit Email", "4: Edit Notes", "<: Go Back"};
		String input;
		boolean inputValid = true;
		do {
			System.out.println("Please choose a property to edit");
			System.out.println(Arrays.toString(options));	
			input = reader.next();
			if(input.equals("DONE")) {
				return true;
			}
			else if(input.equals("1")) {
				System.out.println("Please enter the new name:");
				input = reader.next();
				contact.setName(input);
			}
			else if(input.equals("2")) {
				System.out.println("Please enter the new number:");
				input = reader.next();
				try {
					long number = Long.parseLong(input);
					myPhonebook.changePhoneNumber(contact, number);
				}
				catch(Exception e) {
					System.out.println(INVALIDINPUT);
					inputValid = false;
				}
			}		
			else if(input.equals("3")) {
				System.out.println("Please enter the new email:");
				input = reader.next();
				contact.setEmail(input);
			}
			else if(input.equals("4")) {
				System.out.println("Please enter the new notes:");
				input = reader.next();	//Show contacts here
				contact.setNotes(input);
			} 
			else if(input.equals("<")) {
				return false;
			}
		}while(!inputValid);
		return false;
	}

	private static boolean addContactDialog() {
		// TODO Auto-generated method stub
		String[] contactPrompts = new String[]{"Please type in the name of the contact you would like to add"
				,"Please enter the number", "Please enter the email", "Please enter any notes"};
		String input;
		String name = "", email = "", notes = "", imagepath = "";
		long number = 0;
		int i = 0;
		for(String contactPrompt : contactPrompts) {
			System.out.println(contactPrompt);
			if(i == 0) {
				System.out.println("At any time you can enter '<' to go back or 'DONE' to exit");
			}
			input = reader.next();
			if(input.equals("<")) {
				return false;
			}
			else if(input.equals("DONE")){
				return true;
			}
			switch(i) {
				case 0:
					name = input;
					break;
				case 1:
					try {
						number = Long.parseLong(input);
					}
					catch(Exception e) {
						System.out.println(INVALIDINPUT);
						i--; //redo it
					}
					break;
				case 2:
					email = input;
					break;
				case 3:
					notes = input;
					break;
					
			}
			
			i++;
		}
		Contact c = new Contact(name, number, email, notes);
		myPhonebook.addContact(c);
		return false;
	}

}
