package wacc.ast.type;

public class IntNode extends LiteralNode<Long> {

  public IntNode(long value) {
    super();
    this.value = value;
    this.type = new TypeNode(TypeEnum.INT);
    type.setParent(this);
    if (!isSemanticallyValid()) {
      System.out.println("Not a valid 32 bit integer");
    }
  }

  @Override
  public boolean isSemanticallyValid() {
    return value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE;
  }
}
