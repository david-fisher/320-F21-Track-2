package org.scenebuilder.scenebuilder;

public class DummyRNG {

    private String rngID;

    public DummyRNG(String rngID) {
        this.rngID = rngID;
    }

    // setters
    public void setRNGID(String rngID) { this.rngID = rngID; }

    // getters
    public String getRngID() { return this.rngID; }
}
