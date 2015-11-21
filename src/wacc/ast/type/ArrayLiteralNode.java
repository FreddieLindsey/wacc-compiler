package wacc.ast.type;

import wacc.ast.ExprNode;
import wacc.ast.IdentNode;

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
  }

  public ArrayList<ExprNode> getExprs() {
    return this.exprs;
  }

  @Override
  public boolean isSemanticallyValid() {
    ArrayTypeNode thisType = (ArrayTypeNode) type;
    if (exprs.size() == 0) return true;

    ExprNode firstElement = exprs.get(0);
    if (firstElement.type() == null && firstElement instanceof IdentNode) {
      thisType.setArrayType(symbolTable.lookUp(((IdentNode) firstElement).getIdent()));
    } else {
      thisType.setArrayType(firstElement.type().copy());
    }

    for (ExprNode e : exprs) {
      if (!e.isSemanticallyValid() || !e.type().equals(thisType.type())) {
        return false;
      }
    }
    semanticallyValid = true;
    return semanticallyValid;
  }
}
