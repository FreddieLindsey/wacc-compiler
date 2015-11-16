package wacc;

import antlr.BasicLexer;
import antlr.BasicParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import wacc.ast.ASTTree;

import java.io.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BasicParser parser = parseInput(System.in);

    System.out.println((parser.program()).toStringTree(parser));
  }

  public static BasicParser parseInput(InputStream i) throws IOException {
    return
      new BasicParser(
        new CommonTokenStream(
          new BasicLexer(
            new ANTLRInputStream(i))));
  }

  public static ASTTree analyseFile(ParseTree parseTree) throws IOException {
    return new ASTTree(parseTree);
  }
}
