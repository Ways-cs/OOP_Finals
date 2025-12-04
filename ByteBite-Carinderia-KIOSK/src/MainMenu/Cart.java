package MainMenu;

import java.util.ArrayList;

public class Cart {
    public static class Item {
        public final String name;
        public final double price;

        public Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    private final ArrayList<Item> items = new ArrayList<>();

    public void add(String name, double price) {
        items.add(new Item(name, price));
    }

    public void remove(int index) {
        if (index >= 0 && index < items.size()) items.remove(index);
    }

    public double total() {
        return items.stream().mapToDouble(i -> i.price).sum();
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
