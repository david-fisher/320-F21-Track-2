
public class Interpreter {
	public GameState register;
	public Interpreter() {
		this.register = new GameState();
	}
	public Interpreter(GameState oldState) {
		this.register = oldState;
	}
	public GameState interpretExpression(Expression e) {
		if (e.type == "number") {
		} 
		else if (e.type == "boolean") {
		}
		else if (e.type == "operator") {
		}
		else if (e.type == "variable") {
		}
	}
	public GameState interpretStatement(Statement s) {
		if (s.kind == "let") {
		}
		else if (s.kind == "assign") {
		}
		else if (s.kind == "if") {
		}
		else if (s.kind == "while") {
		}
		else if (s.kind == "print") {
		}
	}
	public GameState interpretScope(Program p) {
	}
	public GameState interpretProgram(Program p) {
	}
}

