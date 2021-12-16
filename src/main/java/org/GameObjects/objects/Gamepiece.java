package org.GameObjects.objects;

import org.GamePlay.Display;

public class Gamepiece extends GameObject{

	private static int count = 0;

	public Gamepiece() {
		super() ;
		this.setLabel("gamepiece" + String.format("%02d", ++count));
		this.setIcon("default_gamepiece_icon.jpg") ;
		this.setColorString("#000000") ;
		this.setLocation(null);
	}

	/* Trait Types:
	 * 	label 	: 	String
	 * 	icon 	: 	String
	 * 	color 	:	String (Can be obtained as JAVAFX Color object)
	 * 	location:	Tile
	 */


	// set trait to value. Overrides checking for default traits only
	public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {

		// run game object's set trait first
		if (super.setTrait(trait, value, suppressTraitChecker)) {
			return true ;
		}

		// checks for other valid inputs
		else if (suppressTraitChecker ||	// if true don't check trait type
				(trait.equals("location") && value instanceof Tile)) {	// check value is String
			prevTraits.put(trait, traits.get(trait)) ;
			traits.put(trait, value) ;
			return true ;
		}

		// returns false if input is invalid
		return false ;
	}

	public boolean setLocation(Tile tile) {

		// first clear current location
		if (this.getLocation() != null) {
			this.getLocation().removeGamepiece(this) ;
		}

		// set location
		if (this.setTrait("location", tile)) {
			if (!tile.hasGamepiece(this)) {
				if (this.getParent() != null) {
					Display.getDisplay().updatePiece(this);
				}
				return tile.addGamepiece(this);
			}
			return true ;
		}

		return false ;
	}

	public Tile getLocation() {
		return (Tile)this.getTrait("location");
	}

	public String repr(boolean hasLabel) {
		return "Gamepiece\n" + super.repr(hasLabel);
	}
}
