package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class PairLiteralNode extends LiteralNode {
  
  public PairLiteralNode() {
    this.value = null;
  }

  @Override
  public boolean isSemanticallyValid() {
    return true;
  }

  @Override
  public TypeEnum type() {
    return TypeEnum.PAIR;
  }
}
