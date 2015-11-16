package wacc.ast;

import wacc.symbolTable.SymbolTable;

public class ParamListNode extends ASTNode {

  private ParamNode[] ps;
  private SymbolTable scope;

  public ParamListNode(ASTNode parent, ParamNode[] ps) {
    super(parent);
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
    scope = new SymbolTable(null);
    return true;
  }

  public ParamNode[] getParams() {
    return ps;
  }
}
