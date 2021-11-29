package nodes;
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
            case "move":
                return new MoveNode();
            case "invoke":
                return new InvokeNode();
            default:
                System.out.println("Unknown rule type: " + type);
                return null;
        }
    }
}