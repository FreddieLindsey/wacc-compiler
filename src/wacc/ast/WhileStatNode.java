package wacc.ast;

import wacc.symbolTable.TypeEnum;

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
    return stat.isSemanticallyValid()
        && expr.isSemanticallyValid()
        && expr.type().equals(
          new TypeNode(null, TypeEnum.BOOL));
  }

}
