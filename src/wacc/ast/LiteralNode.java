package wacc.ast;

public abstract class LiteralNode<T> extends ExprNode {

  protected T value;

  public T getValue() {
    return value;
  }

}
