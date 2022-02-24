package Visitor;

import java.io.IOException;
import java.io.PrintWriter;
import Node.*;
import Node.StatementOp.*;
import Node.ExpressionOp.*;

public class NodeVisitor implements Visitor {

    PrintWriter p;

    public NodeVisitor(String file) {
        try {
            p = new PrintWriter(file + ".xml");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void flush() {
        p.flush();
        p.close();
    }

    @Override
    public Object visit(ProgramOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        for (int i = n.varDeclOp.size() -1; i>=0; i--)
            n.varDeclOp.get(i).accept(this);
        for (int i = n.funOp.size() -1; i>=0; i--)
            n.funOp.get(i).accept(this);
        if (n.mainOp != null)
            n.mainOp.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    @Override
    public Object visit(MainOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        n.body.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    @Override
    public Object visit(VarDeclOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        for (int i = n.idInitOp.size() -1; i>=0; i--)
            n.idInitOp.get(i).accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    @Override
    public Object visit(ParDeclOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        if (n.id != null)
            n.id.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    @Override
    public Object visit(IdInitOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        n.id.accept(this);
        if (n.e != null)
            n.e.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    @Override
    public Object visit(FunOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        n.id.accept(this);
        for (int i = n.parDeclOp.size() -1; i>=0; i--)
            n.parDeclOp.get(i).accept(this);
        if (n.bodyOp != null)
            n.bodyOp.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }


    @Override
    public Object visit(BodyOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        for (int i = n.varDeclOp.size() -1; i>=0; i--)
            n.varDeclOp.get(i).accept(this);
        for (int i = n.stat.size() -1; i>=0; i--)
            n.stat.get(i).accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(Identifier n) {
        p.println("<" + n.getOp() + ">\n");
        p.println("" + n.value + "\n");
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(IntegerEx n) {
        p.println("<" + n.getOp() + ">\n");
        p.println("" + n.value + "\n");
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(RealEx n) {
        p.println("<" + n.getOp() + ">\n");
        p.println("" + n.value + "\n");
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(StringEx n) {
        p.println("<" + n.getOp() + ">\n");
        p.println("" + n.value + "\n");
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(MultiOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        n.exOp1.accept(this);
        n.exOp2.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(SingOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        p.println("" + n.exop + "\n");
        n.exOp1.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(NullOp n) {
        p.println("<" + n.getOp() + ">\n");
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(OutparOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        n.id.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(BooleanEx n) {
        p.println("<" + n.getOp() + ">\n");
        p.println("" + n.value + "\n");
        p.println("</" + n.getOp() + ">\n");
        return null;
    }


    public Object visit(ReadOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        for (int i = n.idlist.size() -1; i>=0; i--) {
            n.idlist.get(i).accept(this);
            if (n.exOp != null)
                n.exOp.accept(this);
        }
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(WriteOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        if (n.exOp != null)
            n.exOp.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(AssignOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        if (n.id != null)
            n.id.accept(this);
        if (n.exOp != null)
            n.exOp.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(WhileOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        if (n.exOp != null)
            n.exOp.accept(this);
        if (n.bodyOp != null)
            n.bodyOp.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(IfStatOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        if (n.exOp != null)
            n.exOp.accept(this);
        if (n.bodyOp != null)
            n.bodyOp.accept(this);
        if (n.el != null)
            n.el.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(SwitchOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        n.i.accept(this);
        n.e.accept(this);
        for (int i = n.el.size() -1; i>=0; i--) {
            n.el.get(i).accept(this);
        }
        n.e1.accept(this);
        for (int i = n.el1.size() -1; i>=0; i--) {
            n.el.get(i).accept(this);
        }
        n.e2.accept(this);
        for (int i = n.el2.size() -1; i>=0; i--) {
            n.el.get(i).accept(this);
        }
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(ElseStatOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        if (n.bodyOp != null)
            n.bodyOp.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(ReturnOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        if (n.exOp != null)
            n.exOp.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(CallFunOp n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        n.id.accept(this);
        if (n.exOpList != null)
            for (int i = n.exOpList.size() -1; i>=0; i--)
                n.exOpList.get(i).accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }

    public Object visit(CallfunEx n) throws Exception {
        p.println("<" + n.getOp() + ">\n");
        n.callFunOp.accept(this);
        p.println("</" + n.getOp() + ">\n");
        return null;
    }
}



