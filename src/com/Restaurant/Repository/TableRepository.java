
package com.Restaurant.Repository;

import com.Restaurant.Model.Table;
import java.util.ArrayList;
import java.util.List;

public class TableRepository {

    private final List<Table> tables = new ArrayList<>();

    public TableRepository() {
        tables.add(new Table(1, 2));
        tables.add(new Table(2, 4));
        tables.add(new Table(3, 4));
        tables.add(new Table(4, 6));
        tables.add(new Table(5, 8));
    }

    public List<Table> getAllTables() {
        return new ArrayList<>(tables);
    }

    public Table getTableById(int id) {
        for (Table table : tables) {
            if (table.getId() == id) {
                return table;
            }
        }

        return null;
    }
}
