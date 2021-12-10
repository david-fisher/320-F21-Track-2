package org.RuleEngine.nodes;
public final class NodeMaker {
    private NodeMaker() {}

    public static OpNode makeNode(String type) {
        switch (type) {
            case "AL":
                return new ALNode("+");
            case "rset":
                return new RSetNode();
            case "pset":
                return new PSetNode();
            case "get":
                return new GetNode();
            case "if":
                return new IfNode();
            case "while":
                return new WhileNode();
            case "tile":
                return new TileNode();
            case "moveBy":
                return new MoveByNode();
            case "moveTo":
                return new MoveToNode();
            case "nextPlayer":
                return new NextPlayerNode();
            case "invoke":
                return new InvokeNode();
            case "display":
                return new DisplayNode();
            case "draw":
                return new DrawNode();
            case "put":
                return new PutNode();
            case "shuffle":
                return new ShuffleNode();
            case "use":
                return new UseNode();
        }

        System.out.println("Unknown rule type");
        return null;
    }
    
    public static LiteralNode<Integer> makeIntegerNode(Integer value) { return new LiteralNode<Integer>(value); }
    public static LiteralNode<Double> makeDoubleNode(Double value) { return new LiteralNode<Double>(value); }
    public static LiteralNode<String> makeStringNode(String value) { return new LiteralNode<String>(value); }
}