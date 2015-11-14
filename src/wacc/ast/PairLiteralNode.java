package wacc.ast;

public class PairLiteralNode extends LiteralNode {
  
  public PairLiteralNode() {
    this.value = null;
  }

  @Override
  public boolean isSemanticallyValid() {
    return true;
  }

}
