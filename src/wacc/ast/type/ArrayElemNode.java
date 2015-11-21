package wacc.ast.type;

import wacc.ast.AssignNode;
import wacc.ast.ExprNode;
import wacc.ast.IdentNode;

import java.util.ArrayList;

public class ArrayElemNode extends AssignNode {

  private IdentNode ident;
  private ArrayList<ExprNode> exprs = new ArrayList<>();

  public ArrayElemNode(IdentNode ident) {
    super();
    this.ident = ident;
    ident.setParent(this);
  }

  public TypeNode type() {
    return ((ArrayTypeNode) ident.type()).type();
  }

  public IdentNode getId() {
    return this.ident;
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
      if (!e.isSemanticallyValid() || !e.type().equals(new TypeNode(TypeEnum.INT))) {
        return false;
      }
    }
    semanticallyValid = ident.isSemanticallyValid();
    return semanticallyValid;
  }

  @Override
  public boolean validLeft() {
    return true;
  }

  @Override
  public boolean validRight() {
    return true;
  }
}
