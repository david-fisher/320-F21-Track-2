package org.Editors.blocks;

public final class GenBlockMaker {
    private GenBlockMaker() {}

    public static Block makeBlock(String type) {
        Block newBlock = new Block();
        switch (type) {
            case "rset":
                newBlock.createGenBlock("rset", new String[] {"Reg:", "Obj:"});
                newBlock.createNode("rset");
                return newBlock;
            case "pset":
                newBlock.createGenBlock("pset", new String[] {"Prop:", "Obj:", "Val:"});
                newBlock.createNode("pset");
                return newBlock;
            case "get":
                newBlock.createGenBlock("get", new String[] {"Prop:", "Obj:"});
                newBlock.createNode("get");
                return newBlock;
            case "invoke":
                newBlock.createGenBlock("invoke", new String[] {"Event:"});
                newBlock.createNode("invoke");
                return newBlock;
            case "moveBy":
                newBlock.createGenBlock("move by steps", new String[] {"Obj:", "Steps:"});
                newBlock.createNode("moveBy");
                return newBlock;
            case "moveTo":
                newBlock.createGenBlock("move to tile", new String[] {"Obj:", "Tile:"});
                newBlock.createNode("moveTo");
                return newBlock;
            case "nextPlayer":
                newBlock.createGenBlock("get next player", new String[] {});
                newBlock.createNode("nextPlayer");
                return newBlock;
            case "tile":
                newBlock.createGenBlock("get tile[i]", new String[] {"i:"});
                newBlock.createNode("tile");
                return newBlock;
            case "use":
                newBlock.createGenBlock("use die/spinner", new String[] {"die/spnr:"});
                newBlock.createNode("use");
                return newBlock;
            case "draw":
                newBlock.createGenBlock("deck draw", new String[] {"Plcmnt:", "Deck:", "Player:"});
                newBlock.createNode("draw");
                return newBlock;
            case "put":
                newBlock.createGenBlock("deck put", new String[] {"Card:", "Plcmnt:", "Deck:"});
                newBlock.createNode("put");
                return newBlock;
            case "shuffle":
                newBlock.createGenBlock("deck shuffle", new String[] {"Deck:"});
                newBlock.createNode("shuffle");
                return newBlock;
            case "display":
                newBlock.createGenBlock("display", new String[] {"String:"});
                newBlock.createNode("display");
                return newBlock;
            case "putInInventory":
                newBlock.createGenBlock("put inventory", new String[] {"Obj:", "Player:"});
                newBlock.createNode("putInInventory");
                return newBlock;
            case "getLastUsed":
                newBlock.createGenBlock("get last used", new String[] {"Player:"});
                newBlock.createNode("getLastUsed");
                return newBlock;
            case "getGamepiece":
                newBlock.createGenBlock("get gamepiece", new String[] {"Player:"});
                newBlock.createNode("getGamepiece");
                return newBlock;
            case "negate":
                newBlock.createGenBlock("negate", new String[] {"Value:"});
                newBlock.createNode("negate");
                return newBlock;
            case "player":
                newBlock.createGenBlock("get player[i]", new String[] {"i:"});
                newBlock.createNode("player");
                return newBlock;
        }

        System.out.println("Unknown block type");
        return null;
    }
}
