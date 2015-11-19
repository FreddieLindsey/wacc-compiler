package wacc.ast;

import wacc.ast.io.BeginStatNode;
import wacc.ast.io.CompStatNode;
import wacc.ast.type.TypeNode;
import wacc.symbolTable.SymbolTable;

public abstract class ASTNode {

  protected ASTNode parent;
  protected SymbolTable symbolTable;
  protected boolean semanticallyValid;

  public ASTNode() {
    semanticallyValid = false;
    symbolTable = new SymbolTable();
  }

  public void setParent(ASTNode parent) {
    this.parent = parent;
    if (parent != null) symbolTable.setParent(parent.getSymbolTable());
  }

  public void addToScope(String s, TypeNode t) {
    if (parent != null) {
      ASTNode a = parent;
      while (a.parent != null && !(a instanceof BeginStatNode) && a.parent instanceof CompStatNode) {
        a = a.parent;
      }
      a.symbolTable.add(s, t);
    }
  }

  public abstract boolean isSemanticallyValid();

  public SymbolTable getSymbolTable() {
    return symbolTable;
  }

}
