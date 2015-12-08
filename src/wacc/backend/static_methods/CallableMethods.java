package wacc.backend.static_methods;

import wacc.backend.instruction.*;

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

  public static InstructionBlock p_print_int(String s) {

//  p_print_int:
//    PUSH {lr}
//    MOV r1, r0
//    LDR r0, =s (<- .word 3 .ascii "%d\0")
//    ADD r0, r0, #4
//    BL printf
//    MOV r0, #0
//    BL fflush
//    POP {pc}

    InstructionBlock i = new InstructionBlock("p_print_int");
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

  public static InstructionBlock p_check_divide_by_zero(String s){
//     p_check_divide_by_zero:
// 28    PUSH {lr}
// 29    CMP r1, #0
// 30    LDREQ r0, =msg_0
// 31    BLEQ p_throw_runtime_error
// 32    POP {pc}

    InstructionBlock i = new InstructionBlock("p_check_divide_by_zero");
    ArrayList<Arg> args;

    args = new ArrayList<>();
    args.add(new Register(RegEnum.LR));
    i.add(new AssemblyInstr(AssemblyInstrEnum.PUSH,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R0));
    args.add(new Const(0, true));
    i.add(new AssemblyInstr(AssemblyInstrEnum.CMP,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R0));
    args.add(new Label(s));
    i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
      AssemblyInstrCond.EQ, args));

    args = new ArrayList<>();
    args.add(new Label("p_throw_runtime_error"));
    i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
      AssemblyInstrCond.EQ, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.PC));
    i.add(new AssemblyInstr(AssemblyInstrEnum.POP,
      AssemblyInstrCond.NO_CODE, args));

    return i;
  }

  public static InstructionBlock p_throw_overflow_error(String s) {
//     #25 p_throw_overflow_error:
// #26   LDR r0, =msg_0
// #27   BL p_throw_runtime_error

    InstructionBlock i = new InstructionBlock("p_check_divide_by_zero");
    ArrayList<Arg> args;

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R0));
    args.add(new Label(s));
    i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Label("p_throw_runtime_error"));
    i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
      AssemblyInstrCond.NO_CODE, args));

    return i;

  }

  public static InstructionBlock p_throw_runtime_error(String s) {
// #27 p_throw_runtime_error:
// #28   BL p_print_string
// #29   MOV r0, #-1
// #30   BL exit

    InstructionBlock i = new InstructionBlock("p_throw_runtime_error");
    ArrayList<Arg> args;

    args = new ArrayList<>();
    args.add(new Label("p_print_string"));
    i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Register(RegEnum.R0));
    args.add(new Const(-1, true));
    i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
      AssemblyInstrCond.NO_CODE, args));

    args = new ArrayList<>();
    args.add(new Label("exit"));
    i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
      AssemblyInstrCond.NO_CODE, args));

    return i;

  }
}
