package MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CategoryPanel extends JPanel {

    private CategoryClickListener listener;
    private JPanel categoryListPanel;

    // Buttons
    public JButton mainDishButton;
    public JButton sideDishButton;
    public JButton checkoutButton;

    public CategoryPanel(List<Category> categories, CategoryClickListener listener) {
        this.listener = listener;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        setBackground(new Color(240,240,240));

        // Top buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(1,3,15,0));
        buttonPanel.setBackground(new Color(240,240,240));

        mainDishButton = new JButton("Main Dish");
        sideDishButton = new JButton("Side Dish");
        checkoutButton = new JButton("Checkout");

        styleButton(mainDishButton);
        styleButton(sideDishButton);
        styleButton(checkoutButton);

        buttonPanel.add(mainDishButton);
        buttonPanel.add(sideDishButton);
        buttonPanel.add(checkoutButton);

        add(buttonPanel, BorderLayout.NORTH);

        // Category list
        categoryListPanel = new JPanel();
        categoryListPanel.setLayout(new GridLayout(0,1,15,15));
        categoryListPanel.setBackground(new Color(240,240,240));
        JScrollPane scrollPane = new JScrollPane(categoryListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);

        setCategories(categories);
    }

    public void setCategories(List<Category> categories){
        categoryListPanel.removeAll();
        for(Category category : categories){
            categoryListPanel.add(createCategoryBox(category));
        }
        categoryListPanel.revalidate();
        categoryListPanel.repaint();
    }

    private void styleButton(JButton btn){
        btn.setFocusPainted(false);
        btn.setBackground(new Color(180,180,180));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(120,40));
    }

    private JPanel createCategoryBox(Category category){
        JPanel box = new JPanel(new BorderLayout());
        box.setPreferredSize(new Dimension(150,150)); // square-ish
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
        box.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel nameLabel = new JLabel("<html><center>" + category.getName() + "</center></html>", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD,16));

        JLabel countLabel = new JLabel(category.getSubCategories().size() + " items", SwingConstants.CENTER);
        countLabel.setFont(new Font("Arial", Font.PLAIN,14));
        countLabel.setForeground(Color.GRAY);

        box.add(nameLabel, BorderLayout.CENTER);
        box.add(countLabel, BorderLayout.SOUTH);

        box.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(listener != null) listener.onCategoryClicked(category);
            }
        });

        return box;
    }

    public interface CategoryClickListener {
        void onCategoryClicked(Category category);
    }

    public static class Category {
        private String name;
        private List<SubCategoryPanel.SubCategory> subCategories;

        public Category(String name, List<SubCategoryPanel.SubCategory> subCategories){
            this.name = name;
            this.subCategories = subCategories;
        }

        public String getName(){ return name; }
        public List<SubCategoryPanel.SubCategory> getSubCategories(){ return subCategories; }
    }
}

