package wacc.ast;

import wacc.symbolTable.TypeEnum;

public abstract class ExprNode extends ASTNode {

  protected TypeEnum type;

  public TypeEnum type() {
    return type;
  }

}
