package org.scenebuilder.scenebuilder;

public class DummyInventory {

    private String inventoryID;

    public DummyInventory(String inventoryID) {
        this.inventoryID = inventoryID;
    }

    // setters
    public void setInventoryID(String inventoryID) { this.inventoryID = inventoryID; }

    // getters
    public String getInventoryID() { return this.inventoryID; }
}
