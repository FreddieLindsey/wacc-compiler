package wacc.ast;

import wacc.ast.type.TypeNode;
import wacc.symbolTable.TypeEnum;

public class BoolNode extends LiteralNode<Boolean> {

  public BoolNode(boolean value) {
    super();
    this.value = value;
    this.type = new TypeNode(TypeEnum.BOOL);
  }

  @Override
  public boolean isSemanticallyValid() {
    return value != null;
  }
}
