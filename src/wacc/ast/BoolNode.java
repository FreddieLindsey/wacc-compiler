package wacc.ast;

public class BoolNode extends LiteralNode<Boolean> {
  
  public BoolNode(boolean value) {
    this.value = value;
  }

  @Override
  public boolean isSemanticallyValid() {
    return value != null;
  }

}