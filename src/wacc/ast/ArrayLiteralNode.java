package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class ArrayLiteralNode extends LiteralNode<ExprNode[]> {
  
  public ArrayLiteralNode(ExprNode[] value) {
    this.value = value;
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

  @Override
  public TypeEnum type() {
    return TypeEnum.ARR;
  }
}
