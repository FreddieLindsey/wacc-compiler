package wacc.ast;

public class ArrayLiteralNode extends LiteralNode<ExprNode[]> {
  
  public ArrayLiteralNode(ExprNode[] value) {
    this.value = value;
  }
  
  @Override
  public boolean isSemanticallyValid() {

    for (int i = 0; i < value.length; ++i) {
      ExprNode n = value[i];
      if (!n.isSemanticallyValid()) {
        return false;
      }
    }
    return true; 
  }

}