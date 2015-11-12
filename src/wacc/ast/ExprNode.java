package wacc.ast;

import wacc.symbolTable.TypeEnum;

public interface ExprNode extends ASTNode {

  public TypeEnum type();

}
