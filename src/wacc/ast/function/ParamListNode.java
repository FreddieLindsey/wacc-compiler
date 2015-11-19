package wacc.ast.function;

import wacc.ast.ASTNode;
import wacc.symbolTable.SymbolTable;

import java.util.ArrayList;

public class ParamListNode extends ASTNode {

  private ArrayList<ParamNode> ps;

  public ParamListNode() {
    super();
    this.ps = new ArrayList<>();
  }

  @Override
  public boolean isSemanticallyValid() {
    SymbolTable s = new SymbolTable();
    for (ParamNode p : ps) {
      if (!p.isSemanticallyValid()
        || s.lookUp(p.getIdent().getIdent()) != null) {
        return false;
      }
      s.add(p.getIdent().getIdent(), p.getType());
    }

    semanticallyValid = true;
    return semanticallyValid;
  }

  public ArrayList<ParamNode> getParams() {
    return ps;
  }

  public void addParam(ParamNode p) {
    ps.add(p);
    p.setParent(this);
  }
}
