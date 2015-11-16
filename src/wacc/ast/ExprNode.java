package wacc.ast;

public abstract class ExprNode extends ASTNode {

  protected TypeNode type;

  public TypeNode type() {
    return type;
  }

}
