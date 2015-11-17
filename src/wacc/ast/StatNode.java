package wacc.ast;

import wacc.symbolTable.SymbolTable;

public abstract class StatNode extends ASTNode {

  public void setSymbolTable(SymbolTable symbolTable) {
    this.symbolTable = symbolTable;
  }

  public boolean hasReturn() {
    return false;
  }
}
