package MainMenu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuScreen extends JFrame {

    private CategoryPanel categoryPanel;
    private SubCategoryPanel subCategoryPanel1;
    private SubCategoryPanel subCategoryPanel2;

    // Categories for main and side dishes
    private List<CategoryPanel.Category> mainDishCategories;
    private List<CategoryPanel.Category> sideDishCategories;

    public MenuScreen() {
        setTitle("Menu System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1400, 900);
        setLocationRelativeTo(null);

        initializeCategories();
        setupUI();
    }

    private void initializeCategories() {
        mainDishCategories = new ArrayList<>();
        sideDishCategories = new ArrayList<>();

        /*change as needed*/
        // Main Dish Categories
        mainDishCategories.add(new CategoryPanel.Category("Saucy Stewed",
                createSubList("Chicken Adobo", "Beef Caldereta", "Pork Menudo", "Braised Tofu")));
        mainDishCategories.add(new CategoryPanel.Category("Soups",
                createSubList("Sinigang na Isda","Ginisang Monggo","Sinigang na Baboy","Tinolang Manok")));
        mainDishCategories.add(new CategoryPanel.Category("Vegetable",
                createSubList("Adobong Sitaw","Ginisang Ampalaya","Pinakbet","Laing")));

        // Side Dish Categories
        sideDishCategories.add(new CategoryPanel.Category("Fried",
                createSubList("French Fries","Fried Chicken","Fried Fish")));
        sideDishCategories.add(new CategoryPanel.Category("Rice",
                createSubList("Plain Rice","Garlic Rice","Special Rice")));
        sideDishCategories.add(new CategoryPanel.Category("Salads",
                createSubList("Ensalada","Korean Salad","Fruit Salad")));
    }

    private List<SubCategoryPanel.SubCategory> createSubList(String... items){
        List<SubCategoryPanel.SubCategory> list = new ArrayList<>();
        for(String name : items){

            String fileName = name.toLowerCase().replace(" ", "_") + ".png";
            list.add(new SubCategoryPanel.SubCategory(name,"₱50", fileName));
        }
        return list;
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        Color bg = new Color(245, 245, 245);
        Color categoryBg = new Color(220, 230, 220);
        Color highlight = new Color(60, 100, 70);
        Color white = Color.WHITE;

        // Main panel with left (categories) and right (subcategories)
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        mainPanel.setBackground(bg);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        // Left: Category Panel (scrollable)
        gbc.gridx = 0;
        gbc.weightx = 0.33;
        categoryPanel = new CategoryPanel(mainDishCategories, this::onCategorySelected);
        categoryPanel.setBackground(categoryBg);
        categoryPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JScrollPane catScroll = new JScrollPane(categoryPanel);
        catScroll.getVerticalScrollBar().setUnitIncrement(16);
        catScroll.setBorder(null);

        mainPanel.add(catScroll, gbc);

        // Right: SubCategory Panels
        gbc.gridx = 1;
        gbc.weightx = 0.67;
        JPanel subArea = new JPanel(new GridLayout(2,1,0,20));
        subArea.setBackground(bg);
        subArea.setBorder(BorderFactory.createEmptyBorder(10,20,10,10));

        subCategoryPanel1 = new SubCategoryPanel();
        subCategoryPanel2 = new SubCategoryPanel();
        subCategoryPanel1.setBackground(white);
        subCategoryPanel2.setBackground(white);
        subArea.add(subCategoryPanel1);
        subArea.add(subCategoryPanel2);

        JScrollPane subScroll = new JScrollPane(subArea);
        subScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        subScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(subScroll, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Buttons UI
        categoryPanel.mainDishButton.setBackground(highlight);
        categoryPanel.mainDishButton.setForeground(Color.WHITE);
        categoryPanel.mainDishButton.setFont(new Font("Poppins", Font.BOLD, 18));

        categoryPanel.sideDishButton.setBackground(highlight);
        categoryPanel.sideDishButton.setForeground(Color.WHITE);
        categoryPanel.sideDishButton.setFont(new Font("Poppins", Font.BOLD, 18));

        categoryPanel.checkoutButton.setBackground(new Color(200, 80, 80));
        categoryPanel.checkoutButton.setForeground(Color.WHITE);
        categoryPanel.checkoutButton.setFont(new Font("Poppins", Font.BOLD, 18));

        // Buttons functionality
        categoryPanel.mainDishButton.addActionListener(e -> {
            categoryPanel.setCategories(mainDishCategories);
            if(!mainDishCategories.isEmpty()) onCategorySelected(mainDishCategories.get(0));
        });

        categoryPanel.sideDishButton.addActionListener(e -> {
            categoryPanel.setCategories(sideDishCategories);
            if(!sideDishCategories.isEmpty()) onCategorySelected(sideDishCategories.get(0));
        });

        categoryPanel.checkoutButton.addActionListener(e -> JOptionPane.showMessageDialog(this,"Go to Checkout"));

        // Default selection
        if(!mainDishCategories.isEmpty()) onCategorySelected(mainDishCategories.get(0));
    }

    private void onCategorySelected(CategoryPanel.Category category){
        List<SubCategoryPanel.SubCategory> subs = category.getSubCategories();
        int half = (int)Math.ceil(subs.size()/2.0);
        subCategoryPanel1.displaySubCategories(subs.subList(0,half));
        subCategoryPanel2.displaySubCategories(subs.subList(half,subs.size()));
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new MenuScreen().setVisible(true));
    }
}
