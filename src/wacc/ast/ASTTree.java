package wacc.ast;

import antlr.BasicParser;
import org.antlr.v4.runtime.tree.ParseTree;
import wacc.parseTreeVisitor.BaseVisitor;

import java.util.Set;

public class ASTTree {

  private final ParseTree parseTree;
  private Set<String> semanticErrors;
  private ASTNode head;
  private BaseVisitor visitor;

  public ASTTree(ParseTree parseTree) {
    this.parseTree = parseTree;
  }

  public ASTTree(BasicParser.ProgramContext p) {
  	visitor = new BaseVisitor();
  	head = visitProgram(p);
  }


}
