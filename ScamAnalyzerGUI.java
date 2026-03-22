import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScamAnalyzerGUI {

    private JTextArea messageArea;
    private JTextField phoneField;
    private JTextField suspiciousWordField;
    private JLabel riskLabel;
    private JLabel statusLabel;
    private JLabel confidenceLabel;
    private JTextArea scamListArea;
    private JTextArea detectedWordsArea;

    private ArrayList<String> scamPhoneNumbers = new ArrayList<>();
    private AnalyzeMessage analyzer = new AnalyzeMessage();

    public ScamAnalyzerGUI() {

        JFrame frame = new JFrame("SMS Scam Detection Simulator");
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Message Area
        frame.add(new JLabel("Message:"));
        messageArea = new JTextArea(5, 40);
        frame.add(new JScrollPane(messageArea));

        // Phone Number
        frame.add(new JLabel("Phone Number:"));
        phoneField = new JTextField(25);
        frame.add(phoneField);

        // Analyze Button
        JButton analyzeButton = new JButton("Analyze Message");
        frame.add(analyzeButton);

        // Save Button (Always Enabled)
        JButton saveButton = new JButton("Save Number to Scam List");
        frame.add(saveButton);

        // Risk Output
        riskLabel = new JLabel("Risk Score: 0");
        frame.add(riskLabel);

        statusLabel = new JLabel("Status: ");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(statusLabel);

        confidenceLabel = new JLabel("Confidence: 0%");
        frame.add(confidenceLabel);

        // Detected Words Display
        frame.add(new JLabel("Detected Suspicious Words:"));
        detectedWordsArea = new JTextArea(3, 40);
        detectedWordsArea.setEditable(false);
        frame.add(new JScrollPane(detectedWordsArea));

        // Saved Scam Numbers
        frame.add(new JLabel("Saved Scam Phone Numbers:"));
        scamListArea = new JTextArea(5, 40);
        scamListArea.setEditable(false);
        frame.add(new JScrollPane(scamListArea));

        JButton clearButton = new JButton("Clear Scam List");
        frame.add(clearButton);

        // Add Suspicious Word
        frame.add(new JLabel("Add Suspicious Word:"));
        suspiciousWordField = new JTextField(15);
        frame.add(suspiciousWordField);

        JButton addWordButton = new JButton("Add Word");
        frame.add(addWordButton);

        // =========================
        // ANALYZE BUTTON LOGIC
        // =========================
        analyzeButton.addActionListener(e -> {

            String message = messageArea.getText();
            String phone = phoneField.getText();

            int score = 0;
            detectedWordsArea.setText("");

            for(String word : analyzer.keyWords) {
                if(message.toLowerCase().contains(word)) {
                    score++;
                    detectedWordsArea.append(word + "\n");
                }
            }

            riskLabel.setText("Risk Score: " + score);

            int confidence = score * 20;
            if(confidence > 100) confidence = 100;
            confidenceLabel.setText("Confidence: " + confidence + "%");

            if(score >= 3) {
                statusLabel.setText("Status: HIGH RISK OF SCAM!");
                statusLabel.setForeground(Color.RED);
            } 
            else if(score > 0) {
                statusLabel.setText("Status: Potential Risk");
                statusLabel.setForeground(Color.ORANGE);
            } 
            else {
                statusLabel.setText("Status: Low Risk");
                statusLabel.setForeground(Color.GREEN);
            }
        });

        // =========================
        // SAVE BUTTON LOGIC
        // =========================
        saveButton.addActionListener(e -> {

            String phone = phoneField.getText();

            if(phone.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a phone number first.");
                return;
            }

            if(!scamPhoneNumbers.contains(phone)) {
                scamPhoneNumbers.add(phone);
                updateScamList();
                JOptionPane.showMessageDialog(frame, "Number Saved Successfully!");
            } 
            else {
                JOptionPane.showMessageDialog(frame, "Number Already Exists in Scam List.");
            }
        });

        // Add Word Button
        addWordButton.addActionListener(e -> {
            String newWord = suspiciousWordField.getText();
            if(!newWord.isEmpty()) {
                analyzer.addSuspiciousWord(newWord);
                suspiciousWordField.setText("");
            }
        });

        // Clear List Button
        clearButton.addActionListener(e -> {
            scamPhoneNumbers.clear();
            updateScamList();
        });

        frame.setVisible(true);
    }

    private void updateScamList() {
        scamListArea.setText("");
        for(String number : scamPhoneNumbers) {
            scamListArea.append(number + "\n");
        }
    }

    public static void main(String[] args) {
        new ScamAnalyzerGUI();
    }
}