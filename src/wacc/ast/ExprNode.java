package wacc.ast;

import wacc.symbolTable.TypeEnum;

public interface ExprNode extends ASTNode implements AssignRHSNode {

  public TypeEnum type();

}
