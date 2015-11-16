package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class BoolNode extends LiteralNode<Boolean> {

  public BoolNode(ASTNode parent, boolean value) {
    super(parent);
    this.value = value;
    this.type = TypeEnum.BOOL;
  }

  @Override
  public boolean isSemanticallyValid() {
    return value != null;
  }
}
