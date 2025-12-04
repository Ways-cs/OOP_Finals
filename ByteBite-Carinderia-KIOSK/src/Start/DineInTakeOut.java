package Start;

import javax.swing.*;
import java.awt.*;
import MainMenu.MenuScreen;

public class DineInTakeOut extends JFrame {
    private Font gagaFont;

    public DineInTakeOut() {
        setTitle("Select Order Type");
        setSize(400, 700); // Portrait
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load Gagalin font
        try {
            gagaFont = Font.createFont(Font.TRUETYPE_FONT,
                            new java.io.File("src/Start/Fonts/gagalin.otf"))
                    .deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(gagaFont);
        } catch (Exception e) {
            e.printStackTrace();
            gagaFont = new Font("Serif", Font.PLAIN, 24);
        }


        // Background image
        ImageIcon bg = new ImageIcon("src/Start/DineinTakeout.png");
        Image scaled = bg.getImage().getScaledInstance(400, 700, Image.SCALE_SMOOTH);
        bg = new ImageIcon(scaled);

        JLabel background = new JLabel(bg);
        background.setLayout(new BorderLayout());
        add(background, BorderLayout.CENTER);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        background.add(centerWrapper, BorderLayout.CENTER);

        // FIXED ROW for 2 options
        JPanel optionRow = new JPanel(new GridLayout(1, 2, 20, 0));
        optionRow.setOpaque(false);

        JPanel dineOption = createOption("src/Start/DineIn.png", "DINE IN");
        JPanel takeOption = createOption("src/Start/TakeOut.png", "TAKE OUT");

        optionRow.add(dineOption);
        optionRow.add(takeOption);

        centerWrapper.add(optionRow);

        setVisible(true);
    }

    private JPanel createOption(String imgPath, String buttonText) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        int boxWidth = 150;
        int boxHeight = 200;
        int iconWidth = 130;
        int iconHeight = 130;

        ImageIcon icon = new ImageIcon(imgPath);
        Image scaled = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaled);

        // Rounded container
        JPanel box = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(230, 230, 230));  // light gray
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };

        box.setOpaque(false);
        box.setPreferredSize(new Dimension(boxWidth, boxHeight));
        box.setMaximumSize(new Dimension(boxWidth, boxHeight));
        box.setLayout(new GridBagLayout());

        JLabel imgLabel = new JLabel(icon);
        box.add(imgLabel);

        JButton btn = new JButton(buttonText);
        btn.setContentAreaFilled(false);   // no bg
        btn.setBorderPainted(false);
        btn.setForeground(new Color(0, 128, 0));
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setPreferredSize(new Dimension(150, 45));
        btn.setMaximumSize(new Dimension(150, 45));

        btn.setFont(gagaFont);

        btn.addActionListener(e -> openMenu());

        panel.add(box);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btn);

        return panel;
    }


    private void openMenu() {
        dispose();
        SwingUtilities.invokeLater(() -> new MenuScreen().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DineInTakeOut::new);
    }
}
