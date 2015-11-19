package wacc.ast.io;

import wacc.ast.ExprNode;
import wacc.ast.StatNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;

public class IfStatNode extends StatNode {

  private ExprNode expr;
  private StatNode stat1;
  private StatNode stat2;

  public IfStatNode(ExprNode expr, StatNode stat1, StatNode stat2) {
    super();
    this.expr = expr;
    this.stat1 = stat1;
    this.stat2 = stat2;
    expr.setParent(this);
    stat1.setParent(this);
    stat2.setParent(this);
  }

  public ExprNode getExpr() {
    return this.expr;
  }

  public StatNode getTrueBranch() {
    return this.stat1;
  }

  public StatNode getFalseBranch() {
    return this.stat2;
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = expr.isSemanticallyValid()
        && stat1.isSemanticallyValid()
        && stat2.isSemanticallyValid()
        && expr.type().getType().equals(TypeEnum.BOOL);
    return semanticallyValid;
  }

  @Override
  public boolean hasReturn() {
    return stat1.hasReturn() && stat2.hasReturn();
  }

}
