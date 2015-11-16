package wacc.ast.io;

import wacc.ast.ASTNode;
import wacc.ast.StatNode;

public class SkipNode extends StatNode {

  public SkipNode(ASTNode parent) {
    super(parent);
  }

  @Override
  public boolean isSemanticallyValid() {
    return true;
  }
}
