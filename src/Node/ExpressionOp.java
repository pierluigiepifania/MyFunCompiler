package Node;

import Visitor.*;
import Node.StatementOp.*;

public abstract class ExpressionOp extends Node implements Visitable {

    public ExpressionOp(String op) {
        super(op);
    }

    public static class Identifier extends ExpressionOp {

        public String value;
        public ExpressionOp e;

        public Identifier(String op, String value) {
            super(op);
            this.value = value;
        }

        public Identifier(String op, String value, ExpressionOp e) {
            super(op);
            this.value = value;
            this.e = e;
        }

        @Override
        public Object accept(Visitor visitor) throws Exception {
            return visitor.visit(this);
        }
    }

    public static class BooleanEx extends ExpressionOp {

        public String op;
        public boolean value;

        public BooleanEx (String op, boolean value) {
            super(op);
            this.value = value;
        }

        @Override
        public Object accept(Visitor visitor) throws Exception {
            return visitor.visit(this);
        }
    }

    public static class IntegerEx extends ExpressionOp {

        public Integer value;

        public IntegerEx (String op, Integer value) {
            super(op);
            this.value = value;
        }

        @Override
        public Object accept(Visitor visitor) throws Exception {
            return visitor.visit(this);
        }
    }

    public static class RealEx extends ExpressionOp {

        public Float value;

        public RealEx (String op, Float value) {
            super(op);
            this.value = value;
        }

        @Override
        public Object accept(Visitor visitor) throws Exception {
            return visitor.visit(this);
        }
    }

    public static class StringEx extends ExpressionOp {

        public String value;

        public StringEx (String op, String value) {
            super(op);
            this.value = value;
        }

        @Override
        public Object accept(Visitor visitor) throws Exception {
            return visitor.visit(this);
        }
    }

    public static class MultiOp extends ExpressionOp {

        public String op;
        public int exop;
        public ExpressionOp exOp1, exOp2;

        public MultiOp(String op, int exop, ExpressionOp exOp1, ExpressionOp exOp2) {
            super(op);
            this.exop = exop;
            this.exOp1 = exOp1;
            this.exOp2 = exOp2;
        }

        public Object accept(Visitor v) throws Exception {
            return v.visit(this);
        }
    }

    public static class SingOp extends ExpressionOp {

        public String op;
        public int exop;
        public ExpressionOp exOp1;

        public SingOp(String op, int exop, ExpressionOp exOp1) {
            super(op);
            this.exop = exop;
            this.exOp1 = exOp1;
        }

        public Object accept(Visitor v) throws Exception {
            return v.visit(this);
        }
    }

    public static class NullOp extends ExpressionOp {

        public String op;

        public NullOp(String op) {
            super(op);
        }

        public Object accept(Visitor v) throws Exception {
            return v.visit(this);
        }
    }

    public static class OutparOp extends ExpressionOp {

        public String op;
        public Identifier id;

        public OutparOp(String op, Identifier id) {
            super(op);
            this.id = id;
        }

        public Object accept(Visitor v) throws Exception {
            return v.visit(this);
        }

    }

    public static class CallfunEx extends ExpressionOp {

        public String op;
        public CallFunOp callFunOp;

        public CallfunEx (String op, CallFunOp callfunOp) {
            super(op);
            this.callFunOp = callfunOp;
        }

        public Object accept(Visitor v) throws Exception {
            return v.visit(this);
        }
    }

}
