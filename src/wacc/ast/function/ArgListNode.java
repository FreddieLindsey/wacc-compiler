package wacc.ast.function;

import wacc.ast.ASTNode;
import wacc.ast.ExprNode;

import java.util.ArrayList;

public class ArgListNode extends ASTNode {

  private ArrayList<ExprNode> exprs;

  public ArgListNode() {
    super();
    exprs = new ArrayList<>();
  }

  public ArrayList<ExprNode> getExprs() {
    return this.exprs;
  }

  public void addExpr(ExprNode e) {
    exprs.add(e);
    e.setParent(this);
  }

  @Override
  public boolean isSemanticallyValid() {
    // Check all expressions are valid individually
    for (ExprNode e : exprs) {
      if (!e.isSemanticallyValid()) {
        return false;
      }
    }
    semanticallyValid = true;
    return semanticallyValid;
  }
}
