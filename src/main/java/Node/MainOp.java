package main.java.Node;


import main.java.Visitor.Visitor;

public class MainOp extends Node {

    public String op;
    public BodyOp body;

    public MainOp(String op, BodyOp body) {
        super(op);
        this.body = body;
    }

    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
