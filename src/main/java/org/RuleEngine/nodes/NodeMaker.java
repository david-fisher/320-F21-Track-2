package org.RuleEngine.nodes;
public final class NodeMaker {
    private NodeMaker() {}

    public static OpNode makeNode(String type) {
        switch (type) {
            case "AL":
                return new ALNode();
            case "rset":
                return new RSetNode();
            case "pset":
                return new PSetNode();
            case "get":
                return new GetNode();
            case "negate":
                return new NegateNode();
            case "if":
                return new IfNode();
            case "while":
                return new WhileNode();
            case "tile":
                return new TileNode();
            case "moveTo":
                return new MoveToNode();
            case "moveBy":
                return new MoveByNode();
            case "player":
                return new PlayerNode();
            case "nextPlayer":
                return new NextPlayerNode();
            case "invoke":
                return new InvokeNode();
            case "getGamepiece":
                return new GetGamepieceNode();
            case "putInInventory":
                return new PutInInventoryNode();
            case "getLastUsed":
                return new GetLastUsedNode();
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
