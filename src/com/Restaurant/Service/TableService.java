
package com.Restaurant.Service;

import com.Restaurant.Model.Table;
import com.Restaurant.Repository.TableRepository;
import java.util.List;

public class TableService {

    private final TableRepository repository;

    public TableService() {
        this(new TableRepository());
    }

    public TableService(TableRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException(
                "Table repository is required."
            );
        }

        this.repository = repository;
    }

    public List<Table> getAllTables() {
        return repository.getAllTables();
    }

    public Table getTableById(int id) {
        return repository.getTableById(id);
    }

    public boolean occupyTable(int id) {
        Table table = repository.getTableById(id);

        if (table == null
                || table.getStatus() != Table.TableStatus.AVAILABLE) {
            return false;
        }

        table.occupyTable();
        return true;
    }

    public boolean freeTable(int id) {
        Table table = repository.getTableById(id);

        if (table == null
                || table.getStatus() != Table.TableStatus.OCCUPIED) {
            return false;
        }

        table.freeTable();
        return true;
    }
}
