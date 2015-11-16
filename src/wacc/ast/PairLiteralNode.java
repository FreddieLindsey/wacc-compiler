package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class PairLiteralNode extends LiteralNode {
  
  public PairLiteralNode() {
    super(null);
    this.value = null;
    this.type = TypeEnum.PAIR;
  }

  @Override
  public boolean isSemanticallyValid() {
    return true;
  }
}
