package wacc.ast;

public class IntNode extends LiteralNode<Long> {

  public IntNode(long value) {
    this.value = value;
  }

  @Override
  public boolean isSemanticallyValid() {
    return value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE;
  }

}