public class Expression {
	public String type;
	public String name;
	public double value;
	public boolean flag;
	public Expression left;
	public Expression right;
	public Expression(int value) {
		this.type = "number";
		this.value = (double)value;
	}
	public Expression(double value) {
		this.type = "number";
		this.value = value;
	}
	public Expression(boolean flag) {
		this.type = "boolean";
		this.flag = flag;
	}
	public Expression(String op, Expression left, Expression right) {
		this.type = "operator";
		this.left = left;
		this.right = right;
	}
	public Expression(String name) {
		this.type = "variable";
		this.name = name;
	}
}
