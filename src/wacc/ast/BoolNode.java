package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class BoolNode extends LiteralNode<Boolean> {

  public BoolNode(boolean value) {
    super();
    this.value = value;
    this.type = TypeEnum.BOOL;
  }

  @Override
  public boolean isSemanticallyValid() {
    return value != null;
  }
}
