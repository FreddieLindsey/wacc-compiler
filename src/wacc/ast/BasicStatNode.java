package wacc.ast;

public class BasicStatNode implements StatNode {

  private ExprNode expr;
  private StatTypeEnum st;

  public BasicStatNode(StatTypeEnum st, ExprNode expr) {
    this.st = st;
    this.expr = expr;
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
      case SKIP: return true;
      case READ:
      case FREE:
      case RETURN:
      case EXIT:
      case PRINT:
      case PRINTLN: return expr.isSemanticallyValid();
      default: return false;
    }
  }

}
