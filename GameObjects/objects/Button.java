package objects;
import java.awt.Color;

public class Button extends GameObject {
    private static int count = 0;

// TODO: needs width and height and position info?
	private String text;
	private boolean enabled;
	private boolean pressed;

	public Button() {
		super() ;  
		this.setLabel("button" + String.format("%02d", ++count));
		this.setIcon("default_gamepiece_icon.jpg");
		this.setColor(Color.WHITE);
		setTrait("text", "text");
		setTrait("enabled", true);
		setTrait("pressed", false);
	}

	//set trait to value. Overrides checking for default traits only.
	public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {
		if (super.setTrait(trait, value, suppressTraitChecker)) {
			return true;
		} else if (suppressTraitChecker || this.getTrait(trait).getClass().equals(value.getClass())) {
			traits.put(trait, value);
			return true ;
		}
	  
		// returns false if input is invalid
		return false ;
	}
  
	public String toString() {
		return this.getLabel() ;
	}
}