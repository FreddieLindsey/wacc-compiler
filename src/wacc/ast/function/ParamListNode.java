package wacc.ast.function;

import wacc.ast.ASTNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ParamListNode extends ASTNode {

  private ArrayList<ParamNode> ps;

  public ParamListNode() {
    super();
    this.ps = new ArrayList<>();
  }

  @Override
  public boolean isSemanticallyValid() {
    // Check for multiple declarations of same ident
    Set<String> idents = new HashSet<>();
    for (ParamNode p : ps) {
      if (!p.isSemanticallyValid()) {
        return false;
      }
      String ident = p.getIdent().getIdent();
      if (idents.contains(ident)) {
        return false;
      } else {
        idents.add(ident);
      }
    }
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
