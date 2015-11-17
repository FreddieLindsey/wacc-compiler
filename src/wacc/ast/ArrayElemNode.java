package wacc.ast;

import wacc.ast.type.TypeNode;
import wacc.symbolTable.TypeEnum;

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
    return ident.type();
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
    return ident.isSemanticallyValid();
  }

  @Override
  public boolean validLeft() {
    return false;
  }

  @Override
  public boolean validRight() {
    return true;
  }
}
