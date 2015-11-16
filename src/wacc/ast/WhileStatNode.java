package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class WhileStatNode implements StatNode {

  private ExprNode expr;
  private StatNode stat;

  public WhileStatNode(ExprNode expr, StatNode stat) {
    this.expr = expr;
    this.stat = stat;
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
        && expr.type() == TypeEnum.BOOL;
  }

}