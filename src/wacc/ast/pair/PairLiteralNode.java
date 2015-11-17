package wacc.ast.pair;

import wacc.ast.type.TypeNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.LiteralNode;

public class PairLiteralNode extends LiteralNode {
  
  public PairLiteralNode() {
    super();
    this.value = null;
    this.type = new TypeNode(TypeEnum.PAIR);
  }

  @Override
  public boolean isSemanticallyValid() {
    return true;
  }
}
