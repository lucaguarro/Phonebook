import java.util.Arrays;
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
		int justDigits = -1;
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
				justDigits = Integer.parseInt(input.replaceAll("\\D", ""));
			}
			if(justDigits >= 5 && justDigits < 0) {
				if(false/*getFavorite(justDigits)*/) {
					
				}
			}
			else if(input.replaceAll("\\D", "").length() == 10) {
				//callNumber(int phonenumber, bool outgoing)
			}
			else if(false/*Search through contacts via input*/) {
				//If the contact was found
				//callNumber(phonenumer, outgoing)
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
		String input = "";
		System.out.println("Please choose an option");
		String[] options = new String[]{"1: Get Call History","2: Edit Phonebook","3: Edit Favorites", "4: Go Back"};
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
				//System.out(myPhonebook.getCallHistory());
			}
			else if(input.equals("2")) {
				
			}
			else if(input.equals("3")) {
				
			}
			else if(input.equals("4")) {
				return false;
			}
			else {
				inputValid = false;
			}
		}while(!inputValid);
		return false;
	}

}
