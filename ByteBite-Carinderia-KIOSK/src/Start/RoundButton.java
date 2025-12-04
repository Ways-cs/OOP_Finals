package Start;

import javax.swing.*;
import java.awt.*;

public class RoundButton extends JButton {
    private int radius = 20;

    public RoundButton(String text) {
        super(text);
        setFocusPainted(false);     // remove the outline when clicked
        setContentAreaFilled(false); // make background transparent
        setOpaque(false);            // make button fully see-through
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
    }
}
