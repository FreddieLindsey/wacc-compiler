package wacc;

import antlr.BasicLexer;
import antlr.BasicParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import wacc.ast.ASTNode;
import wacc.ast.ProgramNode;
import wacc.parseTreeVisitor.BaseVisitor;
import wacc.backend.Instruction;
import java.util.ArrayList;

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
    String n = "a.out";

    if (args.length > 0) {
      n = args[0];
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
    ArrayList<Instruction> programCode = prog.generateCode();
    StringBuilder assemblyCode = new StringBuilder();
    for (Instruction inst : programCode) {
      assemblyCode.append(inst);
      assemblyCode.append("\n");
    }

    // Generate output file name
    StringBuilder outputName = new StringBuilder(n);
    if (outputName.indexOf(".") > 0) {
      int lastIndex = outputName.lastIndexOf(".");
      outputName.setLength(lastIndex);
    }
    outputName.append(".s");

    // Save to file
    FileWriter writer = new FileWriter(outputName.toString());
    writer.write(assemblyCode.toString());
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
}
