package org.scenebuilder.scenebuilder.dummy;

public class DummyRNG {

    // super class of separate dice and spinner classes?
    private String rngID;

    public DummyRNG(String rngID) {
        this.rngID = rngID;
    }

    // setters
    public void setRNGID(String rngID) { this.rngID = rngID; }

    // getters
    public String getRngID() { return this.rngID; }
}
