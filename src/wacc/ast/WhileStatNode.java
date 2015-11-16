package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class WhileStatNode extends StatNode {

  private ExprNode expr;
  private StatNode stat;

  public WhileStatNode(ASTNode parent, ExprNode expr, StatNode stat) {
    super(parent);
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
