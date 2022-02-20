package Node;

import Visitor.Visitor;
import java.util.List;

public class ProgramOp extends Node {

    public List<VarDeclOp> varDeclOp;
    public List<FunOp> funOp;
    public MainOp mainOp;

    public ProgramOp (String op, List<VarDeclOp> varDeclOp, List<FunOp> funOp, MainOp mainOp ) {
        super(op);
        this.varDeclOp = varDeclOp;
        this.funOp = funOp;
        this.mainOp = mainOp;
    }

    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
