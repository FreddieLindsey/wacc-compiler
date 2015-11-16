package wacc.ast;

import wacc.symbolTable.SymbolTable;

public abstract class ASTNode {

  protected ASTNode parent;
  protected SymbolTable symbolTable;

  public ASTNode(ASTNode parent) {
    this.parent = parent;
    this.symbolTable = new SymbolTable((parent != null) ? parent.getSymbolTable() : null);
  }
  public abstract boolean isSemanticallyValid();
  public SymbolTable getSymbolTable() {
    return symbolTable;
  }

}
