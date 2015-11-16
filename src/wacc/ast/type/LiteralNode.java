package wacc.ast.type;

import wacc.ast.ExprNode;

public abstract class LiteralNode<T> extends ExprNode {

  protected T value;

  public T getValue() {
    return value;
  }

}
