package wacc.ast;

public abstract class LiteralNode<T> implements ASTNode {
  
  protected T value;
  
  // Returns the value held by the literal node
  public T getValue() {
    return value;
  }

}