package Start;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JFrame {

    public StartScreen() {
        setTitle("Kiosk Start");
        setSize(400, 700); // Portrait
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Center full-screen image placeholder
        ImageIcon layoutImg = new ImageIcon("src/Start/ByteBites.png");
        Image scaledImage = layoutImg.getImage().getScaledInstance(400, 560, Image.SCALE_SMOOTH);
        layoutImg = new ImageIcon(scaledImage);

        JLabel imgPlaceholder =  new JLabel(layoutImg);
        imgPlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
        imgPlaceholder.setVerticalAlignment(SwingConstants.CENTER);
        add(imgPlaceholder, BorderLayout.CENTER);

        // Bottom full-width button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        bottomPanel.setBackground(Color.WHITE);

        RoundButton startBtn = new RoundButton("Touch To Start");
        startBtn.setBackground(new Color(240,240,240));
        startBtn.setPreferredSize(new Dimension(getWidth(), 80));

        startBtn.setOpaque(false);
        startBtn.setFocusable(false);
        startBtn.setBorderPainted(false);
        startBtn.setContentAreaFilled(false);

        startBtn.addActionListener(e -> {
            dispose();
            new DineInTakeOut();
        });

        bottomPanel.add(startBtn, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
