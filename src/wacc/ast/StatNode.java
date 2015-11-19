package wacc.ast;

import wacc.ast.type.TypeNode;
import wacc.symbolTable.SymbolTable;

public abstract class StatNode extends ASTNode {

  public void setSymbolTable(SymbolTable symbolTable) {
    this.symbolTable = symbolTable;
  }

  public TypeNode returnType() {
    return null;
  }

  public boolean returns() {
    return false;
  }
}
