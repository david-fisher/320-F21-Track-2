package engine;
import java.util.Map;

// Placeholder for GameObject
public class GameObject {
    public String label;
    public Map<String, Object> traits;
    public GameObject(String label) {
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
    public void setLabel(String newLabel){
        this.label = newLabel;
        return;
    }
    public boolean setTrait(String trait, Object value) {
        this.traits.put(trait, value);
        return true;
    }
    public boolean setTrait(String trait, Object value, boolean suppressTraitChecker) {
        this.traits.put(trait, value);
        return true;
    }
    public Object getTrait(String trait){
        return this.traits.get(trait);
    }
    public String repr(boolean hasLabel){
        return "";
    } 
}