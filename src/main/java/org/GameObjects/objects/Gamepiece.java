package org.GameObjects.objects;

import org.GamePlay.Display;
import org.RuleEngine.engine.GameState;
import org.RuleEngine.engine.Interpreter;

public class Gamepiece extends GameObject{

	private static int count = 0;

	public Gamepiece() {
		super() ;
		this.setLabel("gamepiece" + String.format("%02d", ++count));
		this.setIcon("default_gamepiece_icon.jpg") ;
		this.setColorString("#000000") ;
		this.setLocation(null, null);
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
                Display.getDisplay().updatePiece(this);
                return tile.addGamepiece(this);
            }
            return true ;
        }

        return false ;
    }
    
    // This setLocation overload method triggers the onLand event of the tile.
    // This is primarily used by the rules.
	public boolean setLocation(Tile tile, GameState currState) {
		// first clear current location
		if (this.getLocation() != null) {
			this.getLocation().removeGamepiece(this) ;
		}

		// set location
		if (this.setTrait("location", tile)) {
			if (!tile.hasGamepiece(this)) {
				Display.getDisplay().updatePiece(this);
                Interpreter.getInstance().interpretEvent(currState.getEvent("onLand"), currState);
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