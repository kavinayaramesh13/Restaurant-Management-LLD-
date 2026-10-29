
package com.Restaurant.Service;

import com.Restaurant.Model.MenuItem;
import com.Restaurant.Repository.MenuRepository;
import java.util.List;

public class MenuService {

    private final MenuRepository repository;

    public MenuService() {
        this(new MenuRepository());
    }

    public MenuService(MenuRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException(
                "Menu repository is required."
            );
        }

        this.repository = repository;
    }

    public List<MenuItem> getAllItems() {
        return repository.getAllItems();
    }

    public MenuItem getItemById(int id) {
        return repository.getItemById(id);
    }
}
