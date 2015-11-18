package wacc.ast.type;

import wacc.ast.ExprNode;

import java.util.ArrayList;

public class ArrayLiteralNode extends LiteralNode<ExprNode> {

  private final ArrayList<ExprNode> exprs;

  public ArrayLiteralNode() {
    super();
    exprs = new ArrayList<>();
    type = new ArrayTypeNode();
  }

  public void addExpr(ExprNode e) {
    exprs.add(e);
    e.setParent(this);

    ArrayTypeNode thisType = (ArrayTypeNode) type;
    if (thisType.type() == null) {
      thisType.setArrayType(e.type().copy());
    }
  }

  public ArrayList<ExprNode> getExprs() {
    return this.exprs;
  }

  @Override
  public boolean isSemanticallyValid() {
    // Ensure types are the same and each expression is valid
    TypeNode thisType = ((ArrayTypeNode) type).type();
    if (thisType == null) return exprs.size() == 0;
    for (ExprNode e : exprs) {
      if (!e.isSemanticallyValid() || !e.type().equals(thisType)) {
        return false;
      }
    }
    return true;
  }
}
