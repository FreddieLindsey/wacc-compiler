package wacc.ast;

public class ArgListNode extends ASTNode {

  private ExprNode[] exprs;

  public ArgListNode(ASTNode parent, ExprNode[] exprs) {
    super(parent);
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
