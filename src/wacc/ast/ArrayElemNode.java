package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class ArrayElemNode extends AssignNode {

  private IdentNode ident;
  private ExprNode expr;
  
  public ArrayElemNode(ASTNode parent, IdentNode ident, ExprNode expr) {
    super(parent);
    this.ident = ident;
    this.expr  = expr;
  }

  public TypeEnum type() {
    return ident.type();
  }

  public IdentNode getId() {
    return this.ident;
  }

  public ExprNode getExpr() {
    return this.expr;
  }

  @Override
  public boolean isSemanticallyValid() {
    return ident.isSemanticallyValid() 
        && expr.isSemanticallyValid()
        && expr.type() == ident.type();
  }

  @Override
  public boolean validLeft() {
    return false;
  }

  @Override
  public boolean validRight() {
    return true;
  }
}
