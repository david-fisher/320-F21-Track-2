public class BooleanExp extends OpTree {
    public boolean value;
    public BooleanExp(boolean value) {
        Super("boolean");
        this.value = value;
    }
}