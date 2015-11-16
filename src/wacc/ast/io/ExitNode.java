package wacc.ast.io;

import wacc.ast.ASTNode;
import wacc.ast.ExprNode;
import wacc.ast.StatNode;

public class ExitNode extends StatNode {

  private final ExprNode e;

  public ExitNode(ASTNode parent, ExprNode e) {
    super(parent);
    this.e = e;
    e.setParent(this);
  }

  @Override
  public boolean isSemanticallyValid() {
    return e.isSemanticallyValid();
  }
}
