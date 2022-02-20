package Node;

import Visitor.Visitor;
import Node.ExpressionOp.Identifier;

public class IdInitOp extends Node {

    public String op;
    public Identifier id;
    public ExpressionOp e;

    public IdInitOp(String op, Identifier id) {
        super(op);
        this.id = id;
    }

    public IdInitOp(String op, Identifier id, ExpressionOp e) {
        super(op);
        this.id = id;
        this.e = e;
    }

    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
