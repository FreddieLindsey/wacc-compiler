package wacc.ast.type;

import wacc.Main;

public class IntNode extends LiteralNode<Long> {

  public IntNode(long value) {
    super();
    this.value = value;
    this.type = new TypeNode(TypeEnum.INT);
    type.setParent(this);
    if (!isSemanticallyValid()) {
      System.exit(Main.SYNTAX_EXIT);
    }
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = value + Integer.MIN_VALUE < 0;
    return semanticallyValid;
  }
}
