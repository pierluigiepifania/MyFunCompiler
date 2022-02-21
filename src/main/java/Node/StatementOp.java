package main.java.Node;
import main.java.Node.ExpressionOp.*;
import main.java.Visitor.Visitable;
import main.java.Visitor.Visitor;

import java.util.List;

public abstract class StatementOp extends Node implements Visitable {

    public StatementOp(String op) {
        super(op);
    }

    public static class ReadOp extends StatementOp {

        public String op;
        public List<Identifier> idlist;
        public ExpressionOp exOp;

        public ReadOp(String op, List<Identifier> idlist, ExpressionOp exOp) {
            super(op);
            this.idlist = idlist;
            this.exOp = exOp;
        }

        public ReadOp(String op, List<Identifier> idlist) {
            super(op);
            this.idlist = idlist;
            this.exOp = exOp;
        }

        public Object accept(Visitor v) throws Exception {
            return v.visit(this);
        }

    }

    public static class WriteOp extends StatementOp {

        public String op;
        public int typeWrite;
        public ExpressionOp exOp;

        public WriteOp(String op, int typeWrite, ExpressionOp exOp) {
            super(op);
            this.typeWrite = typeWrite;
            this.exOp = exOp;
        }

        public Object accept(Visitor v) throws Exception {
            return v.visit(this);
        }

    }

    public static class AssignOp extends StatementOp {

        public String op;
        public Identifier id;
        public ExpressionOp exOp;

        public AssignOp(String op, Identifier id, ExpressionOp exOp) {
            super(op);
            this.id = id;
            this.exOp = exOp;
        }

        public Object accept(Visitor v) throws Exception {
            return v.visit(this);
        }

    }

    public static class WhileOp extends StatementOp {

        public String op;
        public ExpressionOp exOp;
        public BodyOp bodyOp;

        public WhileOp(String op, ExpressionOp exOp, BodyOp bodyOp) {
            super(op);
            this.exOp = exOp;
            this.bodyOp = bodyOp;
        }

        public Object accept(Visitor v) throws Exception {
            return v.visit(this);
        }

    }

    public static class IfStatOp extends StatementOp {

        public String op;
        public ExpressionOp exOp;
        public BodyOp bodyOp;
        public ElseStatOp el;

        public IfStatOp(String op, ExpressionOp exOp, BodyOp bodyOp, ElseStatOp el) {
            super(op);
            this.exOp = exOp;
            this.bodyOp = bodyOp;
            this.el = el;
        }

        public Object accept(Visitor v) throws Exception {
            return v.visit(this);
        }
    }

    public static class ElseStatOp extends StatementOp {

        public String op;
        public BodyOp bodyOp;

        public ElseStatOp(String op, BodyOp bodyOp) {
            super(op);
            this.bodyOp = bodyOp;

        }

        public Object accept(Visitor v) throws Exception {
            return v.visit(this);
        }
    }

    public static class ReturnOp extends StatementOp {

        public ExpressionOp exOp;
        public String op;

        public ReturnOp(String op, ExpressionOp exOp) {
            super(op);
            this.exOp = exOp;
        }

        public Object accept(Visitor v) throws Exception {
            return v.visit(this);
        }
    }

    public static class CallFunOp extends StatementOp {

        public String op;
        public Identifier id;
        public List<ExpressionOp> exOpList;

        public CallFunOp(String op, Identifier id, List<ExpressionOp> exOpList) {
            super(op);
            this.id = id;
            this.exOpList = exOpList;
        }

        public CallFunOp(String op, Identifier id) {
            super(op);
            this.id = id;
        }

        public Object accept(Visitor v) throws Exception {
            return v.visit(this);
        }
    }

}
