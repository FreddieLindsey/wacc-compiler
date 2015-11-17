package wacc.ast;

import wacc.ast.type.TypeNode;
import wacc.symbolTable.TypeEnum;

public abstract class ExprNode extends ASTNode {

  protected TypeNode type;

  public TypeNode type() {
    return type;
  }

}
