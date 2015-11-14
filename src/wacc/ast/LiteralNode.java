package wacc.ast;

import wacc.symbolTable.TypeEnum;

public abstract class LiteralNode<T> implements ExprNode {

  protected T value;

  public abstract TypeEnum type();

  // Returns the value held by the literal node
  public T getValue() {
    return value;
  }

}
