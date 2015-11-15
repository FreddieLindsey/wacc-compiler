package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class ArrayElemNode implements ASTNode, AssignLHSNode {

  private IdentNode ident;
  private ExprNode expr;
  
  public ArrayElemNode(IdentNode ident, ExprNode expr) {
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

  public ExprNode getElement() {
    return ident.retrieve().getElement(this.expr);
  }

  @Override
  public boolean isSemanticallyValid() {
    return ident.isSemanticallyValid() 
        && expr.isSemanticallyValid()
        && expr.type() == ident.type();
  }

}
