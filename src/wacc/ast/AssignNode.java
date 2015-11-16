package wacc.ast;

public abstract class AssignNode extends ExprNode {

  public abstract boolean validLeft();
  public abstract boolean validRight();

}
