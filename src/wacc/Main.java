package wacc;

import antlr.BasicLexer;
import antlr.BasicParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import wacc.ast.ASTNode;
import wacc.ast.ProgramNode;
import wacc.backend.instruction.InstructionBlock;
import wacc.parseTreeVisitor.BaseVisitor;

import java.io.*;

public class Main {

  public static final int SYNTAX_EXIT = 100;
  protected static final int SEMANTIC_EXIT = 200;

  private static final PrintStream default_ = System.out;
  private static final PrintStream default_err = System.err;
  private static final ByteArrayOutputStream baos = new ByteArrayOutputStream();
  private static final PrintStream capture_ = new PrintStream(baos);

  public static void main(String[] args) throws IOException {
    InputStream i;
    StringBuilder outputName = new StringBuilder("a.out");

    if (args.length > 0) {
      outputName = Main.generateOutputName(args[0]);
      if (args.length > 1) {
        outputName = new StringBuilder(args[1]);
      }
      i = new FileInputStream(new File(args[0]));
    } else {
      i = System.in;
    }

    BasicParser parser = parseInput(i);
    i.close();

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

    ProgramNode prog = analyseFile(parseTree);
    if (prog == null) System.exit(SYNTAX_EXIT);
    boolean valid = prog.isSemanticallyValid();
    if (!valid) System.exit(SEMANTIC_EXIT);

    // Compile
    InstructionBlock programCode = prog.generateCode();

    // Save to file
    FileWriter writer = new FileWriter(outputName.toString());
    writer.write(programCode.toString());
    writer.close();
  }

  public static BasicParser parseInput(InputStream i) throws IOException {
    return
      new BasicParser(
        new CommonTokenStream(
          new BasicLexer(
            new ANTLRInputStream(i))));
  }

  public static ProgramNode analyseFile(ParseTree parseTree) throws IOException {
    BaseVisitor<ASTNode> visitor = new BaseVisitor<>();
    return (ProgramNode) visitor.visit(parseTree);
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

  private static StringBuilder generateOutputName(String input) {
    StringBuilder outputName = new StringBuilder(input);
    int end = 0;
    if (outputName.indexOf(".") > 0) {
      end = outputName.lastIndexOf(".");
    }
    outputName.setLength(end);
    outputName.append(".s");
    return new StringBuilder(outputName.substring(outputName.lastIndexOf("/") + 1));
  }
}
