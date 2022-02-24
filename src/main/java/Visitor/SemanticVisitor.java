package Visitor;

import Node.*;
import Node.StatementOp.*;
import Node.ExpressionOp.*;
import SymbleTable.Symbol;
import SymbleTable.SymbolTableStack;
import Utility.TypeCheck;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class SemanticVisitor implements Visitor {

    private SymbolTableStack stack;

    public SemanticVisitor() {
        stack = new SymbolTableStack();
    }

    @Override
    public Object visit(ProgramOp n) throws Exception {
        stack.enterScope();
        Symbol s;
        for (int i = n.varDeclOp.size() - 1; i >= 0; i--)
            n.varDeclOp.get(i).accept(this);
        for (int i = n.funOp.size() - 1; i >= 0; i--)
            n.funOp.get(i).accept(this);
        n.mainOp.accept(this);
        stack.exitScope();
        return null;
    }

    @Override
    public Object visit(VarDeclOp n) throws Exception {
        for (int i = n.idInitOp.size() - 1; i >= 0; i--) {
            if (n.type != null)
                n.idInitOp.get(i).addType(n.type);
            else
                n.idInitOp.get(i).vartype = true;
            n.idInitOp.get(i).accept(this);
        }
        return null;
    }

    @Override
    public Object visit(IdInitOp n) throws Exception {
        if (stack.probe(n.id.value))
            throw new Exception("Multiple Declaration of Id");
        if (n.e != null) {
            n.e.accept(this);
            if (n.vartype = true)
                n.addType(n.e.getType());
            if (TypeCheck.checkType(Token.ASSIGN, n.getType(), n.e.getType()) == -1)
                throw new Exception("Assign type error");
        }
        stack.addId(new Symbol(n.id.value, n.getType()));
        n.id.accept(this);
        return null;
    }

    public Object visit(Identifier n) throws Exception {
        Symbol s = stack.lookup(n.value);
        if (s == null)
            throw new Exception("Identifier "+n.value+" not declared");
        n.typeList = s.typeList;
        return null;
    }

    @Override
    public Object visit(FunOp n) throws Exception {
        Symbol fun = stack.lookup(n.id.value);
        if (fun != null)
            throw new Exception("Multiple Declaration of Function");
        List<Integer> param = new ArrayList<>();
        List<Integer> outpar = new ArrayList<>();
        Integer toRet;
        for (int i = n.parDeclOp.size() - 1; i >= 0; i--) {
            if (n.parDeclOp.get(i).inOut == 1)
                outpar.add(n.parDeclOp.get(i).type);
            else
                param.add(n.parDeclOp.get(i).type);
        }
        if (n.type != null) {
            toRet = n.type;
            fun = new Symbol(n.id.value, param, outpar, toRet);
        } else if (!outpar.isEmpty())
            fun = new Symbol(n.id.value, param, outpar);
        else
            fun = new Symbol(n.id.value, param);
        stack.addId(fun);
        stack.enterScope();
        n.id.accept(this);
        for (int i = n.parDeclOp.size() - 1; i >= 0; i--) {
            n.parDeclOp.get(i).accept(this);
        }
        n.bodyOp.accept(this);
        if (n.type != null) {  // controllo se il return è del tipo dichiarato
            if (n.type != n.bodyOp.getRetType())
                throw new Exception("Return type error in function");
        }
        stack.exitScope();
        return null;
    }

    @Override
    public Object visit(MainOp n) throws Exception {
        stack.enterScope();
        n.body.accept(this);
        stack.exitScope();
        return null;
    }

    @Override
    public Object visit(BodyOp n) throws Exception {
        stack.enterScope();
        Integer ret = null;
        for (int i = n.varDeclOp.size() - 1; i >= 0; i--)
            n.varDeclOp.get(i).accept(this);
        for (int i = n.stat.size() - 1; i >= 0; i--) {
            n.stat.get(i).accept(this);
            if (n.stat.get(i).isFunction)
                if (n.stat.get(i).getRetType() != null)
                    throw new Exception("Function need assignment");
            if (n.stat.get(i).getRetType() != null) {
                ret = n.stat.get(i).getRetType();
            }
        }
        n.ret = ret;
        stack.exitScope();
        return null;
    }

    @Override
    public Object visit(ParDeclOp n) throws Exception {
        if (stack.probe(n.id.value))
            throw new Exception("Multiple declaration of parameter");
        stack.addId(new Symbol(n.id.value, n.type));
        return null;
    }

    public Object visit(ReadOp n) throws Exception {
        for (int i = n.idlist.size() - 1; i >= 0; i--) {
            n.idlist.get(i).accept(this);
            if (n.exOp != null) {
                n.exOp.accept(this);
            }
        }
        return null;
    }

    public Object visit(WriteOp n) throws Exception {
        n.exOp.accept(this);
        return null;
    }

    public Object visit(AssignOp n) throws Exception {
        n.id.accept(this);
        n.exOp.accept(this);
        if (TypeCheck.checkType(Token.ASSIGN, n.id.getType(), n.exOp.getType()) == -1)
            throw new Exception("Assign type error");
        return null;
    }

    public Object visit(WhileOp n) throws Exception {
        if (n.exOp != null)
            n.exOp.accept(this);
        if (n.bodyOp != null)
            n.bodyOp.accept(this);
        if (n.exOp.getType() != Token.BOOL)
            throw new Exception("While condition error");
        return null;
    }

    public Object visit(IfStatOp n) throws Exception {
        if (n.exOp != null)
            n.exOp.accept(this);
        if (n.bodyOp != null)
            n.bodyOp.accept(this);
        if (n.el != null)
            n.el.accept(this);
        if (n.exOp.getType() != Token.BOOL)
            throw new Exception("If condition error");
        return null;
    }

    public Object visit(SwitchOp n) throws Exception {
        n.i.accept(this);
        n.e.accept(this);
        for (int i = n.el.size() -1; i>=0; i--) {
            n.el.get(i).accept(this);
        }
        n.e1.accept(this);
        for (int i = n.el.size() -1; i>=0; i--) {
            n.el1.get(i).accept(this);
        }
        n.e2.accept(this);
        for (int i = n.el.size() -1; i>=0; i--) {
            n.el2.get(i).accept(this);
        }
        if(n.i.getType()!= n.e.getType() || n.i.getType()!= n.e1.getType() || n.i.getType()!= n.e2.getType()) {
            throw new Exception("Switch type error");
        }
        return null;

    }

    public Object visit(ElseStatOp n) throws Exception {
        if (n.bodyOp != null)
            n.bodyOp.accept(this);
        return null;
    }

    public Object visit(ReturnOp n) throws Exception {
        n.exOp.accept(this);
        n.ret = n.exOp.getType();
        return null;
    }

    public Object visit(IntegerEx n) {
        n.addType(Token.INTEGER);
        return null;
    }

    public Object visit(RealEx n) {
        n.addType(Token.REAL);
        return null;
    }

    public Object visit(StringEx n) {
        n.addType(Token.STRING);
        return null;
    }

    public Object visit(NullOp n) {
        n.addType(Token.NULL);
        return null;
    }

    public Object visit(BooleanEx n) {
        n.addType(Token.BOOL);
        return null;
    }

    public Object visit(SingOp n) throws Exception {
        n.exOp1.accept(this);
        Integer type;
        if (n.exop == 0) {
            type = n.exOp1.getType();
        } else {
            type = TypeCheck.checkType(n.exop, n.exOp1.getType());
        }
        if (type == -1)
            throw new Exception("Operator type error");
        n.addType(type);
        return null;
    }

    public Object visit(MultiOp n) throws Exception {
        n.exOp1.accept(this);
        n.exOp2.accept(this);
        Integer type = TypeCheck.checkType(n.exop, n.exOp1.getType(), n.exOp2.getType());
        if (type == -1)
            throw new Exception("Operator type error" + n.exop);
        n.addType(type);
        return null;
    }

    public Object visit(CallFunOp n) throws Exception {
        n.isFunction = true;
        Symbol s = stack.lookup(n.id.value, 1);
        if (s == null)
            throw new Exception("Function "+n.id.value+" not declared");
        n.id.accept(this);
        List<Integer> types = new LinkedList<>();
        List<Integer> outpar = new LinkedList<>();
        Integer ret = null;
        if (s.typeRet != null)
            ret = s.typeRet;
        if (n.exOpList != null) {
            for (int i = n.exOpList.size() - 1; i >= 0; i--) {
                n.exOpList.get(i).accept(this);
                if (n.exOpList.get(i).typeList != null)
                    types.addAll(n.exOpList.get(i).typeList);
                if (n.exOpList.get(i).outpar != null)
                    outpar.addAll(n.exOpList.get(i).outpar);
            }
            if (types.size() != (s.typeList.size()))
                throw new Exception("Missing function parameter");
            if (!outpar.isEmpty() && (outpar.size() != (s.outpar.size())))
                throw new Exception("Missing outpar return parameter");
            int j = 0;
            for (int i = s.typeList.size() - 1; i >= 0; i--) {
                if (types.get(j) != s.typeList.get(i))
                    throw new Exception("The function parameter types are incorrect");
                j++;
            }
            j = 0;
            if(!outpar.isEmpty())
            for (int i = s.outpar.size() - 1; i >= 0; i--) {
                if (outpar.get(j) != s.outpar.get(i))
                    throw new Exception("The return parameter types are incorrect");
                n.addRetType(outpar.get(j));
                j++;
            }
        }
    n.ret =ret;
        return null;
}

    public Object visit(OutparOp n) throws Exception {
        n.id.accept(this);
        n.addRetType(n.id.getType());
        return null;
    }

    public Object visit(CallfunEx n) throws Exception {
        Symbol s;
        n.callFunOp.accept(this);
        s = stack.lookup(n.callFunOp.id.value);
        n.ret = s.typeRet;
        n.typeList.add(s.typeRet);
        return null;
    }

}
