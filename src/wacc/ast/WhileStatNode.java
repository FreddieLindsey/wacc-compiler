package wacc.ast;

public class WhileStatNode implements StatNode {

  private ExprNode expr;
  private StatNode stat;

  public WhileStatNode(ExprNode expr, StatNode stat) {
    this.expr = expr;
    this.stat = stat;
  }

  @Override
  public boolean isSemanticallyValid() {
    //check expr type is bool & semantically valid
    return stat.isSemanticallyValid()
        && expr.isSemanticallyValid()
        && expr.type() == TypeEnum.BOOL;
  }

}