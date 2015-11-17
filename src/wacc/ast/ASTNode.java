package wacc.ast;

import wacc.symbolTable.SymbolTable;

public abstract class ASTNode {

  protected ASTNode parent;
  protected SymbolTable symbolTable;

  public ASTNode() {
    symbolTable = new SymbolTable();
  }
  public void setParent(ASTNode parent) {
    this.parent = parent;
    if (parent != null) symbolTable.setParent(parent.getSymbolTable());
  }
  public abstract boolean isSemanticallyValid();
  public SymbolTable getSymbolTable() {
    return symbolTable;
  }

}
