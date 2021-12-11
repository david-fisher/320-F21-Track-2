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
            case "moveby":
                newBlock.createGenBlock("move by steps", new String[] {"Obj:", "Steps:"});
                newBlock.createNode("moveby");
                return newBlock;
            // case "use":
            //     return new UseNode();
        }

        System.out.println("Unknown block type");
        return null;
    }
}
