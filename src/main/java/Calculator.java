package main.java;

import main.java.Node.*;
import java_cup.runtime.Symbol;
import main.java.Visitor.MyFunCVisitor;
import main.java.Visitor.NodeVisitor;
import main.java.Visitor.SemanticVisitor;

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

        System.out.println("Avvio calcolatrice in corso ... \n");
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

//C:\jflex-1.8.2\bin\jflex -d src srcjflexcup\Lexer.flex
//java -jar C:\CUP\java-cup-11b.jar -parser main.java.main.java.main.java.main.java.MyFunparser -symbols Token -destdir src srcjflexcup/myfun.cup
//NOTA: In myfunparser per NUMBER_INT in expr: Integer.parseInt((String)((java_cup.runtime.Symbol) CUP$main.java.main.java.main.java.main.java.MyFunparser$stack.peek()).value);
