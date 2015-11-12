package wacc.ast;

public class UnOpNode implements ExprNode {
  
  // TODO: UnOpNode's type should be set by checking which operator it holds

  private UnaryOperator op;
  private ExprNode expr;

  public UnOpNode(UnaryOperator op, ExprNode expr) {

    this.op = op;
    this.expr = expr;

  }

  @Override
  public boolean isSemanticallyValid() {
    boolean checkExpr = expr.isSemanticallyValid();

    // TODO: Type checks
    switch (op) {
    case NOT: // expr.type == bool
    case NEG: // expr.type == int
    case LEN: // expr.type == T[]
    case ORD: // expr.type == char 
    case CHR: // expr.type == int
    default:
      System.err.println("Invalid Unary Operator");
    }

    return false;
  }
  
}
