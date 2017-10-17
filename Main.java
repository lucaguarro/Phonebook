import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static Scanner reader = new Scanner(System.in);
	static PhoneBook myPhonebook = new PhoneBook();
	static String INVALIDINPUT = "You did not enter a valid input. Please try again.";
	
	public static void main(String[] args) {
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
			else {
				System.out.println(INVALIDINPUT);
				System.out.println(Arrays.toString(options));
			}
			if(!endProgram) {
				System.out.println("Welcome to your phone. Please choose an option");
				System.out.println(Arrays.toString(options));				
			}
		}while(!input.equals("DONE") || endProgram);
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
			if(justDigits >= 5 && justDigits < 0) {
				if(myPhonebook.isFavorite((int) justDigits)) {
					myPhonebook.doCall(myPhonebook.getFavorite((int) justDigits), outgoing);
				}
			}
			else if(input.replaceAll("\\D", "").length() == 10) {
				myPhonebook.doCall(justDigits, outgoing);
			}
			else if(myPhonebook.isContact(input)) {
				//If the contact was found
				myPhonebook.doCall(myPhonebook.getContact(input), outgoing);
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
		System.out.println("Please choose an option");
		String[] options = new String[]{"1: Get Call History","2: Edit Phonebook","3: Edit Favorites", "<: Go Back"};
		System.out.println(Arrays.toString(options));
		input = reader.next();
		boolean inputValid;
		do {
			inputValid = true;
			if(input.equals("DONE")) {
				System.out.println("Exiting...");
				return true;
			}
			else if(input.equals("1")) {
				System.out.println(myPhonebook.getCallHistory());
			}
			else if(input.equals("2")) {
				endProgram = editPhonebookMenu();
			}
			else if(input.equals("3")) {
				
			}
			else if(input.equals("<")) {
				return false;
			}
			else {
				inputValid = false;
			}
		}while(!inputValid || endProgram);
		return false;
	}

	private static boolean editPhonebookMenu() {
		String input = "";
		String[] options = new String[]{"1: Add Contact","2: Edit Contact","3: Delete Contact", "4: Display Contacts", "<: Go Back"};
		boolean endProgram = false;
		//String[] options = new String[]{"1: Get Call History","2: Edit Phonebook","3: Edit Favorites", "4: Go Back"};	
		input = reader.next();
		boolean inputValid;
		do {
			System.out.println("Please choose an option");
			System.out.println(Arrays.toString(options));
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
				//Show contacts here
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

	private static boolean deleteContactDialog() {
		// TODO Auto-generated method stub
		
	}

	private static boolean editContactDialog() {
		// TODO Auto-generated method stub
		
	}

	private static boolean addContactDialog() {
		// TODO Auto-generated method stub
		String[] contactPrompts = new String[]{"Please type in the name of the contact you would like to add"
				,"Please enter the number", "Please enter the email", "Please enter any notes"};
		String input;
		String name, email, notes;
		long number;
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
			else if(input.equals("DONE"){
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
	}

}
