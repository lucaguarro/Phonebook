import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static Scanner reader = new Scanner(System.in);
	static PhoneBook myPhonebook = new PhoneBook();
	
	public static void main(String[] args) {
		showMainMenu();
		reader.close();
	}

	private static void showMainMenu() {
		boolean endProgram = false;
		String input = "";
		System.out.println("Welcome to your phone. Enter a 10-digit phonenumber, name, or"
				+ " 1-5 to choose a preset favorite");
		System.out.println("Enter \"options\" to do other stuff. At any time type \"DONE\" to exit");
		do {
			input = reader.next();
			if(input.equals("options")) {
				endProgram = showOptions();
				if(!endProgram) { //Go back was prompted
					System.out.println("Enter a 10-digit phonenumber, name,"
							+ " 1-5 to choose a preset favorite"
							+ " or options to enter the options menu");
				}
			}
			
		}while(!input.equals("DONE") || endProgram);
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
				System.out(myPhonebook.getCallHistory());
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
