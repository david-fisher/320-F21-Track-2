import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        GameState state = new GameState();
        LiteralNode exp1 = new LiteralNode<Integer>(5);
        LiteralNode exp2 = new LiteralNode<Double>(2.2);
        ALNode st1 = new ALNode("+");
        ALNode st2 = new ALNode("*");
        st1.getRuleGroup(0).add(exp1);
        st1.getRuleGroup(0).add(exp2);
        st2.getRuleGroup(0).add(exp2);
        st2.getRuleGroup(0).add(st1);
        interpreter.interpretRule(st2, state);
    }
}