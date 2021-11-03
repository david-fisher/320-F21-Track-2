import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        GameState state = new GameState();
        LiteralNode exp1 = new LiteralNode<String>("cat");
        LiteralNode exp2 = new LiteralNode<String>("dog");
        BinaryNode st1 = new BinaryNode("+");
        BinaryNode st2 = new BinaryNode("+");
        st1.getRuleGroup(0).add(exp1);
        st1.getRuleGroup(0).add(exp2);
        st2.getRuleGroup(0).add(exp2);
        st2.getRuleGroup(0).add(st1);
        interpreter.interpretRule(st2, state);
    }
}