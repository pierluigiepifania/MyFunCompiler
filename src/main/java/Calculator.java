import Node.ProgramOp;
import Visitor.MyFunCVisitor;
import Visitor.NodeVisitor;
import Visitor.SemanticVisitor;
import java_cup.runtime.Symbol;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Calculator {
    public static void main(String[] args) throws Exception {

        Path inputPath = Paths.get(args[0]);
        String inputFileName = inputPath.getFileName().toString(); //prendo il nome del file
        int dotIndex = inputFileName.lastIndexOf(".");
        String cFileName = (dotIndex == -1 ? inputFileName : inputFileName.substring(0, dotIndex) + ".c"); //rimuovo estensione
        String coutdir = "test_files/c_out/";
        String tmp = coutdir + cFileName.toString();
        Files.createDirectories(Paths.get(coutdir));

        try {
            BufferedReader in = new BufferedReader(new FileReader(inputPath.toString()));
            Lexer lexer = new Lexer(in);
            Symbol sym;
            MyFunparser parser = new MyFunparser(lexer);
            sym = parser.parse();
            ProgramOp root = (ProgramOp) sym.value;
            NodeVisitor v = new NodeVisitor("Visitor");
            root.accept(v);
            v.flush();
            SemanticVisitor semanticVisitor = new SemanticVisitor();
            root.accept(semanticVisitor);
            MyFunCVisitor toCVisitor = new MyFunCVisitor(tmp);
            root.accept(toCVisitor);
            toCVisitor.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
