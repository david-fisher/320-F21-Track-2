//package org.objects;
//
//import java.awt.Color;
//import java.util.*;
///**
// * Write a description of class Tile here.
// *
// * @author (your name)
// * @version (a version number or a date)
// */
//public class Tile extends GameObject
//{
//    // instance variables - replace the example below with your own
//	private ArrayList<Tile> connections;
//    private static int count = 0;
//
//    /**
//     * Constructor for objects of class Tile
//     */
//    public Tile()
//    {
//    	super() ;
//
//        connections = new ArrayList<Tile>() ;
//
//    	this.setLabel("tile" + String.format("%02d", ++count));
//        this.setIcon("default_tile_icon.jpg") ;
//        this.setColor(Color.BLACK) ;
//        this.setTrait("connections", connections, true) ;
//    }
//
//    public List<Tile> getConnect()
//    {
//        return connections;
//    }
//
//    public boolean addConnect(Tile tile) {
//        return connections.add(tile);
//    }
//
//    public boolean deleteConnect(Tile tile) {
//    	return connections.remove(tile);
//    }
//
//    public List<Gamepiece> getGamePieces() {
//    	// TODO
//    	return new ArrayList<Gamepiece>() ;
//    }
//
//    public String toString() {
//    	return this.getLabel();
//    }
//}
