package wacc.ast;

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

    for (ExprNode e : exprs) {
      if (!e.isSemanticallyValid()) {
        return false;
      }
    }

    return true;
  }

  public void setExprs(ExprNode[] exprs) {
    this.exprs = exprs;
  }
}
