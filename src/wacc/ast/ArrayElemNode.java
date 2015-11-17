package wacc.ast;

import wacc.symbolTable.TypeEnum;

import java.util.ArrayList;

public class ArrayElemNode extends AssignNode {

  private IdentNode ident;
  private ArrayList<ExprNode> exprs = new ArrayList<>();

  public ArrayElemNode(ASTNode parent, IdentNode ident) {
    super(parent);
    this.ident = ident;
    ident.setParent(this);
  }

  public TypeEnum type() {
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
      if (!e.isSemanticallyValid() || e.type() != TypeEnum.INT) {
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
