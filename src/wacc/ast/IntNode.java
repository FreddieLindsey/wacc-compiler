package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class IntNode extends LiteralNode<Long> {

  private static final TypeEnum type = TypeEnum.INT;

  public IntNode(long value) {
    this.value = value;
  }

  @Override
  public TypeEnum type() {
    return TypeEnum.INT;
  }

  @Override
  public boolean isSemanticallyValid() {
    return value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE;
  }

}
