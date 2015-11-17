package wacc.ast;

public class BasicStatNode extends StatNode {

  private ExprNode expr;
  private StatTypeEnum st;

  public BasicStatNode(StatTypeEnum st, ExprNode expr) {
    super();
    this.st = st;
  }

  public void addExpr(ExprNode e) {
    this.expr = e;
    e.setParent(this);
  }

  public StatTypeEnum getType() {
    return st;
  }

  public ExprNode getExpr() {
    return expr;
  }

  @Override
  public boolean isSemanticallyValid() {
    switch (st) {
      case SKIP:
        return true;
      case READ:
      case FREE:
      case RETURN:
      case EXIT:
      case PRINT:
      case PRINTLN: 
        return expr != null && expr.isSemanticallyValid();
    }
    return false;
  }

}
