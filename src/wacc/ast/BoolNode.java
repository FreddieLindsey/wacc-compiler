package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class BoolNode extends LiteralNode<Boolean> {

  private static final TypeEnum type = TypeEnum.BOOL;

  public BoolNode(boolean value) {
    this.value = value;
  }

  @Override
  public boolean isSemanticallyValid() {
    return value != null;
  }

}
