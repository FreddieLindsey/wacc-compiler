package wacc.ast.pair;

import wacc.ast.type.AnyTypeNode;
import wacc.ast.type.LiteralNode;
import wacc.ast.type.PairTypeNode;

public class PairLiteralNode extends LiteralNode {

  public PairLiteralNode() {
    super();
    this.value = null;
    this.type = new PairTypeNode(new AnyTypeNode(), new AnyTypeNode());
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = true;
    return semanticallyValid;
  }
}
