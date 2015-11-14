package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class ArrayElemNode implements ASTNode, AssignLHSNode {

  //for array access eg. a[0]

  private IdentNode ident;
  private ExprNode expr;
  
  public ArrayElemNode(IdentNode ident, ExprNode expr) {
    this.ident = ident;
    this.expr  = expr;
  }

  public TypeEnum type() {
    //should return primitive type using symbol table to figure out what kind of array it is
    return TypeEnum.ARR;
  }

  public IdentNode getId() {
    return this.ident;
  }

  public ExprNode getExpr() {
    return this.expr;
  }

  @Override
  public boolean isSemanticallyValid() {
    //TODO: handle out of bounds access problems, should this be in the symbol table?
    return ident.isSemanticallyValid() 
        && expr.isSemanticallyValid()
        && expr.type() == TypeEnum.INT;
  }

}