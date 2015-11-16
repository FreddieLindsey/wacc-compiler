package wacc.ast;

public abstract class LiteralNode<T> extends ExprNode {

  protected T value;

  public LiteralNode(ASTNode parent) {
    super(parent);
  }

  public T getValue() {
    return value;
  }

}
