package wacc.ast.operator;

import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.assign.AssignNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;
import wacc.backend.instruction.*;

public class BinOpNode extends AssignNode {

  private ExprNode lhs;
  private BinaryOperator op;
  private ExprNode rhs;

  public BinOpNode(BinaryOperator op) {
    super();
    this.op = op;
  }

  public void addLHS(ExprNode e) {
    this.lhs = e;
    e.setParent(this);
  }

  public void addRHS(ExprNode e) {
    this.rhs = e;
    e.setParent(this);
  }

  @Override
  public TypeNode type() {
    switch (op) {
      case MUL:
      case DIV:
      case MOD:
      case ADD:
      case SUB:
        return new TypeNode(TypeEnum.INT);
      case GT:
      case GTE:
      case LT:
      case LTE:
      case EQ:
      case NEQ:
      case AND:
      case OR:
        return new TypeNode(TypeEnum.BOOL);
    }
    return null;
  }

  @Override
  public boolean isSemanticallyValid() {
    TypeNode lhs_type, rhs_type;

    // Check null
    if (lhs == null || rhs == null) return false;

    // Check lhs and rhs are valid
    if (!lhs.isSemanticallyValid()
      || !rhs.isSemanticallyValid()) return false;

    // Check lhs
    lhs_type = (lhs instanceof IdentNode) ?
      symbolTable.lookUp(((IdentNode) lhs).getIdent()) :
      lhs.type();

    // Check rhs
    rhs_type = (rhs instanceof IdentNode) ?
      symbolTable.lookUp(((IdentNode) rhs).getIdent()) :
      rhs.type();

    if (!lhs_type.equals(rhs_type)) return false;

    switch (op) {
      case MUL:
      case DIV:
      case MOD:
      case ADD:
      case SUB:
        semanticallyValid = lhs_type.getType().equals(TypeEnum.INT);
        break;
      case GT:
      case GTE:
      case LT:
      case LTE:
        semanticallyValid = lhs_type.getType().equals(TypeEnum.INT)
          || lhs_type.getType().equals(TypeEnum.CHAR);
        break;
      case EQ:
      case NEQ:
        semanticallyValid = true;
        break;
      case AND:
      case OR:
        semanticallyValid = lhs_type.getType().equals(TypeEnum.BOOL);
    }
    return semanticallyValid;
  }

  public ExprNode getLHS() {
    return lhs;
  }

  public ExprNode getRHS() {
    return rhs;
  }

  @Override
  public boolean validLeft() {
    return false;
  }

  @Override
  public boolean validRight() {
    return true;
  }

  @Override
  public InstructionBlock generateCode(ArrayList<Register> regs) {
    InstructionBlock i = new InstructionBlock();

    ArrayList<Arg> args = new ArrayList<Arg>();
    AssemblyInstr a;

    ArrayList<Register> liveRegs = new ArrayList<Register>();
    liveRegs.addAll(regs);

    if (lhs.weight() > rhs.weight) {
      i.addAll(lhs.generateCode(liveRegs));
      regs.remove(0);
      i.addAll(rhs.generateCode(liveRegs));
    }

    switch (op) {
      case MUL: args.add(regs.get(0));
                args.add(regs.get(1));
                args.add(regs.get(0));
                args.add(regs.get(1));
                break;
      case DIV: break;
      case MOD: break;
      case ADD: break;
      case SUB: break;
      case GT: break;
      case GTE: break;
      case LT: break;
      case LTE: break;
      case EQ: break;
      case NEQ: break;
      case AND: break;
      case OR: break;
      default: 
        args.add(regs.get(0));
        args.add(regs.get(0));
        args.add(regs.get(1));
    }

    //default?
    // args.add(regs.get(0));
    // args.add(regs.get(0));
    // args.add(regs.get(1));

    switch (op) {
      case MUL: 
//   SMULL r4, r5, r4, r5
//   CMP r5, r4, ASR #31
//   BLNE p_throw_overflow_error
            i.add(new AssemblyInstr(AssemblyInstrEnum.SMULL,
            AssemblyInstrCond.NO_CODE, args));

            args = new ArrayList<Arg>();
            args.add(new Register(RegEnum.RO));
            args.add(new Register(RegEnum.R4));
            args.add(new BarrelShift(BarrelShiftEnum.ASR, new Const(31, true)));
            i.add(new AssemblyInstr(AssemblyInstrEnum.CMP,
            AssemblyInstrCond.NO_CODE, args));

            args = new ArrayList<Arg>();
            args.add(new Label("p_throw_overflow_error"));
            i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
            AssemblyInstrCond.NE, args));

            break;
      case DIV: 

//    MOV r0, r4
//    MOV r1, r5
//    BL p_check_divide_by_zero
//    BL __aeabi_idiv
//    MOV r4, r0


