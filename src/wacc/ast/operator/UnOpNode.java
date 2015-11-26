package wacc.ast.operator;

import wacc.ast.ExprNode;
import wacc.ast.assign.AssignNode;
import wacc.ast.type.ArrayTypeNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;

public class UnOpNode extends AssignNode {

  private UnaryOperator op;
  private ExprNode expr;

  public UnOpNode(UnaryOperator op) {
    super();
    this.op = op;
  }

  @Override
  public TypeNode type() {
    switch (op) {
      case NOT:
        return new TypeNode(TypeEnum.BOOL);
      case NEG:
        return new TypeNode(TypeEnum.INT);
      case LEN:
        return new TypeNode(TypeEnum.INT);
      case ORD:
        return new TypeNode(TypeEnum.INT);
      case CHR:
        return new TypeNode(TypeEnum.CHAR);
      default:
        return null;
    }
  }

  @Override
  public boolean isSemanticallyValid() {
    if (!expr.isSemanticallyValid()) return false;

    switch (op) {
      case NOT:
        semanticallyValid = expr.type().getType().equals(TypeEnum.BOOL);
        break;
      case NEG:
        semanticallyValid = expr.type().getType().equals(TypeEnum.INT);
        break;
      case LEN:
        semanticallyValid = expr.type() instanceof ArrayTypeNode;
        break;
      case ORD:
        semanticallyValid = expr.type().getType().equals(TypeEnum.CHAR);
        break;
      case CHR:
        semanticallyValid = expr.type().getType().equals(TypeEnum.INT);
    }
    return semanticallyValid;
  }

  public void setExpr(ExprNode expr) {
    this.expr = expr;
    expr.setParent(this);
  }

  @Override
  public boolean validLeft() {
    return false;
  }

  @Override
  public boolean validRight() {
    return true;
  }
}
