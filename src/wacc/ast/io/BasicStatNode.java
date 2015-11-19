package wacc.ast.io;

import wacc.ast.ExprNode;
import wacc.ast.StatNode;
import wacc.ast.StatTypeEnum;
import wacc.ast.type.TypeEnum;

public class BasicStatNode extends StatNode {

  private ExprNode expr;
  private StatTypeEnum st;

  public BasicStatNode(StatTypeEnum st) {
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
        semanticallyValid = true; break;
      case READ:
      case FREE:
      case RETURN:
      case EXIT:
        semanticallyValid = expr.type().getType().equals(TypeEnum.INT); break;
      case PRINT:
      case PRINTLN:
        semanticallyValid = expr != null && expr.isSemanticallyValid();
    }
    return semanticallyValid;
  }

  @Override
  public boolean hasReturn() {
    return st == StatTypeEnum.RETURN;
  }

}
