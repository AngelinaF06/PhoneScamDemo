import java.util.Scanner;
import java.util.ArrayList;
public class Demo {

	public static void main(String[] args) {

		// variables:
		boolean continueProgram = true;
		Scanner scnr = new Scanner(System.in);
		String message;
		String scamPhoneNumber;
		String answer;
		String suspiciousWord;

		
		// ArrayList to Store Phone Numbers
		ArrayList<String> scamPhoneNums = new ArrayList<>();
		// Object to make an analysis
		AnalyzeMessage a1 = new AnalyzeMessage();
		// Loop to prompt user until false
    while(continueProgram) {
		// Welcome Message:
	 System.out.println("Hello Welcome to Phone Scam Demo console, please enter your message down below:");
	 message = scnr.nextLine();
	 
	 System.out.println("Please enter the message sender's phone number: ");
	 scamPhoneNumber = scnr.nextLine();
	 
	 
	// Object Message to use the methods
			Message m1 = new Message(message, scamPhoneNumber);
	// Methods being used
	        m1.printInfo();
	        a1.reportRiskScore(m1,0);
	        
	System.out.println("Do you want to save the message's phone user in the Phone Number Scams Library ? (yes / no)");
	        answer = scnr.nextLine();
	        if(answer.equalsIgnoreCase("yes")) {
	        scamPhoneNums.add(m1.getPhoneNumber());
	        // Storing it in the library
	        System.out.println("Phone Number Scams Library: ");
	        for(int i = 0; i < scamPhoneNums.size(); i++) {
	        	System.out.println(scamPhoneNums.get(i));
	        }
	      }
	System.out.println("Would you like to add a new suspicious word to the List ? (yes/no)");
	        answer = scnr.nextLine();
	        if(answer.equalsIgnoreCase("yes")) {
	        System.out.println("Please type it out: ");
	        suspiciousWord = scnr.nextLine();
	        a1.addSuspiciousWord(m1,suspiciousWord);	
	        }
	System.out.println("Do you want to analyze another message ? (yes/no)");
	        answer = scnr.nextLine();
	        if(answer.equalsIgnoreCase("no")) {
	        continueProgram = false;
	        scnr.close();
	        }

	}
}

}
