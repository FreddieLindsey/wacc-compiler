package wacc.ast.io;

import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.StatNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;

public class WhileStatNode extends StatNode {

  private ExprNode expr;
  private StatNode stat;

  public WhileStatNode(ExprNode expr, StatNode stat) {
    super();
    this.expr = expr;
    this.stat = stat;
    expr.setParent(this);
    stat.setParent(this);
  }

  public ExprNode getExpr() {
    return this.expr;
  }

  public StatNode getStat() {
    return this.stat;
  }

  @Override
  public boolean isSemanticallyValid() {
    if (expr instanceof IdentNode &&
      symbolTable.lookUp(((IdentNode) expr).getIdent()) == null) return false;

    semanticallyValid = stat.isSemanticallyValid()
      && expr.isSemanticallyValid()
      && expr.type().getType().equals(TypeEnum.BOOL);
    return semanticallyValid;
  }

  @Override
  public TypeNode hasReturn() {
    return stat.hasReturn();
  }

}
