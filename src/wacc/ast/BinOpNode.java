package wacc.ast;

public class BinOpNode implements ExprNode {
	
  private ExprNode lhs;
  private BinaryOperator op;
  private ExprNode rhs;

  public BinOpNode(ExprNode lhs, BinaryOperator op, ExprNode rhs) {

    this.lhs = lhs;
    this.op  = op;
    this.rhs = rhs;

  }

  @Override
  public boolean isSemanticallyValid() {
    //TODO: check lhs and rhs collpase down to same type
    //TODO: check that these matching types can have the operator applied to them
    return lhs.isSemanticallyValid()
        && rhs.isSemanticallyValid();
  }

}
