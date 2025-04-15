import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TypingSpeedTestGUI extends JFrame implements ActionListener {

    private static final String[] SENTENCES = {
            "The quick brown fox jumps over the lazy dog.",
            "Pack my box with five dozen liquor jugs!",
            "Jived fox nymph grabs quick waltz.",
            "How vexingly quick daft zebras jump!",
            "Bright vixens jump; dozy fowl quack.",
            "The five boxing wizards jump quickly.",
            "Crazy Frederick bought many very exquisite opal jewels.",
            "We promptly judged antique ivory buckles for the next prize.",
            "Jack quietly moved up front and seized the big, yellow taxi.",
            "Sympathy for the devil: $666 worth of glam rock.",
            "The @ symbol is often used in email addresses like user@example.com.",
            "She said, \"Hello!\" and waved at 9 strangers passing by.",
            "Typing 1234567890 correctly is important for numeric accuracy.",
            "Use CTRL+C to copy and CTRL+V to paste.",
            "His favorite password was 'P@ssw0rd!' but it was easily cracked.",
            "Do you know the capital of Djibouti? It’s Djibouti!",
            "Why did the chicken cross the road? To test your typing speed!",
            "He earned 98.5% in the final exam—what a brilliant score!",
            "Math symbols like +, -, *, / are essential in coding.",
            "Escape characters such as \\n and \\t help format strings.",
            "She bought 12 apples, 9 bananas, and 7 kiwis for $15.",
            "Typing requires accuracy, rhythm, and a bit of flair.",
            "Underneath the surface, a quiet storm brews.",
            "One fish, two fish, red fish, blue fish.",
            "Speed, accuracy, and consistency: the trifecta of typing.",
            "In 2023, global internet users surpassed 5 billion!",
            "Avoid repeating charactersssss unless it's necessary!",
            "Curly braces { } are used frequently in programming.",
            "Keep calm and press the 'Enter' key.",
            "The wizard conjured fire with a flick of his wrist.",
            "A watched pot never boils, but an idle brain rusts.",
            "He said, 'Time is money,' and walked away.",
            "You’ve got 10 minutes to type as fast as you can!",
            "Success is 99% perspiration and 1% inspiration.",
            "Every great developer starts with a single semicolon (;).",
            "Don't forget to save your work: CTRL+S is your best friend.",
            "Syntax errors can crash your code and your mood!",
            "Her username was 'Qwerty@123'—simple yet effective.",
            "The sky darkened, thunder roared, and lightning cracked!",
            "This sentence contains every single letter: the quick brown fox...",
            "My lucky numbers are 3, 7, 21, 42, and 88.",
            "Ctrl + Alt + Delete can solve many computer issues.",
            "I owe you $40.75 from yesterday's dinner.",
            "Typing tests improve hand-eye coordination, no doubt.",
            "Email me at contact@typingpro.com for more info.",
            "He whispered, “Do not fear the dark, fear the silence.”",
            "High-quality code is readable, maintainable, and testable.",
            "123 Main Street, Apt #56, New York, NY 10001.",
            "The password must contain @, $, %, and a capital letter.",
            "Java, Python, C++, and JavaScript are popular programming languages.",
            "The concert starts at 7:45 PM; don’t be late!",
            "Mr. and Mrs. Smith celebrated their 25th anniversary in style.",
            "Backup your files every day; accidents happen!",
            "He ordered 2 burgers, 1 soda, and a large fry.",
            "Don't type in ALL CAPS unless you're yelling!",
            "Typing isn't just speed—it's a form of expression.",
            "The percentage symbol (%) often means ‘per hundred’.",
            "The lazy dog didn’t jump, it simply rolled over.",
            "Typing this sentence should take you exactly 10 seconds.",
            "Please use a semicolon (;) to end your statement.",
            "Brackets ( ) group things, while braces { } define blocks.",
            "He scored 99/100 and still wasn't satisfied.",
            "A bird in hand is worth two in the bush.",
            "The year 2000 was known as the Y2K bug era.",
            "Her Wi-Fi password was 'L0v3_My_D0g!'.",
            "The address was 404 Not Found—how ironic!",
            "Click 'OK' to proceed or 'Cancel' to exit.",
            "Can you beat the record: 120 WPM with 100% accuracy?",
            "The speed limit is 60 km/h, but he went 75!",
            "You must agree to the Terms & Conditions.",
            "Always sanitize your inputs: it's basic security!",
            "This-is-a-hyphenated-word-example.",
            "Be punctual: the event starts at exactly 9:00 AM.",
            "Curiosity killed the cat, but satisfaction brought it back.",
            "$1000 is a lot to pay for a chair with no cushion.",
            "He typed 88 words per minute, with zero errors!",
            "Zebras zigzagged across the zebra crossing.",
            "Some emojis 😀😎👍 don't work well in plain text.",
            "The 3 musketeers fought valiantly until the end.",
            "She's the co-founder & CEO of TechWorld Inc.",
            "The cake costs $45.99, tax included.",
            "The escape key (ESC) can exit full-screen mode.",
            "A pangram uses every letter—just like this sentence!",
            "File not found. Error 404: Check the directory path.",
            "All work and no play makes Jack a dull boy.",
            "The clock struck midnight, and the spell was broken.",
            "Did you remember to lock your computer? 🔒",
            "He whispered 'abracadabra' and vanished!",
            "Note: Use hashtags like #coding, #java, and #developer.",
            "Running late? Text me at 555-0198.",
            "Pi (π) is approximately 3.14159—remember that!",
            "Passwords like 123456 or admin are not secure!"
    };

    private JLabel sentenceLabel, resultLabel;
    private JTextArea typingArea;
    private JButton startButton, retryButton;

    private String currentSentence;
    private long startTime;

    public TypingSpeedTestGUI() {
        setTitle("Typing Speed Test");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Top Panel with both sentenceLabel and resultLabel
        sentenceLabel = new JLabel("Click 'Start Test' to begin", JLabel.CENTER);
        sentenceLabel.setFont(new Font("Arial", Font.BOLD, 16));

        resultLabel = new JLabel("", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(sentenceLabel);
        topPanel.add(resultLabel);

        add(topPanel, BorderLayout.NORTH);

        // Typing area
        typingArea = new JTextArea();
        typingArea.setLineWrap(true);
        typingArea.setWrapStyleWord(true);
        typingArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        typingArea.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(typingArea);
        add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start Test");
        retryButton = new JButton("Retry");
        retryButton.setEnabled(false);
        buttonPanel.add(startButton);
        buttonPanel.add(retryButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        startButton.addActionListener(this);
        retryButton.addActionListener(this);

        // Detect Enter key press to calculate results
        typingArea.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    calculateResults();
                }
            }
        });

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private void startTest() {
        currentSentence = getRandomSentence();
        sentenceLabel.setText("<html><body style='text-align:center'>" + currentSentence + "</body></html>");
        typingArea.setText("");
        typingArea.setEnabled(true);
        typingArea.requestFocus();
        startTime = System.currentTimeMillis();
        resultLabel.setText("Type the sentence above and press Enter.");
        startButton.setEnabled(false);
        retryButton.setEnabled(true);
    }

    private void calculateResults() {
        long endTime = System.currentTimeMillis();
        String typedText = typingArea.getText().trim();
        double timeTaken = (endTime - startTime) / 1000.0;
        int wordCount = countWords(currentSentence);
        double wpm = (wordCount / timeTaken) * 60;
        double accuracy = calculateAccuracy(currentSentence, typedText);

        resultLabel.setText(String.format("Time: %.2f sec | WPM: %.2f | Accuracy: %.2f%%", timeTaken, wpm, accuracy));
        typingArea.setEnabled(false);
    }

    private String getRandomSentence() {
        Random rand = new Random();
        return SENTENCES[rand.nextInt(SENTENCES.length)];
    }

    private int countWords(String sentence) {
        return sentence.trim().split("\\s+").length;
    }

    private double calculateAccuracy(String original, String typed) {
        String[] originalWords = original.trim().split("\\s+");
        String[] typedWords = typed.trim().split("\\s+");
        int correctWords = 0;

        for (int i = 0; i < Math.min(originalWords.length, typedWords.length); i++) {
            if (originalWords[i].equals(typedWords[i])) {
                correctWords++;
            }
        }

        return (correctWords / (double) originalWords.length) * 100;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton || e.getSource() == retryButton) {
            startTest();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TypingSpeedTestGUI::new);
    }
}
