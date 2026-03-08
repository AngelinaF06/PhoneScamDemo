
public class Message {
// fields
 private String senderPhoneNumber;
 private String scamMessage;
 
// setters
 public void setMessage(String scamMessage) {
	 this.scamMessage = scamMessage;
 }
 public void setPhoneNumber(String senderPhoneNumber) {
	 this.senderPhoneNumber = senderPhoneNumber;
 }
// getters:
 public String getMessage() {
	 return scamMessage;
 }
 public String getPhoneNumber() {
	 
	 return senderPhoneNumber;
 }

 // Constructor:
 public Message(String scamMessage, String senderPhoneNumber) {
	 this.scamMessage = scamMessage;
	 this.senderPhoneNumber = senderPhoneNumber;
 }
 
 // PrintInfo method
 public void printInfo() {
	 System.out.println("Sender: " + senderPhoneNumber + "\nMessage: " + scamMessage);
 }
 
}
