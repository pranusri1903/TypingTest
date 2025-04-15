import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TypingSpeedTestGUI extends JFrame implements ActionListener {

    private static final String[] SENTENCES = {
        "The quick brown fox jumps over the lazy dog.",
        "Java is a high-level programming language.",
        "Typing speed is measured in words per minute.",
        "Practice makes a person perfect in typing.",
        "Accurate typing is better than fast typing."
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

        sentenceLabel = new JLabel("Click 'Start Test' to begin", JLabel.CENTER);
        sentenceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(sentenceLabel, BorderLayout.NORTH);

        typingArea = new JTextArea();
        typingArea.setLineWrap(true);
        typingArea.setWrapStyleWord(true);
        typingArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        typingArea.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(typingArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Start Test");
        retryButton = new JButton("Retry");
        retryButton.setEnabled(false);
        buttonPanel.add(startButton);
        buttonPanel.add(retryButton);
        add(buttonPanel, BorderLayout.SOUTH);

        resultLabel = new JLabel("", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(resultLabel, BorderLayout.NORTH);

        startButton.addActionListener(this);
        retryButton.addActionListener(this);

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