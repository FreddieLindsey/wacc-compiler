package wacc.ast;

public class ArrayElemNode implements ASTNode {

  //for array access eg. a[0]
  //TODO: handle out of bounds access problems


  private IdentNode ident;
  private ExprNode expr;
  
  public ArrayElemNode(IdentNode ident, ExprNode expr) {
    this.ident = ident;
    this.expr  = expr;
  }

  @Override
  public boolean isSemanticallyValid() {
    return ident.isSemanticallyValid() && expr.isSemanticallyValid();
  }

}