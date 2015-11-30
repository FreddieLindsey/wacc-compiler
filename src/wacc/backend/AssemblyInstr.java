package wacc.backend;

import java.util.ArrayList;

public class AssemblyInstr implements Instruction {


  private AssemblyInstrEnum instrType;
  private AssemblyInstrCond cond;
  private ArrayList<Arg> args;


  public AssemblyInstr(AssemblyInstrEnum instrType, 
                       AssemblyInstrCond cond, 
                       ArrayList<Arg> args) {
    this.instrType = instrType;
    this.cond = cond;
    this.args = args;
  }

  public AssemblyInstrEnum instrType() {
    return instrType;
  }

  public AssemblyInstrCond getCond() {
    return cond;
  }

  public ArrayList<Arg> getArgs() {
    return args;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(instrType.name());

    if (instrType == AssemblyInstrEnum.POP
     || instrType == AssemblyInstrEnum.PUSH) {
      sb.append(" {");
    } else {
      if (cond != AssemblyInstrCond.NO_CODE) {
        // I think push/pop cannot have conditions
        sb.append(cond.name());
      }
      sb.append(" ");
    }

    for (int i = 0; i < args.size(); i++) {
      if (i == args.size()-1) {
        sb.append(args.get(i).toString());
      } else {
        sb.append(args.get(i).toString() + ", ");
      }
    }

    if (instrType == AssemblyInstrEnum.POP
     || instrType == AssemblyInstrEnum.PUSH) {
      sb.append("}");
    }

    return sb.toString();
  }

  private ArrayList<Instruction> getPrintFunc() {
    //p_print_string:
      //PUSH {lr}
      //LDR r1, [r0]
      //ADD r2, r0, #4
      //LDR r0, =msg_1
      //ADD r0, r0, #4
      //BL printf
      //MOV r0, #0
      //BL fflush
      //POP {pc}

    ArrayList<Instruction> instrs = new ArrayList<Instruction>();

    Instruction i = new Label("p_print_string:");
    instrs.add(i);

    ArrayList<Arg> args = new ArrayList<Arg>();
    args.add(new Register(RegEnum.LR));
    i = new AssemblyInstr(AssemblyInstrEnum.PUSH, 
                          AssemblyInstrCond.NO_CODE, args);
    instrs.add(i);

    args = new ArrayList<Arg>();
    args.add(new Register(RegEnum.R1));
    args.add(new MemoryAccess(RegEnum.R0))
    i = new AssemblyInstr(AssemblyInstrEnum.LDR, 
                          AssemblyInstrCond.NO_CODE, args);
    instrs.add(i);

    args = new ArrayList<Arg>();
    args.add(new Register(RegEnum.R2));
    args.add(new Register(RegEnum.R0));
    args.add(new Const(4), true);
    i = new AssemblyInstr(AssemblyInstrEnum.LDR, 
                          AssemblyInstrCond.NO_CODE, args);
    instrs.add(i);


    return null;
  }

  private ArrayList<AssemblyInstr> getPrintlnFunc() {
    return null;
  }

}
