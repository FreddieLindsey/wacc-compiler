package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class IfStatNode implements StatNode {

  private ExprNode expr;
  private StatNode stat1;
  private StatNode stat2;

  public IfStatNode(ExprNode expr, StatNode stat1, StatNode stat2) {
    this.expr = expr;
    this.stat1 = stat1;
    this.stat2 = stat2;
  }

  @Override
  public boolean isSemanticallyValid() {
    // check expr type is bool & semantically valid
    return expr.isSemanticallyValid()
        && stat1.isSemanticallyValid()
        && stat2.isSemanticallyValid()
        && expr.type() == TypeEnum.BOOL;
  }

}
