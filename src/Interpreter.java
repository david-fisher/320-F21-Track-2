import java.util.ArrayList;

public class Interpreter {
    public void interpretRule(OpTree rule, GameState currState) {
        OpTree test = rule.execute(currState);
		System.out.println(test.value);
    }

    public void interpretEvent(ArrayList<OpTree> event, GameState currState) {
        for(int i = 0; i < event.size(); i++) {
            interpretRule(event.get(0), currState);
        }
    }
}