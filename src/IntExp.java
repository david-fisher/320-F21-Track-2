public class IntExp extends OpTree {
    public IntExp(int value) {
        super(RuleType.INTEGER, null);
        super.value = value;
    }
}