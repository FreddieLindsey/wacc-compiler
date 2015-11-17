package wacc.ast.function;

import wacc.ast.ASTNode;
import wacc.symbolTable.SymbolTable;

import java.util.ArrayList;

public class ParamListNode extends ASTNode {

  private ArrayList<ParamNode> ps;
  private SymbolTable scope;

  public ParamListNode() {
    super();
    this.ps = new ArrayList<>();
    this.scope = new SymbolTable();
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
    scope = new SymbolTable();
    return true;
  }

  public ArrayList<ParamNode> getParams() {
    return ps;
  }

  public void addParam(ParamNode p) {
    ps.add(p);
    p.setParent(this);
  }
}
