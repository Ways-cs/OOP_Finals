package Main;

import Start.StartScreen;
import javax.swing.SwingUtilities;

public class MainPOS {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StartScreen::new);
    }
}
