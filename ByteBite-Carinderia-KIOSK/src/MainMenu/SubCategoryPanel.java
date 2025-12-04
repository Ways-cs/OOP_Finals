package MainMenu;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SubCategoryPanel extends JPanel {

    private JPanel contentPanel;

    public SubCategoryPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(245, 245, 245));

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 2, 15, 15)); // 2 columns per row
        contentPanel.setBackground(new Color(245, 245, 245));

        // No internal scroll pane - scrolling handled by parent
        add(contentPanel, BorderLayout.CENTER);
    }

    public void displaySubCategories(List<SubCategory> subs) {
        contentPanel.removeAll();

        for (SubCategory sub : subs) {
            contentPanel.add(createSubCategoryBox(sub));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel createSubCategoryBox(SubCategory sub) {
        JPanel box = new JPanel();
        box.setLayout(new BorderLayout());

        // Square-ish box
        box.setPreferredSize(new Dimension(350, 350));
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        //Image
        JLabel imgLabel;
        try {
            ImageIcon img = new ImageIcon("src/MainMenu/images/" + sub.getImg());
            Image scaleImg = img.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
            imgLabel = new JLabel(new ImageIcon(scaleImg));
        } catch (Exception e) {
            imgLabel = new JLabel("No Image");
        }
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Name label
        JLabel nameLabel = new JLabel("<html><center>" + sub.getName() + "</center></html>", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Price label
        JLabel priceLabel = new JLabel(sub.getPrice(), SwingConstants.CENTER);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        priceLabel.setForeground(new Color(0, 100, 0));

        // Add/Remove buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton addButton = createButton("Add to Cart", new Color(76, 175, 80));
        JButton removeButton = createButton("Remove", new Color(244, 67, 54));

        addButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Added " + sub.getName() + " to cart!");
        });

        removeButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Removed " + sub.getName() + " from cart!");
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        // Add all components
        box.add(imgLabel, BorderLayout.NORTH);
        box.add(nameLabel, BorderLayout.CENTER);
        box.add(priceLabel, BorderLayout.SOUTH);
        box.add(buttonPanel, BorderLayout.PAGE_END);

        return box;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        return button;
    }

    public static class SubCategory {
        private String name;
        private String price;
        private String img;

        public SubCategory(String name, String price, String img) {
            this.name = name;
            this.price = price;
            this.img = img;
        }

        public String getName() { return name; }
        public String getPrice() { return price; }
        public String getImg() { return img; }
    }
}
