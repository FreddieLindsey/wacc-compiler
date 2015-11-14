package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class BoolNode extends LiteralNode<Boolean> {

  public BoolNode(boolean value) {
    this.value = value;
  }

  @Override
  public TypeEnum type() {
    return TypeEnum.BOOL;
  }

  @Override
  public boolean isSemanticallyValid() {
    return value != null;
  }

}
