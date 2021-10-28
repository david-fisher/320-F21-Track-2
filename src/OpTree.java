import java.util.ArrayList;

public class OpTree {
    public final RuleType kind;
    public ArrayList<ArrayList<OpTree>> operands;
    public int value;

    public OpTree(RuleType kind, ArrayList<ArrayList<OpTree>> operands) {
        this.kind = kind;
        this.operands = operands;
    }

    public OpTree execute(GameState state) {
        return kind.execute(this, state);
    }

    public ArrayList<OpTree> getRuleGroup(int i) {
        return operands.get(i);
    }

    public OpTree getOperand(int i) {
        return operands.get(0).get(i);
    }
}