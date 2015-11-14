package wacc.ast;

public class ArgListNode implements ASTNode {

  private ExprNode[] exprs;

  public ArgListNode(ExprNode[] exprs) {
    this.exprs = exprs;
  }

  public ExprNode[] getExprs() {
    return this.exprs;
  }

  @Override
  public boolean isSemanticallyValid() {

    for (int i = 0; i < exprs.length; i++) {
      if (!exprs[i].isSemanticallyValid()) {
        return false;
      }
    }

    return true;
  }

}
