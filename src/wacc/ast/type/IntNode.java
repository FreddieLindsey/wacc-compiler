package wacc.ast.type;

import wacc.Main;
import wacc.backend.instruction.*;

import java.util.ArrayList;

public class IntNode extends LiteralNode<Long> {

  public IntNode(long value) {
    super();
    this.value = value;
    this.type = new TypeNode(TypeEnum.INT);
    type.setParent(this);
    if (!isSemanticallyValid()) {
      System.exit(Main.SYNTAX_EXIT);
    }
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = value + Integer.MIN_VALUE <= 0;
    return semanticallyValid;
  }

  @Override
  public InstructionBlock generateCode(ArrayList<Register> regs) {
    InstructionBlock i = new InstructionBlock();

    ArrayList<Arg> args;
    AssemblyInstr a;

    Long l = value;

    args = new ArrayList<>();
    args.add(regs.get(0));
    args.add(new Const(l.intValue(), false));

    i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
      AssemblyInstrCond.NO_CODE, args));

    return i;

  }

}
