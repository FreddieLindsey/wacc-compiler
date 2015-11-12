package wacc.ast;

public class UnOpNode implements ExprNode {

  private BinaryOperator op;
  private ExprNode expr;

  public UnOpNode(BinaryOperator op, ExprNode expr) {

    this.op = op;
    this.expr = expr;

  }


  @Override
  public boolean isSemanticallyValid() {
    //TODO: check that this matching type can have the operator applied to it
    return expr.isSemanticallyValid();
  }

}