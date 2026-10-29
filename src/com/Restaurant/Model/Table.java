
package com.Restaurant.Model;

public class Table {

    public enum TableStatus {
        AVAILABLE,
        OCCUPIED
    }

    private int id;
    private int capacity;
    private TableStatus status;

    public Table(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.status = TableStatus.AVAILABLE;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void occupyTable() {
        status = TableStatus.OCCUPIED;
    }

    public void freeTable() {
        status = TableStatus.AVAILABLE;
    }

    public void displayTable() {
        System.out.println(
            "Table ID: " + id
            + " | Capacity: " + capacity
            + " | Status: " + status
        );
    }
}
