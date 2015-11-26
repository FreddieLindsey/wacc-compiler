package wacc.ast;

import wacc.ast.type.TypeNode;
import wacc.backend.*;

import java.util.ArrayList;

public abstract class ExprNode extends ASTNode {

  protected TypeNode type;

  public TypeNode type() {
    return type;
  }

  public ArrayList<AssemblyInstr> generateCode() {
  	return null;
  }

}
