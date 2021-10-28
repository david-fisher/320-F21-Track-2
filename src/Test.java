import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        GameState state = new GameState();
        IntExp exp1 = new IntExp(1);
        IntExp exp2 = new IntExp(2);
        Rule add = new Rule(RuleType.ADD, new ArrayList<ArrayList<OpTree>>());
        add.operands.add(new ArrayList<OpTree>());
        add.operands.get(0).add(exp1);
        add.operands.get(0).add(exp2);
        interpreter.interpretRule(add, state);
    }
//
}