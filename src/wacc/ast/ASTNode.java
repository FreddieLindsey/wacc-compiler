package wacc.ast;

import wacc.symbolTable.SymbolTable;

public abstract class ASTNode {

  protected ASTNode parent;
  protected SymbolTable symbolTable;

  public ASTNode() {
    this.symbolTable = new SymbolTable((parent != null) ? parent.getSymbolTable() : null);
  }
  public abstract boolean isSemanticallyValid();
  public SymbolTable getSymbolTable() {
    return symbolTable;
  }

}
