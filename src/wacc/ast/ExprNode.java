package wacc.ast;

import wacc.ast.type.TypeNode;

public abstract class ExprNode extends ASTNode {

  protected TypeNode type;

  public TypeNode type() {
    return type;
  }

}
