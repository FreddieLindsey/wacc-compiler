package wacc.ast.type;

import wacc.ast.ExprNode;

import java.util.ArrayList;

public class ArrayLiteralNode extends LiteralNode<ExprNode> {

  private final ArrayList<ExprNode> exprs;

  public ArrayLiteralNode() {
    super();
    exprs = new ArrayList<>();
  }

  public void addExpr(ExprNode e) {
    exprs.add(e);
    e.setParent(this);

    if (this.type == null) {
      type = new TypeNode(new TypeNode(e.type()));
    }
  }

  public ArrayList<ExprNode> getExprs() {
    return this.exprs;
  }

  @Override
  public boolean isSemanticallyValid() {
    if (exprs.size() <= 0) {
      return true;
    }

    for (ExprNode e : exprs) {
      if (!e.isSemanticallyValid() || e.type().equals(type().getArrType())) {
        return false;
      }
    }
    return true;
  }
}
