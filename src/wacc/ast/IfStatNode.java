package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class IfStatNode extends StatNode {

  private ExprNode expr;
  private StatNode stat1;
  private StatNode stat2;

  public IfStatNode(ASTNode parent, ExprNode expr, StatNode stat1, StatNode stat2) {
    super(parent);
    this.expr = expr;
    this.stat1 = stat1;
    this.stat2 = stat2;
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
    return expr.isSemanticallyValid()
        && stat1.isSemanticallyValid()
        && stat2.isSemanticallyValid()
        && expr.type() == TypeEnum.BOOL;
  }

}
