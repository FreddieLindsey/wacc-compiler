package wacc.ast;

import wacc.ast.type.TypeNode;
<<<<<<< 23b3de1d77ceccd2e220958f0beada9c5a8d6713
import wacc.backend.instruction.*;
=======
import wacc.backend.instruction.AssemblyInstr;
import wacc.backend.instruction.InstructionBlock;
>>>>>>> Corrected for InstructionBlock


import java.util.ArrayList;

public abstract class ExprNode extends ASTNode {

  protected TypeNode type;

  public TypeNode type() {
    return type;
  }

  public InstructionBlock generateCode() {
    return null;
  }

  public int weight() {
  	//useful for calculating which side to eval first in BinOp
  	return 1;
  }

}
