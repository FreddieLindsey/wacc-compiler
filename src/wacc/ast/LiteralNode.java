package wacc.ast;

public abstract class LiteralNode<T> implements ASTNode, ExprNode {
  
  protected T value;
  
  // Returns the value held by the literal node
  public T getValue() {
    return value;
  }

}
