package wacc.ast.type;

import java.util.ArrayList;

import wacc.backend.instruction.*;

public class BoolNode extends LiteralNode<Boolean> {

  public BoolNode(boolean value) {
    super();
    this.value = value;
    this.type = new TypeNode(TypeEnum.BOOL);
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = value != null;
    return semanticallyValid;
  }

  @Override
  public InstructionBlock generateCode(ArrayList<Register> regs) {
    InstructionBlock i = new InstructionBlock();

    ArrayList<Arg> args;
    AssemblyInstr a;

    args = new ArrayList<>();
    args.add(regs.get(0));
    args.add(new Const(value ? 1 : 0, true));
    i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
      AssemblyInstrCond.NO_CODE, args));

    return i;

  }
}
