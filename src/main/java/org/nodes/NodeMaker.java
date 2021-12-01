package org.nodes;
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
        }

        System.out.println("Unknown rule type");
        return null;
    }
}