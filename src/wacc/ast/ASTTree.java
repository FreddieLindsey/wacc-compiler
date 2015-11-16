package wacc.ast;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Set;

public class ASTTree {

  private final ParseTree parseTree;
  private Set<String> semanticErrors;

  public ASTTree(ParseTree parseTree) {
    this.parseTree = parseTree;
  }
}
