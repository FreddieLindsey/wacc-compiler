package wacc.ast;

import wacc.symbolTable.SymbolTable;

public class ParamListNode implements ASTNode {

  private ParamNode[] ps;
  private SymbolTable scope;

  public ParamListNode(ParamNode[] ps) {
    this.ps = ps;
    this.scope = new SymbolTable(null);
  }

  @Override
  public boolean isSemanticallyValid() {
    for (ParamNode p : ps) {
      p.setScope(scope);
      if (!p.isSemanticallyValid()) {
        return false;
      }
      scope.add(p.getIdent().getIdent(), p.getType());
    }
    return true;
  }

  public ParamNode[] getParams() {
    return ps;
  }
}
