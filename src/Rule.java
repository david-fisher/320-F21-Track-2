import java.util.ArrayList;

public class Rule extends OpTree {

    public Rule(RuleType kind, ArrayList<ArrayList<OpTree>> operands) {
        super(kind, operands);
    }
}