package wacc.ast;

import org.antlr.v4.runtime.tree.ParseTree;
import antlr.BasicParserBaseVisitor;

import java.util.Set;

public class ASTTree {

  private final ParseTree parseTree;
  private Set<String> semanticErrors;
  private ASTNode head;
  private BasicParserBaseVisitor visitor;

  public ASTTree(ParseTree parseTree) {
    this.parseTree = parseTree;
  }

  public ASTTree(ProgramContext p) {
  	this.ProgramContext = p;
  	visitor = new BasicParserBaseVisitor<ProgramNode>();
  	head = visitProgram(p);
  }


}
