package wacc.ast.io;

import wacc.ast.ExprNode;
import wacc.ast.StatNode;
import wacc.ast.StatTypeEnum;
import wacc.ast.type.PairTypeNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;

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
        semanticallyValid = true; return semanticallyValid;
      case EXIT:
        semanticallyValid = expr.type().getType().equals(TypeEnum.INT); break;
      case FREE:
        semanticallyValid = expr.type() instanceof PairTypeNode; break;
      case READ:
        semanticallyValid = !(expr.type() instanceof PairTypeNode); break;
      default:
        semanticallyValid = true;
    }
    semanticallyValid &= expr != null && expr.isSemanticallyValid();
    return semanticallyValid;
  }

  @Override
  public TypeNode returnType() {
    return (st == StatTypeEnum.RETURN) ? expr.type() : null;
  }

}
