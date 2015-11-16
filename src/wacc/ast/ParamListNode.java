package wacc.ast;

public class ParamListNode implements ASTNode {

  private ParamNode[] ps;

  public ParamListNode(ParamNode[] ps) {
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

  public ParamNode[] getParams() {
    return ps;
  }
}
