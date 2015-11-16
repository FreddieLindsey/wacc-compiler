package wacc.ast;

public abstract class AssignNode extends ExprNode {

  public AssignNode(ASTNode parent) {
    super(parent);
  }

  public abstract boolean validLeft();
  public abstract boolean validRight();

}
