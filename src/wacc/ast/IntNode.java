package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class IntNode extends LiteralNode<Long> {

  public IntNode(ASTNode parent, long value) {
    super(parent);
    this.value = value;
    this.type = TypeEnum.INT;
  }

  @Override
  public boolean isSemanticallyValid() {
    return value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE;
  }
}
