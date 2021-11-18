package org.scenebuilder.scenebuilder;

import org.objects.GameObject;

import java.util.ArrayList;

public class DummyInventory {

    private String inventoryID;
    private ArrayList<GameObject> inventory;

    // cards?
    // money?
    // points?
    // pieces?

    public DummyInventory(String inventoryID, ArrayList<GameObject> inventory) {

        this.inventoryID = inventoryID;
        this.inventory = inventory;
    }

    public void setInventory(ArrayList<GameObject> inventory) {
        this.inventory = inventory;
    }
    public ArrayList<GameObject> getInventory() { return this.inventory; }
    // setters
    public void setInventoryID(String inventoryID) { this.inventoryID = inventoryID; }

    // getters
    public String getInventoryID() { return this.inventoryID; }
}
