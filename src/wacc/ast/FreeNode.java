package wacc.ast;

public class FreeNode extends StatNode {

  private final ExprNode e;

  public FreeNode(ASTNode parent, ExprNode e) {
    super(parent);
    this.e = e;
    e.setParent(this);
  }

  @Override
  public boolean isSemanticallyValid() {
    return e.isSemanticallyValid();
  }
}
