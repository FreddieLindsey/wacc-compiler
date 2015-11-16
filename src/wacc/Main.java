package wacc;

import antlr.BasicLexer;
import antlr.BasicParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import wacc.ast.ASTTree;

import java.io.*;

public class Main {

  private static final PrintStream default_ = System.out;
  private static final PrintStream default_err = System.err;
  private static final ByteArrayOutputStream baos = new ByteArrayOutputStream();
  private static final PrintStream capture_ = new PrintStream(baos);

  public static void main(String[] args) throws IOException {
    InputStream i;

    if (args.length > 0) {
      i = new FileInputStream(args[0]);
    } else {
      i = System.in;
    }

    BasicParser parser = parseInput(i);

    engageMessageLock();
    ParseTree parseTree = parser.program();
    String output = baos.toString();
    releaseMessageLock();

    if (!output.equals("")) {
      System.out.print("There were syntax errors in the supplied stream of input"
        + "\n------------------------------\n");
      printErrors(output);
      System.exit(100);
    }

    ASTTree tree = analyseFile(parseTree);
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

  private static void engageMessageLock() {
    System.setErr(capture_);
    System.setOut(capture_);
  }

  private static void releaseMessageLock() {
    baos.reset();
    System.setErr(default_err);
    System.setOut(default_);
  }

  private static void printErrors(String out) {
    String[] errors = out.split("\n");
    int error_count = 1;
    for (String error : errors) {
      System.out.println("Error " + error_count + ":\t" + error);
      error_count++;
    }
  }
}
