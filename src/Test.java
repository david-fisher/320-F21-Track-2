import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        GameState state = new GameState();
        Expression exp1 = new Expression<Double>(1.1);
        Expression exp2 = new Expression<Integer>(5);
        Statement add = new BinaryStatement("+", new ArrayList<ArrayList<OpTree>>());
        add.operands.add(new ArrayList<OpTree>());
        add.getRuleGroup(0).add(exp1);
        add.getRuleGroup(0).add(exp2);
        interpreter.interpretRule(add, state);
    }
}