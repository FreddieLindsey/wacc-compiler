package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class IntNode extends LiteralNode<Long> {

  public IntNode(long value) {
    super();
    this.value = value;
    this.type = new TypeNode(null, TypeEnum.INT);
  }

  @Override
  public boolean isSemanticallyValid() {
    return value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE;
  }
}
