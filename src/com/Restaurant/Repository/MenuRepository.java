
package com.Restaurant.Repository;

import com.Restaurant.Model.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private final List<MenuItem> items = new ArrayList<>();

    public MenuRepository() {
        items.add(new MenuItem(1, "Idli", 40.00));
        items.add(new MenuItem(2, "Dosa", 70.00));
        items.add(new MenuItem(3, "Veg Fried Rice", 120.00));
        items.add(new MenuItem(4, "Paneer Pizza", 180.00));
        items.add(new MenuItem(5, "Fresh Juice", 60.00));
    }

    public List<MenuItem> getAllItems() {
        return new ArrayList<>(items);
    }

    public MenuItem getItemById(int id) {
        for (MenuItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }
}
