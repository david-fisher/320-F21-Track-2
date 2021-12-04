package nodes;
public final class NodeMaker {
    private NodeMaker() {}

    public static OpNode makeNode(String type) {
        switch (type) {
            case "addition":
                return new ALNode("+");
            case "multiplication":
                return new ALNode("*");
            case "rset":
                return new RSetNode();
            case "if":
                return new IfNode();
            case "pset":
                return new PSetNode();
            case "get":
                return new GetNode();
        }

        System.out.println("Unknown rule type");
        return null;
    }
    
    public static LiteralNode<Integer> makeIntegerNode(Integer value) { return new LiteralNode<Integer>(value); }
    public static LiteralNode<Double> makeDoubleNode(Double value) { return new LiteralNode<Double>(value); }
    public static LiteralNode<String> makeStringNode(String value) { return new LiteralNode<String>(value); }
}