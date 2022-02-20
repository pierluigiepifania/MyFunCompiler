package Node;

import Visitor.Visitor;

import java.util.List;

public class BodyOp extends Node {

    public String op;
    public List<VarDeclOp> varDeclOp;
    public List<StatementOp> stat;

    public BodyOp(String op, List<VarDeclOp> varDeclOp, List<StatementOp> stat) {
        super(op);
        this.varDeclOp = varDeclOp;
        this.stat = stat;
    }

    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
