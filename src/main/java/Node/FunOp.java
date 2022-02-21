package main.java.Node;


import main.java.Visitor.Visitor;
import main.java.Node.ExpressionOp.*;
import java.util.List;

public class FunOp extends Node {

    public String op;
    public Identifier id;
    public List<ParDeclOp> parDeclOp;
    public Integer type;
    public BodyOp bodyOp;

    public FunOp(String op, Identifier id, List<ParDeclOp> parDeclOp, int type, BodyOp bodyOp) {
        super(op);
        this.id = id;
        this.parDeclOp = parDeclOp;
        this.type = type;
        this.bodyOp = bodyOp;
    }

    public FunOp(String op, Identifier id, List<ParDeclOp> parDeclOp, BodyOp bodyOp) {
        super(op);
        this.id = id;
        this.parDeclOp = parDeclOp;
        this.bodyOp = bodyOp;
    }

    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
