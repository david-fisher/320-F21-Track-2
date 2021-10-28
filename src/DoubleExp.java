public class DoubleExp extends OpTree {
    public double value;
    public DoubleExp(double value) {
        Super("double");
        this.value = value;
    }
}