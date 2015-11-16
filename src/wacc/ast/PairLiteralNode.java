package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class PairLiteralNode extends LiteralNode {
  
  public PairLiteralNode(ASTNode parent) {
    super(parent);
    this.value = null;
    this.type = TypeEnum.PAIR;
  }

  @Override
  public boolean isSemanticallyValid() {
    return true;
  }
}
