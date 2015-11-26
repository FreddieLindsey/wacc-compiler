package wacc.ast.assign;

import wacc.ast.ExprNode;

public abstract class AssignNode extends ExprNode {

  public abstract boolean validLeft();

  public abstract boolean validRight();


}
