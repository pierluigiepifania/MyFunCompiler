import Node.ProgramOp;
import Visitor.MyFunCVisitor;
import Visitor.NodeVisitor;
import Visitor.SemanticVisitor;
import java_cup.runtime.Symbol;
import java.io.BufferedReader;
import java.io.FileReader;

public class Calculator {

    public static void main(String[] args) throws Exception {
        String filePath = "valid2.txt";

        // TESTER LEXER
        /*
        FileReader input = new FileReader(filePath);
        Lexer lexer = new Lexer(input);
        Symbol sym;

        while (((sym = lexer.next_token()).sym) != Token.EOF) {

            System.out.println("<" + sym.sym + ", '" + sym.value + "'>");

        }
        */

        // TESTER CUP
        FileReader input = new FileReader(filePath);
        System.out.println("Avvio calcolatrice in corso ... \n");
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            Lexer lexer = new Lexer(in);
            Symbol sym;
            MyFunparser parser = new MyFunparser(lexer);
            sym = parser.parse();
            ProgramOp root = (ProgramOp) sym.value;
            NodeVisitor v = new NodeVisitor("output2");
            root.accept(v);
            v.flush();
            SemanticVisitor semanticVisitor = new SemanticVisitor();
            root.accept(semanticVisitor);
            MyFunCVisitor toCVisitor = new MyFunCVisitor("toC");
            root.accept(toCVisitor);
            toCVisitor.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

//C:\jflex-1.8.2\bin\jflex -d src srcjflexcup\Lexer.flex
//java -jar C:\CUP\java-cup-11b.jar -parser MyFunparser -symbols Token -destdir src srcjflexcup/myfun.cup
//java -jar C:\CUP\java-cup-11b.jar -dump -destdir src srcjflexcup/myfun.cup 2> dumpfile
//NOTA: In myfunparser per NUMBER_INT in expr: Integer.parseInt((String)((java_cup.runtime.Symbol) CUP$MyFunparser$stack.peek()).value);
//NOTA: In myfunparser per NUMBER_INT in stat: Integer.parseInt((String)((java_cup.runtime.Symbol) CUP$MyFunparser$stack.peek()).value);
//NOTA: Copiare token in symboltable