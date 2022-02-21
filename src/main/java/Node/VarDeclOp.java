package Node;

import Visitor.Visitor;
import java.util.List;

public class VarDeclOp extends Node {

    public String op;
    public Integer type;
    public List <IdInitOp> idInitOp;

    public VarDeclOp (String op, int type, List <IdInitOp> idInitOp ) {
        super(op);
        this.type = type;
        this.idInitOp = idInitOp;
    }

    public VarDeclOp (String op, List <IdInitOp> idInitOp ) {
        super(op);
        this.idInitOp = idInitOp;
    }

    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
