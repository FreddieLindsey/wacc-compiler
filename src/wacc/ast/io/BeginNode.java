package wacc.ast.io;

import wacc.ast.ASTNode;
import wacc.ast.ExprNode;
import wacc.ast.StatNode;

public class BeginNode extends StatNode {

  private final ExprNode e;

  public BeginNode(ASTNode parent, ExprNode e) {
    super(parent);
    this.e = e;
    e.setParent(this);
  }

  @Override
  public boolean isSemanticallyValid() {
    return e.isSemanticallyValid();
  }
}
