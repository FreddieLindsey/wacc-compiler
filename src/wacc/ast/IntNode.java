package wacc.ast;

import wacc.ast.type.TypeNode;
import wacc.symbolTable.TypeEnum;

public class IntNode extends LiteralNode<Long> {

  public IntNode(long value) {
    super();
    this.value = value;
    this.type = new TypeNode(TypeEnum.INT);
    type.setParent(this);
  }

  @Override
  public boolean isSemanticallyValid() {
    return value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE;
  }
}
