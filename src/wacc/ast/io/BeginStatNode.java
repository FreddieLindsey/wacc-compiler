package wacc.ast.io;

import wacc.ast.type.TypeNode;
import wacc.backend.instruction.Instruction;
import wacc.backend.instruction.InstructionBlock;

import java.util.ArrayList;

public class BeginStatNode extends StatNode {

  private StatNode stat;

  public BeginStatNode() {
    super();
  }

  public void addStat(StatNode s) {
    this.stat = s;
    s.setParent(this);
  }

  @Override
  public boolean isSemanticallyValid() {
    // Check if the stat is valid
    if (!stat.isSemanticallyValid()) return false;

    semanticallyValid = true;
    return semanticallyValid;
  }

  @Override
  public TypeNode returnType() {
    return stat.returnType();
  }

  @Override
  public boolean returns() {
    return stat.returns();
  }

  @Override
  public InstructionBlock generateCode() {
    InstructionBlock i = new InstructionBlock();
    // Scope instruction generates no assembly instructions
    // Return empty list
    return i;
  }

}
