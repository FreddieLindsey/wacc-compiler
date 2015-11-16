package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class UnOpNode extends ExprNode {
  
  private UnaryOperator op;
  private ExprNode expr;

  public UnOpNode(ASTNode parent, UnaryOperator op, ExprNode expr) {
    super(parent);
    this.op = op;
    this.expr = expr;

  }

  @Override
  public TypeEnum type() {
    switch (op) {
      case NOT: return TypeEnum.BOOL;
      case NEG: return TypeEnum.INT;
      case LEN: return TypeEnum.INT;
      case ORD: return TypeEnum.INT;
      case CHR: return TypeEnum.CHAR;
      default:
        return null;
    }
  }

  @Override
  public boolean isSemanticallyValid() {
    boolean valid = expr.isSemanticallyValid();

    switch (op) {
      case NOT: return expr.type() == TypeEnum.BOOL;
      case NEG: return expr.type() == TypeEnum.INT;
      case LEN: return expr.type() == TypeEnum.ARR;
      case ORD: return expr.type() == TypeEnum.CHAR;
      case CHR: return expr.type() == TypeEnum.INT;
      default:
        System.err.println("Invalid Unary Operator");
    }

    return valid;
  }
  
}
