package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class PairLiteralNode extends LiteralNode {
  
  public PairLiteralNode() {
    super();
    this.value = null;
    this.type = new TypeNode(null, TypeEnum.PAIR);
  }

  @Override
  public boolean isSemanticallyValid() {
    return true;
  }
}
