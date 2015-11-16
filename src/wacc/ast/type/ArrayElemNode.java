package wacc.ast.type;

import wacc.ast.ASTNode;
import wacc.ast.ExprNode;
import wacc.ast.IdentNode;

import java.util.ArrayList;

public class ArrayElemNode extends ExprNode {

  private final ArrayList<ExprNode> es;
  private final IdentNode i;

  public ArrayElemNode(IdentNode i) {
    super();
    this.i = i;
    this.es = new ArrayList<ExprNode>();
    i.setParent(this);
  }

  @Override
  public boolean isSemanticallyValid() {
    return false;
  }

  public void addExpr(ExprNode exprNode) {
    es.add(exprNode);
    exprNode.setParent(this);
  }
}
