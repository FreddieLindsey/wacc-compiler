package wacc.backend.static_methods;

import wacc.backend.*;
import wacc.backend.instruction.AssemblyInstr;
import wacc.backend.instruction.InstructionBlock;
import wacc.backend.instruction.Label;

import java.util.ArrayList;

public class CallableMethods {

  public static InstructionBlock p_print_string(String s) {
    InstructionBlock i = new InstructionBlock("p_print_string");
    ArrayList<Arg> args;

    args = new ArrayList<>();
    args.add(new Register(RegEnum.LR));
    i.add(new AssemblyInstr(AssemblyInstrEnum.PUSH,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R1));
    args.add(new MemoryAccess(new Register(RegEnum.R0)));
    i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R2));
    args.add(new Register(RegEnum.R0));
    args.add(new Const(4, true));
    i.add(new AssemblyInstr(AssemblyInstrEnum.ADD,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R0));
    args.add(new Const(s, false));
    i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R0));
    args.add(new Register(RegEnum.R0));
    args.add(new Const(4, true));
    i.add(new AssemblyInstr(AssemblyInstrEnum.ADD,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Label("printf"));
    i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R0));
    args.add(new Const(0, true));
    i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Label("fflush"));
    i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.PC));
    i.add(new AssemblyInstr(AssemblyInstrEnum.POP,
      AssemblyInstrCond.NO_CODE, args));

    return i;
  }

  public static InstructionBlock p_read_int(String s) {
    InstructionBlock i = new InstructionBlock("p_read_int");
    ArrayList<Arg> args;

    args = new ArrayList<>();
    args.add(new Register(RegEnum.LR));
    i.add(new AssemblyInstr(AssemblyInstrEnum.PUSH,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R1));
    args.add(new Register(RegEnum.R0));
    i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R0));
    args.add(new Const(s, false));
    i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R0));
    args.add(new Register(RegEnum.R0));
    args.add(new Const(4, true));
    i.add(new AssemblyInstr(AssemblyInstrEnum.ADD,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Label("scanf"));
    i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.PC));
    i.add(new AssemblyInstr(AssemblyInstrEnum.POP,
      AssemblyInstrCond.NO_CODE, args));

    return i;
  }

  public static InstructionBlock p_print_string_ln(String s) {
    InstructionBlock i = new InstructionBlock("p_print_ln");
    ArrayList<Arg> args;

    args = new ArrayList<>();
    args.add(new Register(RegEnum.LR));
    i.add(new AssemblyInstr(AssemblyInstrEnum.PUSH,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R0));
    args.add(new Const(s, false));
    i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R0));
    args.add(new Register(RegEnum.R0));
    args.add(new Const(4, true));
    i.add(new AssemblyInstr(AssemblyInstrEnum.ADD,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Const("puts", true));
    i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R0));
    args.add(new Const(0, true));
    i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Const("fflush", true));
    i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.PC));
    i.add(new AssemblyInstr(AssemblyInstrEnum.POP,
      AssemblyInstrCond.NO_CODE, args));

    return i;
  }
}
