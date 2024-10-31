import javax.swing.*;
import java.awt.*;

public class Status extends JPanel {
    private static JLabel missCounterLabel;
    private static JLabel strikeCounterLabel;
    private static JLabel totalMissLabel;
    private static JLabel totalHitLabel;
    private static int missCounter;
    private static int strikeCounter;
    private static int totalMiss;
    private static int totalHit;

    public Status() {
        setLayout(new GridLayout(1, 4));
        missCounterLabel = new JLabel("Miss Counter: 0");
        strikeCounterLabel = new JLabel("Strike Counter: 0");
        totalMissLabel = new JLabel("Total Miss: 0");
        totalHitLabel = new JLabel("Total Hit: 0");

        add(missCounterLabel);
        add(strikeCounterLabel);
        add(totalMissLabel);
        add(totalHitLabel);
    }

    public static void updateCounters(boolean isHit) {
        if (isHit) {
            totalHit += 1;
            missCounter = 0;

        } else {
            totalMiss += 1;
            missCounter += 1;
            if (missCounter == 5) {
                strikeCounter += 1;
                missCounter = 0;
                }

            }
        refreshDisplay();
    }

    private static void refreshDisplay() {
        missCounterLabel.setText("Miss Counter: " + missCounter);
        strikeCounterLabel.setText("Strike Counter: " + strikeCounter);
        totalMissLabel.setText("Total Miss: " + totalMiss);
        totalHitLabel.setText("Total Hit: " + totalHit);
    }

    public static int getStrikeCounter() {
        return strikeCounter;
    }

    public static void reset() {
        missCounter = 0;
        strikeCounter = 0;
        totalMiss = 0;
        totalHit = 0;
        refreshDisplay();
    }
}