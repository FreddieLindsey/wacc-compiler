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

  public void checkSymbolTable(String ident) {
    if (this.symbolTable.lookUp(ident) != null) {
      throw new RuntimeException("Function " + ident + " has already "
        + "been declared in the current scope");
    }
  }

  public void requireSymbol(String ident) {
    if (this.symbolTable.lookUp(ident) == null) {
      throw new RuntimeException("Identifier " + ident + " does not exist in " +
        "the current scope");
    }
  }

}
