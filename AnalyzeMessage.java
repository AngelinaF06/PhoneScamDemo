import java.util.ArrayList;
import java.util.List;
public class AnalyzeMessage {
// fields
private Message msg;
public static int riskScore;
private String susWord;
public ArrayList <String> keyWords = new ArrayList<>(List.of("won", "prize", "urgent", "overdue", "unpaid", "click", "link", "social", "security", "credit", "card", "bank", "password", "iphone", "laptop"));
/* List of 12 most used Scam words in messages.
 * "won"
 * "prize"
 * "urgent"
 * "overdue"
 * "unpaid"
 * "click"
 * "social"
 * "security"
 * "credit"
 * "card"
 * "bank"
 * "password"
 */
public void reportRiskScore(Message msg, int riskScore) {
	this.msg = msg;	
	//String ArrayList composed of a keyWords list.
     
	// enhanced for loop to compare the string and the keyWord ArrayList.
   for(String message : keyWords) {
	   if(msg.getMessage().toLowerCase().contains(message)) {
		  System.out.println("Suspicious word found: " + message);
		riskScore++;
	   }    	
    }
   System.out.println("Risk Score: " + riskScore);
 // RiskScore rating
   if(riskScore >= 3)
   System.out.println("HIGH RISK OF SCAM !");
   else
   System.out.println("Potential Risk of Scam");
  }
public void addSuspiciousWord(Message msg, String susWord) {
	this.msg = msg;
	this.susWord = susWord;
	keyWords.add(susWord);
	if(msg.getMessage().toLowerCase().contains(susWord)) {
		System.out.println("Suspicious word found: " + susWord);
		riskScore++;
		
	}
}

	
}




