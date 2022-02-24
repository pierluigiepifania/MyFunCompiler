package Visitor;

import Node.*;
import Node.StatementOp.*;
import Node.ExpressionOp.*;
import Utility.MyFunC;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class MyFunCVisitor implements Visitor {

    PrintWriter printer;
    public List<String> printParameter; //utilizzato per stampare i parametri nella printf
    public int typePrintOperation; //utilizzato per stampare i parametri delle operazioni nella printf come x1+x2
    public List<Identifier> recursive_fun;  //utilizzato per bloccare la chiamata alle funzioni ricorsive nelle dichiarazioni delle funzioni
    public boolean assignString = false; //utlizzato per l'assegnazione di stringhe concatenate
    public boolean i_m_string = false; //utilizzato per inserire le "" ad una stringa in una concatenazione di stringhe
    public final int STRING_SIZE = 101; //spazio allocato dalle malloc
    public List<String> pointer; //utilizzato per tener traccia dei puntatori durante il passaggio di essi alle funzioni
    public boolean assignstat = false; //per distinguere le stringhe a cui va allocata memoria da quelle a cui viene assegnato un valore
    public boolean concat_in_callfun = false;

    public MyFunCVisitor(String file) {
        printParameter = new LinkedList<>();
        recursive_fun = new LinkedList<>();
        pointer = new LinkedList<>();
        try {
            printer = new PrintWriter(file);
            printer.println("#include <stdlib.h>");
            printer.println("#include <stdio.h>");
            printer.println("#include <string.h>");
            printer.println("#include <math.h>");
            printer.print("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flush() {
        printer.flush();
        printer.close();
    }

    public void addNL() {
        printer.print("\n");
    }

    public void addSemiColon() {
        printer.print(";");
    }

    public void addAssign() {
        printer.print(" = ");
    }

    public void addComma() {
        printer.print(",");
    }

    public void addLpar() {
        printer.print(" (");
    }

    public void addRpar() {
        printer.print(") ");
    }

    public void addGLpar() {
        printer.print("{\n");
    }

    public void addGRpar() {
        printer.print("}\n");
    }

    public void addQuotes() {
        printer.print((char) (34));
    }

    @Override
    public Object visit(ProgramOp n) throws Exception {
        print_starting_fun();  //inserisce funzioni ausiliarie
        for (int i = n.varDeclOp.size() - 1; i >= 0; i--)
            n.varDeclOp.get(i).accept(this);
        addNL();
        for (int i = n.funOp.size() - 1; i >= 0; i--)
            n.funOp.get(i).accept(this);
        n.mainOp.accept(this);
        return null;
    }

    @Override
    public Object visit(VarDeclOp n) throws Exception {
        if (n.type != null) {  //se non si tratta di un tipo var
            printer.print(MyFunC.typeConverter(n.type));
            for (int i = 0; i < n.idInitOp.size(); i++) {
                n.idInitOp.get(i).accept(this);
                if (i != n.idInitOp.size() - 1)
                    addComma();
                if (n.type == Token.STRING && !assignstat) {       //se si tratta di una stringa a cui non viene
                    printer.print(" = (char*) malloc (" + STRING_SIZE + ")"); //assegnato qualcosa devo allocare la memoria
                }
                assignstat = false; //reimposto il flag
            }
        } else {  //se è un var aspetto di ricevere il tipo
            for (int i = 0; i < n.idInitOp.size(); i++) {
                printer.print(MyFunC.typeConverter(n.idInitOp.get(i).getType()));
                n.idInitOp.get(i).accept(this);
                if (i != n.idInitOp.size() - 1)
                    addSemiColon();
            }
        }
        addSemiColon();
        addNL();
        return null;
    }

    @Override
    public Object visit(IdInitOp n) throws Exception {
        if (n.id.getType() == Token.STRING)
            i_m_string = true;
        n.id.accept(this);
        if (n.e != null) {
            assignstat = true;
            addAssign();
            n.e.accept(this);
        }
        i_m_string = false;
        return null;
    }

    public Object visit(Identifier n) throws Exception {
        if (!printParameter.contains(n.value))    //se devo stampare dei valori, li raccolgo singolarmente
            printParameter.add(n.value);
        printer.print(n.value);
        return null;
    }

    @Override
    public Object visit(FunOp n) throws Exception {
        if (recursive_fun.contains(n.id))  //controllo che non sia stata già dichiarata questa funzione per evitare la ricorsione,
            return null;                    //altrimenti nel body le chiamate ricorsive la stamperanno più volte
        recursive_fun.add(n.id);
        if (n.type != null) {
            printer.print(MyFunC.typeConverter(n.type) + " ");
        } else
            printer.print("void ");     //se la funzione non ha un valore di ritorno
        n.id.accept(this);
        addLpar();
        for (int i = 0; i < n.parDeclOp.size(); i++) {
            n.parDeclOp.get(i).accept(this);
            if (i != n.parDeclOp.size() - 1)
                addComma();
        }
        addRpar();
        addGLpar();
        n.bodyOp.accept(this);
        addGRpar();
        pointer.clear();
        return null;
    }

    @Override
    public Object visit(ParDeclOp n) throws Exception {
        printer.print(MyFunC.typeConverter(n.type));
        if (n.inOut == 1) {   //controllo se è un outpar, gestiti tramite puntatori a cui aggiungo l'operatore *
            pointer.add(n.id.value);
            printer.print("*");
        }
        n.id.accept(this);
        return null;
    }

    @Override
    public Object visit(BodyOp n) throws Exception {
        for (int i = n.varDeclOp.size() - 1; i >= 0; i--)
            n.varDeclOp.get(i).accept(this);
        for (int i = n.stat.size() - 1; i >= 0; i--) {
            n.stat.get(i).accept(this);
            i_m_string = false;
            addSemiColon();
            addNL();
        }
        return null;
    }

    @Override
    public Object visit(MainOp n) throws Exception {
        printer.print("int main");
        addLpar();
        addRpar();
        addGLpar();
        n.body.accept(this);
        printer.print("return 0");
        addSemiColon();
        addGRpar();
        return null;
    }

    public Object visit(SwitchOp n) throws Exception {
        printer.print("switch ");
        addLpar();
        n.i.accept(this);
        addRpar();
        addGLpar();
        printer.print("case ");
        n.e.accept(this);
        printer.println(":");
        for (int i = n.el.size() -1; i>=0; i--) {
            n.el.get(i).accept(this);
        }
        printer.println(" break;");
        printer.print("case ");
        n.e1.accept(this);
        printer.println(":");
        for (int i = n.el1.size() -1; i>=0; i--) {
            n.el2.get(i).accept(this);
        }
        printer.println(" break;");
        printer.print("case ");
        n.e2.accept(this);
        printer.println(":");
        for (int i = n.el2.size() -1; i>=0; i--) {
            n.el2.get(i).accept(this);
        }
        printer.println(" break;");
        addGRpar();
        return null;
    }

    public Object visit(IfStatOp n) throws Exception {
        printer.print("if");
        addLpar();
        if (n.exOp != null)
            n.exOp.accept(this);
        addRpar();
        addGLpar();
        if (n.bodyOp != null)
            n.bodyOp.accept(this);
        addGRpar();
        if (n.el != null) {
            printer.print("else");
            addGLpar();
            n.el.accept(this);
            addGRpar();
        }
        return null;
    }

    public Object visit(ElseStatOp n) throws Exception {
        if (n.bodyOp != null)
            n.bodyOp.accept(this);
        return null;
    }

    public Object visit(WhileOp n) throws Exception {
        printer.print("while ");
        addLpar();
        if (n.exOp != null)
            n.exOp.accept(this);
        addRpar();
        addGLpar();
        if (n.bodyOp != null)
            n.bodyOp.accept(this);
        addGRpar();
        return null;
    }

    public Object visit(AssignOp n) throws Exception {
        if (n.id.getType() == Token.STRING) {  //sto assegnando ad una stringa imposto un flag
            assignString = true;
        }
        if (pointer.contains(n.id.value) && n.exOp.getType() != Token.STRING)   //se si tratta di un puntatore ricevuto tramite outpar aggiungo l'operatore *
            printer.print("*");
        n.id.accept(this);
        addAssign();
        n.exOp.accept(this);
        addSemiColon();
        addNL();
        return null;
    }

    public Object visit(ReturnOp n) throws Exception {
        printer.print("return ");
        n.exOp.accept(this);
        return null;
    }

    public Object visit(IntegerEx n) {
        printer.print(n.value);
        return null;
    }

    public Object visit(RealEx n) {
        printer.print(n.value);
        return null;
    }

    public Object visit(StringEx n) {
        if (!i_m_string) {
            String s = removeQuotes(n.value);
            printer.print(s);
        } else {
            String s = removeQuotes(n.value);
            addQuotes();
            printer.print(s);
            addQuotes();
        }
        return null;
    }

    public Object visit(NullOp n) {
        printer.print("null");
        return null;
    }

    public Object visit(BooleanEx n) {
        printer.print(n.value ? 1 : 0);
        return null;
    }

    public Object visit(ReadOp n) throws Exception {
        if (n.exOp != null && n.exOp.getType() == Token.STRING) { //se la funzione ha un testo da stampare la divido in una printf
            printer.print("printf");                               //ed una scanf
            addLpar();
            addQuotes();
            n.exOp.accept(this);
            addQuotes();
            addRpar();
            addSemiColon();
        }
        printer.print("scanf");
        addLpar();
        addQuotes();
        for (int i = n.idlist.size() - 1; i >= 0; i--) {
            printer.print(" " + MyFunC.getPlaceholder(n.idlist.get(i).getType()));
        }
        addQuotes();
        addComma();
        for (int i = n.idlist.size() - 1; i >= 0; i--) {
            if (n.idlist.get(i).getType() != Token.STRING)      //se non sto stampando una stringa aggiungo l'operatore &
                printer.print("&");
            n.idlist.get(i).accept(this);
            if (i != 0)
                addComma();
        }
        addRpar();
        return null;
    }

    public Object visit(CallfunEx n) throws Exception {
        n.callFunOp.accept(this);
        return null;
    }

    public Object visit(CallFunOp n) throws Exception {
        n.isFunction = true;
        concat_in_callfun = true;
        n.id.accept(this);
        addLpar();
        if (n.exOpList != null) {
            for (int i = n.exOpList.size() - 1; i >= 0; i--) {
                n.exOpList.get(i).accept(this);
                if (i != 0)
                    addComma();
            }
        }
        addRpar();
        concat_in_callfun = false;
        return null;
    }

    public Object visit(WriteOp n) throws Exception {
        printParameter.clear();     //azzero la lista dei parametri
        printer.print("printf ");
        addLpar();
        addQuotes();
        n.exOp.accept(this); //mentre esploro il nodo aggiungo i parametri, se ci sono, da stampare
        if (printParameter.size() == 1) { //sto stampando un solo valore, quindi aggiuno solo il placeholder
            printer.print(" :" + MyFunC.getPlaceholder(n.exOp.getType()));
        }
        addQuotes();
        if (!printParameter.isEmpty()) {  //aggiungo i parametri alla printf
            addComma();
            printWriteParam();
        }
        addRpar();
        printParameter.clear();  //svuoto la lista
        return null;
    }

    public Object visit(MultiOp n) throws Exception {
        typePrintOperation = n.exop;
        if (typePrintOperation == Token.STR_CONCAT && (assignString || concat_in_callfun)) {  //concatenazione di stringhe per l'assegnazione
            i_m_string = true;
            printer.print("strcat");
            addLpar();
            if (n.exOp1.getType() == Token.INTEGER) {  //se è un intero lo trasformo in una stringa puntatore
                printer.print("convert");
                addLpar();
                n.exOp1.accept(this);
                addRpar();
            } else if (n.exOp1.getType() == Token.REAL) {  //se è un float lo trasformo in una stringa puntatore
                printer.print("convertF");
                addLpar();
                n.exOp1.accept(this);
                addRpar();
            } else if (n.exOp1.getType() == Token.STRING) {  //se è una stringa lo trasformo in un puntatore
                printer.print("convertS");
                addLpar();
                n.exOp1.accept(this);
                addRpar();
            } else
                n.exOp1.accept(this);
            addComma();
            if (n.exOp2.getType() == Token.INTEGER) {
                printer.print("convert");
                addLpar();
                n.exOp2.accept(this);
                addRpar();
            } else if (n.exOp2.getType() == Token.REAL) {
                printer.print("convertF");
                addLpar();
                n.exOp2.accept(this);
                addRpar();
            } else if (n.exOp2.getType() == Token.STRING) {
                printer.print("convertS");
                addLpar();
                n.exOp2.accept(this);
                addRpar();
            } else
                n.exOp2.accept(this);
            addRpar();
            assignString = false;
            concat_in_callfun = false;
        } else if (typePrintOperation == Token.STR_CONCAT) {  //concatenazione di stringhe per la printf con interi
            n.exOp1.accept(this);
            if (n.exOp1.getType() != Token.STRING)
                printer.print(" " + MyFunC.getPlaceholder(n.exOp1.getType()));
            n.exOp2.accept(this);
            if (n.exOp2.getType() != Token.STRING)
                printer.print(" " + MyFunC.getPlaceholder(n.exOp2.getType()));
        } else if (typePrintOperation == Token.EQ) { //se sto confrontando due stringhe
            if (n.exOp1.getType() == Token.STRING || n.exOp2.getType() == Token.STRING) {
                printer.print("strcmp");
                i_m_string = true;
                addLpar();
                n.exOp1.accept(this);
                addComma();
                i_m_string = true;
                n.exOp2.accept(this);
                addRpar();
                printer.print("== 0");
                i_m_string = false;
            } else {    //qualsiasi altro confronto con EQ
                n.exOp1.accept(this);
                printer.print(MyFunC.opConverter(typePrintOperation));
                n.exOp2.accept(this);
            }
        } else {        //qualsiasi altra operazione
            String s = MyFunC.opConverter(typePrintOperation); //per non perdere l'operatore nel caso in n.exOp1 si faccia riferimento ad n.exop
            n.exOp1.accept(this);
            printer.print(s);
            n.exOp2.accept(this);
        }
        return null;
    }

    public Object visit(SingOp n) throws Exception {
        if (n.vartype) {        //se si tratta di un var ho bisogno del tipo
            printer.print(MyFunC.typeConverter(n.exOp1.getType()));
        }
        n.exOp1.accept(this);
        return null;
    }

    public Object visit(OutparOp n) throws Exception {
        printer.print("&");
        n.id.accept(this);
        n.addRetType(n.id.getType());
        return null;
    }

    /////////////////////////////////////

    public String removeQuotes(String s) {  //utilizzato per rimuovere "" dalle stringhe
        StringBuffer sb = new StringBuffer(s);
        sb.deleteCharAt(s.length() - 1);
        sb.deleteCharAt(0);
        return sb.toString();
    }

    public void printWriteParam() {    //utilizzata per stampare i parametri nella printf
        String s = "";
        if (typePrintOperation == Token.PLUS || typePrintOperation == Token.MINUS || typePrintOperation == Token.DIV ||
                typePrintOperation == Token.DIVINT || typePrintOperation == Token.TIMES || typePrintOperation == Token.POW) {
            for (int i = 0; i < printParameter.size(); i++) {   //compongo l'operazione all'interno di un parametro della printf
                if (typePrintOperation == Token.POW) {
                    s += "power(" + printParameter.get(i) + "," + printParameter.get(i + 1) + ")";
                    i++;
                } else {
                    s += printParameter.get(i);
                    if (i != printParameter.size() - 1)
                        s += MyFunC.opConverter(typePrintOperation); //aggiungo l'operatore
                }
            }
            if (!printParameter.contains(s))
                printParameter.add(s);
        }
        for (int i = 0; i < printParameter.size(); i++) {
            printer.print(printParameter.get(i));
            if (i < printParameter.size() - 1)
                addComma();
        }
    }

    public void print_starting_fun() {
        printer.print("char* convert (int n) {\n" +
                "    char* to_ret = (char *) malloc (" + STRING_SIZE + ");\n" +
                "    sprintf(to_ret,\"%d\",n);\n" +
                "    return to_ret;\n" +
                "}\n");
        printer.print("char* convertF (float n) {\n" +
                "    char* to_ret = (char *) malloc (" + STRING_SIZE + ");\n" +
                "    sprintf(to_ret,\"%f\",n);\n" +
                "    return to_ret;\n" +
                "}\n");
        printer.print("char* convertS (char * s) {\n" +
                "    char* to_ret = (char *) malloc (" + STRING_SIZE + ");\n" +
                "    sprintf(to_ret,\"%s\",s);\n" +
                "    return to_ret;\n" +
                "}\n");
        printer.print("int power(int a, int b) {\n" +
                "int i = 0;\n" +
                "int res =1;\n" +
                "while(i<b) {\n" +
                "res = res*a;\n" +
                "i = i+1;\n" +
                "}\n" +
                "return res;\n" +
                " }\n");
    }

}
