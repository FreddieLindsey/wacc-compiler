package wacc.ast;

import java.util.Set;

public class ParamListNode implements ASTNode {

  private Set<ParamNode> ps;

  public ParamListNode(Set<ParamNode> ps) {
     this.ps = ps;
  }

  @Override
  public boolean isSemanticallyValid() {
    for (ParamNode p : ps) {
      if (!p.isSemanticallyValid()) {
        return false;
      }
    }
    return true;
  }

  public Set<ParamNode> getParams() {
    return ps;
  }
}
