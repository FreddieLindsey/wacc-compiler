package wacc.ast.type;

import wacc.ast.assign.AssignNode;

public abstract class LiteralNode<T> extends AssignNode {

  protected T value;

  public T getValue() {
    return value;
  }

  @Override
  public boolean validLeft() {
    return false;
  }

  @Override
  public boolean validRight() {
    return true;
  }

  // TODO: abstract method for returning immediate assembly values?

}