            args = new ArrayList<Arg>();
            args.add(new Register(RegEnum.RO));
            args.add(new Register(RegEnum.R4));
            i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.NO_CODE, args));

            args = new ArrayList<Arg>();
            args.add(new Register(RegEnum.R1));
            args.add(new Register(RegEnum.R5));
            i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.NO_CODE, args));

            args = new ArrayList<Arg>();
            args.add(new Label("p_check_divide_by_zero"));
            i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
            AssemblyInstrCond.NO_CODE, args));

            args = new ArrayList<Arg>();
            args.add(new Label("__aeabi_idiv"));
            i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
            AssemblyInstrCond.NO_CODE, args));

            args = new ArrayList<Arg>();
            args.add(new Register(RegEnum.R4));
            args.add(new Register(RegEnum.R0));
            i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.NO_CODE, args));

            break;
      case MOD: 

//    MOV r0, r4
//    MOV r1, r5
//    BL p_check_divide_by_zero
//    BL __aeabi_idivmod
//    MOV r4, r1
      //Apologies for slighty WET code

            args = new ArrayList<Arg>();
            args.add(new Register(RegEnum.RO));
            args.add(new Register(RegEnum.R4));
            i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.NO_CODE, args));

            args = new ArrayList<Arg>();
            args.add(new Register(RegEnum.R1));
            args.add(new Register(RegEnum.R5));
            i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.NO_CODE, args));

            args = new ArrayList<Arg>();
            args.add(new Label("p_check_divide_by_zero"));
            i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
            AssemblyInstrCond.NO_CODE, args));

            args = new ArrayList<Arg>();
            args.add(new Label("__aeabi_idivmod"));
            i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
            AssemblyInstrCond.NO_CODE, args));

            args = new ArrayList<Arg>();
            args.add(new Register(RegEnum.R4));
            args.add(new Register(RegEnum.R1));
            i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.NO_CODE, args));

      break;
      case ADD: 

//   ADDS r4, r4, r5
//   BLVS p_throw_overflow_error

      args = new ArrayList<Arg>();
      args.add(new Register(RegEnum.R4));
      args.add(new Register(RegEnum.R4));
      args.add(new Register(RegEnum.R5));
      i.add(new AssemblyInstr(AssemblyInstrEnum.ADD,
            AssemblyInstrCond.S, args)); 

      args = new ArrayList<Arg>();
      args.add(new Label("p_throw_overflow_error"));
      i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
            AssemblyInstrCond.VS, args)); 

      break;
      case SUB: 

//   SUBS r4, r4, r5
//   BLVS p_throw_overflow_error

      args = new ArrayList<Arg>();
      args.add(new Register(RegEnum.R4));
      args.add(new Register(RegEnum.R4));
      args.add(new Register(RegEnum.R5));
      i.add(new AssemblyInstr(AssemblyInstrEnum.ADD,
            AssemblyInstrCond.S, args)); 

      args = new ArrayList<Arg>();
      args.add(new Label("p_throw_overflow_error"));
      i.add(new AssemblyInstr(AssemblyInstrEnum.ADD,
            AssemblyInstrCond.S, args)); 

      break;
      case GT: 

//    CMP r4, r5
//    MOVGT r4, #1
//    MOVLE r4, #0

      args = new ArrayList<Arg>();
      args.add(new Register(RegEnum.R4));
      args.add(new Register(RegEnum.R5));
      i.add(new AssemblyInstr(AssemblyInstrEnum.CMP,
            AssemblyInstrCond.S, args)); 

      args = new ArrayList<Arg>();
      args.add(new Register(RegEnum.R4));
      args.add(new Const(1, true)));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.GT, args)); 

      args = new ArrayList<Arg>();
      args.add(new Register(RegEnum.R4));
      args.add(new Const(0, true)));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.LE, args)); 

      break;
      case GTE: 

//    CMP r4, r5
//    MOVGE r4, #1
//    MOVLT r4, #0

      args = new ArrayList<Arg>();
      args.add(new Register(RegEnum.R4));
      args.add(new Register(RegEnum.R5));
      i.add(new AssemblyInstr(AssemblyInstrEnum.CMP,
            AssemblyInstrCond.S, args)); 

      args = new ArrayList<Arg>();
      args.add(new Register(RegEnum.R4));
      args.add(new Const(1, true)));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.GE, args)); 

      args = new ArrayList<Arg>();
      args.add(new Register(RegEnum.R4));
      args.add(new Const(0, true)));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.LT, args)); 

      break;
      case LT: break;
      case LTE: break;
      case EQ: break;
      case NEQ: break;
      case AND: i.add(new AssemblyInstr(AssemblyInstrEnum.AND,
            AssemblyInstrCond.NO_CODE, args)); break;
      case OR: break;
    }


  }

  @Override
  public int weight() {
    return lhs.weight() + rhs.weight();
  }

}
