package wacc.ast;

import wacc.ast.type.TypeNode;
import wacc.backend.instruction.*;

import java.util.ArrayList;

public abstract class ExprNode extends ASTNode {

  protected TypeNode type;

  public TypeNode type() {
    return type;
  }

  public InstructionBlock generateCode(ArrayList<Register> regs) {
    return null;
  }

  public int weight() {
  	//useful for calculating which side to eval first in BinOp
  	return 1;
  }

}
