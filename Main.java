import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String inputLvl1 = "";
		// TODO Auto-generated method stub
		System.out.println("Welcome to your phone. Enter a 10-digit phonenumber, name, or"
				+ " 1-5 to choose a preset favorite");
		System.out.println("Enter \"options\" to do other stuff. At any time type \"DONE\" to exit");
		do {
			inputLvl1 = reader.next();
			
			
		}while(inputLvl1 != "DONE");
	}

}
