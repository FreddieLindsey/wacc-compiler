package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class ArrayLiteralNode extends LiteralNode<ExprNode[]> {
  
  public ArrayLiteralNode(ASTNode parent, ExprNode[] value) {
    super(parent);
    this.value = value;
    this.type = TypeEnum.ARR;
  }
  
  @Override
  public boolean isSemanticallyValid() {
    for (ExprNode n : value) {
      if (!n.isSemanticallyValid()) {
        return false;
      }
    }
    return true; 
  }
}
