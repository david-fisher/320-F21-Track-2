package org.GameObjects.objects;

import javafx.scene.shape.Shape;

public class Button extends GameObject {
    private static int count = 0;
	private static Shape parent;

	public Button() {
		super() ;  
		this.setLabel("button" + String.format("%02d", ++count));
		this.setIcon("default_gamepiece_icon.jpg");
		this.setColorString("#FFFFFF");
		this.setText("type text here");
		this.setEnabled(true);
		this.setPressed(false);
	}
	
	/* Trait Types:
     * 	label 	: 	String
     * 	icon 	: 	String
     * 	color 	:	String (Can be obtained as JAVAFX Color object)
     *  shape   :   String (one of "square", 
     *  xPos    :   Integer
     *  yPos    :   Integer
     *  text    :   String
     *  enabled :   Boolean
     *  pressed :   Boolean
     */

	//set trait to value. Overrides checking for default traits only.
	public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {
		if (super.setTrait(trait, value, suppressTraitChecker)) {
			return true;
		} else if (suppressTraitChecker || 
				(trait.equals("text") && value instanceof String) || // check text is String
				(trait.equals("pressed") && value instanceof Boolean) ||
				(trait.equals("enabled") && value instanceof Boolean)) { 
			traits.put(trait, value);
			return true ;
		}
	  
		// returns false if input is invalid
		return false ;
	}
	  
	public boolean toggleEnabled() {
		setEnabled(!getEnabled());
		return getEnabled();
	}
	
	public boolean togglePressed() {
		setPressed(!getPressed());
		return getPressed();
	}

	public boolean setText(String text) {
		return this.setTrait("text", text);
	}

	public String getText() {
		return (String)this.getTrait("text");
	}
	
	public boolean setEnabled(boolean enabled) {
		return this.setTrait("enabled", enabled);
	}

	public boolean getEnabled() {
		return (boolean)this.getTrait("enabled");
	}
	
	public boolean setPressed(boolean pressed) {
		return this.setTrait("pressed", pressed);
	}

	public boolean getPressed() {
		return (boolean)this.getTrait("pressed");
	}

	public void setParent(Shape parent) { this.parent = parent; }

	public Shape getParent() { return parent; }

	public String repr(boolean hasLabel) {
		return "Button\n" + super.repr(hasLabel);
	}
}