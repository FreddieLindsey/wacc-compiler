package wacc.ast;

public class IdentNode implements ASTNode {

  private String ident;

  public IdentNode(String ident) {
    this.ident = ident;
  }

  @Override
  public boolean isSemanticallyValid() {
    // TODO Auto-generated method stub
    return false;
  }

}
