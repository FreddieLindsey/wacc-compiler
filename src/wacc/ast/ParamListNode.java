package wacc.ast;

public class ParamListNode implements ASTNode {

  private ParamNode[] ps;

  public ParamListNode(ParamNode[] ps) {
     this.ps = ps;
  }

  @Override
  public boolean isSemanticallyValid() {

    for (int i = 0; i < ps.length ; i++) {
      if (!ps[i].isSemanticallyValid()) {
          return false;
      }
    }

    return true;
  }
}
