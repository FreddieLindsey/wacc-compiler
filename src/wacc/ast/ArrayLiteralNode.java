package wacc.ast;

import wacc.symbolTable.TypeEnum;

import java.util.ArrayList;

public class ArrayLiteralNode extends LiteralNode<ExprNode> {

  private final ArrayList<ExprNode> exprs;

  public ArrayLiteralNode(ASTNode parent, ExprNode[] value) {
    super(parent);
    exprs = new ArrayList<>();
    this.type = new TypeNode(ExprNode[0].type()));
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
    if (exprs.size() <= 0) {
      return true;
    }

    TypeEnum t = exprs.get(0).type();

    for (ExprNode e : exprs) {
      if (!e.isSemanticallyValid() || !e.type().equals(t)) {
        return false;
      }
    }
    return true;
  }
}
