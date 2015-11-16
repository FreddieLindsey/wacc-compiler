package wacc.ast.io;

import wacc.ast.ASTNode;
import wacc.ast.AssignNode;
import wacc.ast.StatNode;

public class ReadNode extends StatNode {

  private AssignNode a;

  public ReadNode(ASTNode parent, AssignNode a) {
    super(parent);
    this.a = a;
    a.setParent(this);
  }

  @Override
  public boolean isSemanticallyValid() {
    return ( a.validLeft()
          && a.isSemanticallyValid());
  }
}
