package wacc.ast.assign;

import java.util.ArrayList;

import wacc.ast.ExprNode;

import wacc.backend.instruction.*;

public abstract class AssignNode extends ExprNode {

  public abstract boolean validLeft();

  public abstract boolean validRight();

}
