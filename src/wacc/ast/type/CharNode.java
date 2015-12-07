package wacc.ast.type;

import java.util.ArrayList;

import wacc.backend.instruction.*;

public class CharNode extends LiteralNode<Character> {

  public CharNode(char value) {
    super();
    this.value = value;
    this.type = new TypeNode(TypeEnum.CHAR);
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = true;
    return semanticallyValid;
  }


  @Override 
  public InstructionBlock generateCode(ArrayList<Register> regs) {

    InstructionBlock i = new InstructionBlock();

    ArrayList<Arg> args;
    AssemblyInstr a;

    args = new ArrayList<>();
    args.add(regs.get(0));
    args.add(new Const(value));
    i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
      AssemblyInstrCond.NO_CODE, args));

    return i;

  }
}
