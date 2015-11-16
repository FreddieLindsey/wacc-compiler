package wacc.ast;

import org.antlr.v4.runtime.tree.ParseTree;

public class ASTTree {

  private final ParseTree parseTree;
  private final ProgramNode programNode;

  public ASTTree(ParseTree parseTree) {
    this.parseTree = parseTree;
    this.programNode = new ProgramNode(null);
  }

  public ParseTree getParseTree() {
    return parseTree;
  }
  
  public ProgramNode getASTTree() {
    return programNode;
  }

}
