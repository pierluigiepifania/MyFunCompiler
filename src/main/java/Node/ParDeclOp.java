package Node;

import Node.ExpressionOp.*;
import Visitor.Visitor;

public class ParDeclOp extends Node {

    public String op;
    public int inOut;
    public Integer type;
    public Identifier id;

    public ParDeclOp(String op, int inOut, int type, Identifier id) {
        super(op);
        this.inOut = inOut;
        this.type = type;
        this.id = id;
    }

    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
