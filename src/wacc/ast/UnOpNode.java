package wacc.ast;

import wacc.ast.type.TypeNode;
import wacc.symbolTable.TypeEnum;

public class UnOpNode extends ExprNode {
  
  private UnaryOperator op;
  private ExprNode expr;

  public UnOpNode(UnaryOperator op, ExprNode expr) {
    super();
    this.op = op;
    this.expr = expr;
    expr.setParent(this);
  }

  @Override
  public TypeNode type() {
    switch (op) {
      case NOT: return new TypeNode(null, TypeEnum.BOOL);
      case NEG: return new TypeNode(null, TypeEnum.INT);
      case LEN: return new TypeNode(null, TypeEnum.INT);
      case ORD: return new TypeNode(null, TypeEnum.INT);
      case CHR: return new TypeNode(null, TypeEnum.CHAR);
      default:
        return null;
    }
  }

  @Override
  public boolean isSemanticallyValid() {
    boolean valid = expr.isSemanticallyValid();

    switch (op) {
      case NOT: return expr.type().equals(
          new TypeNode(null, TypeEnum.BOOL));
      case NEG: return expr.type().equals(
          new TypeNode(null, TypeEnum.INT));
      case LEN: return expr.type().equals(
          new TypeNode(null, TypeEnum.ARR));
      case ORD: return expr.type().equals(
          new TypeNode(null, TypeEnum.CHAR));
      case CHR: return expr.type().equals(
          new TypeNode(null, TypeEnum.INT));
      default:
        System.err.println("Invalid Unary Operator");
    }

    return valid;
  }
  
}
