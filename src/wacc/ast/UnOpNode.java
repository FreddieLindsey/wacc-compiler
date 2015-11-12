package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class UnOpNode implements ExprNode {
  
  // TODO: UnOpNode's type should be set by checking which operator it holds

  private UnaryOperator op;
  private ExprNode expr;

  public UnOpNode(UnaryOperator op, ExprNode expr) {

    this.op = op;
    this.expr = expr;

  }

  @Override
  public TypeEnum type() {
    return null;
  }

  @Override
  public boolean isSemanticallyValid() {
    boolean valid = expr.isSemanticallyValid();

    // TODO: Type checks
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
