package wacc.ast.operator;

import java.util.ArrayList;

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

<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
    if (lhs.weight() > rhs.weight()) {
=======
    if (lhs.weight() > rhs.weight) {
>>>>>>> so much stuff
      i.addAll(lhs.generateCode(liveRegs));
      regs.remove(0);
      i.addAll(rhs.generateCode(liveRegs));
    }

<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d

    // TODO : generalise registers 
=======
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

>>>>>>> so much stuff
    switch (op) {
      case MUL: 
//   SMULL r4, r5, r4, r5
//   CMP r5, r4, ASR #31
//   BLNE p_throw_overflow_error
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d

            args.add(regs.get(0));
            args.add(regs.get(1));
            args.add(regs.get(0));
            args.add(regs.get(1));
=======
>>>>>>> so much stuff
            i.add(new AssemblyInstr(AssemblyInstrEnum.SMULL,
            AssemblyInstrCond.NO_CODE, args));

            args = new ArrayList<Arg>();
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
            args.add(regs.get(1));
            args.add(regs.get(0));
=======
            args.add(new Register(RegEnum.RO));
            args.add(new Register(RegEnum.R4));
>>>>>>> so much stuff
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
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
            args.add(new Register(RegEnum.R0));
            args.add(regs.get(0));
=======
            args.add(new Register(RegEnum.RO));
            args.add(new Register(RegEnum.R4));
>>>>>>> so much stuff
            i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.NO_CODE, args));

            args = new ArrayList<Arg>();
            args.add(new Register(RegEnum.R1));
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
            args.add(regs.get(1));
=======
            args.add(new Register(RegEnum.R5));
>>>>>>> so much stuff
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
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
            args.add(regs.get(0));
=======
            args.add(new Register(RegEnum.R4));
>>>>>>> so much stuff
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
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
            args.add(new Register(RegEnum.R0));
            args.add(regs.get(0));
=======
            args.add(new Register(RegEnum.RO));
            args.add(new Register(RegEnum.R4));
>>>>>>> so much stuff
            i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.NO_CODE, args));

            args = new ArrayList<Arg>();
            args.add(new Register(RegEnum.R1));
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
            args.add(regs.get(1));
=======
            args.add(new Register(RegEnum.R5));
>>>>>>> so much stuff
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
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
            args.add(regs.get(0));
=======
            args.add(new Register(RegEnum.R4));
>>>>>>> so much stuff
            args.add(new Register(RegEnum.R1));
            i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.NO_CODE, args));

      break;
      case ADD: 

//   ADDS r4, r4, r5
//   BLVS p_throw_overflow_error

      args = new ArrayList<Arg>();
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
      args.add(regs.get(0));
      args.add(regs.get(0));
      args.add(regs.get(1));
=======
      args.add(new Register(RegEnum.R4));
      args.add(new Register(RegEnum.R4));
      args.add(new Register(RegEnum.R5));
>>>>>>> so much stuff
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
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
      args.add(regs.get(0));
      args.add(regs.get(0));
      args.add(regs.get(1));
      i.add(new AssemblyInstr(AssemblyInstrEnum.SUB,
=======
      args.add(new Register(RegEnum.R4));
      args.add(new Register(RegEnum.R4));
      args.add(new Register(RegEnum.R5));
      i.add(new AssemblyInstr(AssemblyInstrEnum.ADD,
>>>>>>> so much stuff
            AssemblyInstrCond.S, args)); 

      args = new ArrayList<Arg>();
      args.add(new Label("p_throw_overflow_error"));
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
      i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
            AssemblyInstrCond.VS, args)); 
=======
      i.add(new AssemblyInstr(AssemblyInstrEnum.ADD,
            AssemblyInstrCond.S, args)); 
>>>>>>> so much stuff

      break;
      case GT: 

//    CMP r4, r5
//    MOVGT r4, #1
//    MOVLE r4, #0

      args = new ArrayList<Arg>();
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
      args.add(regs.get(0));
      args.add(regs.get(1));
      i.add(new AssemblyInstr(AssemblyInstrEnum.CMP,
            AssemblyInstrCond.NO_CODE, args)); 

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(new Const(1, true));
=======
      args.add(new Register(RegEnum.R4));
      args.add(new Register(RegEnum.R5));
      i.add(new AssemblyInstr(AssemblyInstrEnum.CMP,
            AssemblyInstrCond.S, args)); 

      args = new ArrayList<Arg>();
      args.add(new Register(RegEnum.R4));
      args.add(new Const(1, true)));
>>>>>>> so much stuff
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.GT, args)); 

      args = new ArrayList<Arg>();
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
      args.add(regs.get(0));
      args.add(new Const(0, true));
=======
      args.add(new Register(RegEnum.R4));
      args.add(new Const(0, true)));
>>>>>>> so much stuff
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.LE, args)); 

      break;
      case GTE: 

//    CMP r4, r5
//    MOVGE r4, #1
//    MOVLT r4, #0

      args = new ArrayList<Arg>();
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
      args.add(regs.get(0));
      args.add(regs.get(1));
      i.add(new AssemblyInstr(AssemblyInstrEnum.CMP,
            AssemblyInstrCond.NO_CODE, args)); 

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(new Const(1, true));
=======
      args.add(new Register(RegEnum.R4));
      args.add(new Register(RegEnum.R5));
      i.add(new AssemblyInstr(AssemblyInstrEnum.CMP,
            AssemblyInstrCond.S, args)); 

      args = new ArrayList<Arg>();
      args.add(new Register(RegEnum.R4));
      args.add(new Const(1, true)));
>>>>>>> so much stuff
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.GE, args)); 

      args = new ArrayList<Arg>();
<<<<<<< 70611034c79e7f3d128f3d8311c454e087633f3d
      args.add(regs.get(0));
      args.add(new Const(0, true));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.LT, args)); 

      break;
      case LT: 

//    CMP r4, r5
//    MOVLT r4, #1
//    MOVGE r4, #0

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(regs.get(1));
      i.add(new AssemblyInstr(AssemblyInstrEnum.CMP,
            AssemblyInstrCond.NO_CODE, args)); 

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(new Const(1, true));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.LT, args)); 

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(new Const(0, true));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.GE, args)); 

      break;
      case LTE: 

//    CMP r4, r5
//    MOVLE r4, #1
//    MOVGT r4, #0

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(regs.get(1));
      i.add(new AssemblyInstr(AssemblyInstrEnum.CMP,
            AssemblyInstrCond.NO_CODE, args)); 

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(new Const(1, true));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.LE, args)); 

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(new Const(0, true));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.GT, args)); 

      break;
      case EQ: 

      if (lhs.type().equals(new TypeNode(TypeEnum.ARR))
       || lhs.type().equals(new TypeNode(TypeEnum.PAIR))) {
//      LDR r4, [sp, #5]
//      LDR r5, [sp, #1]

        ArrayList<Arg> args2;

        args = new ArrayList<Arg>();
        args.add(regs.get(0));
        args2 = new ArrayList<Arg>();
        args2.add(new Register(RegEnum.SP));
        args2.add(new Const(5, true)); // needs generalising?
        args.add(new MemoryAccess(args2));
        i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
              AssemblyInstrCond.NO_CODE, args));    


        args = new ArrayList<Arg>();
        args.add(regs.get(1));
        args2 = new ArrayList<Arg>();
        args2.add(new Register(RegEnum.SP));
        args2.add(new Const(1, true)); // needs generalising?
        args.add(new MemoryAccess(args2));
        i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
              AssemblyInstrCond.NO_CODE, args));
        

      }

//   CMP r4, r5
//   MOVEQ r4, #1
//   MOVNE r4, #0

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(regs.get(1));
      i.add(new AssemblyInstr(AssemblyInstrEnum.CMP,
            AssemblyInstrCond.NO_CODE, args)); 


      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(new Const(1, true));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.EQ, args)); 

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(new Const(0, true));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.NE, args)); 


      break;
      case NEQ: 

      if (lhs.type().equals(new TypeNode(TypeEnum.ARR))
       || lhs.type().equals(new TypeNode(TypeEnum.PAIR))) {
//      LDR r4, [sp, #5]
//      LDR r5, [sp, #1]

        ArrayList<Arg> args2;

        args = new ArrayList<Arg>();
        args.add(regs.get(0));
        args2 = new ArrayList<Arg>();
        args2.add(new Register(RegEnum.SP));
        args2.add(new Const(5, true)); // needs generalising?
        args.add(new MemoryAccess(args2));
        i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
              AssemblyInstrCond.NO_CODE, args));    


        args = new ArrayList<Arg>();
        args.add(regs.get(1));
        args2 = new ArrayList<Arg>();
        args2.add(new Register(RegEnum.SP));
        args2.add(new Const(1, true)); // needs generalising?
        args.add(new MemoryAccess(args2));
        i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
              AssemblyInstrCond.NO_CODE, args));
          

      }

//    CMP r4, r5
//    MOVNE r4, #1
//    MOVEQ r4, #0

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(regs.get(1));
      i.add(new AssemblyInstr(AssemblyInstrEnum.CMP,
            AssemblyInstrCond.NO_CODE, args)); 

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(new Const(1, true));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.NE, args)); 

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(new Const(0, true));
      i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
            AssemblyInstrCond.EQ, args)); 

      break;
      case AND:       
//    AND r4, r4, r5

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(regs.get(0));
      args.add(regs.get(1));
      i.add(new AssemblyInstr(AssemblyInstrEnum.AND,
            AssemblyInstrCond.NO_CODE, args));  break;
      case OR: 
//    ORR r4, r4, r5

      args = new ArrayList<Arg>();
      args.add(regs.get(0));
      args.add(regs.get(0));
      args.add(regs.get(1));
      i.add(new AssemblyInstr(AssemblyInstrEnum.ORR,
            AssemblyInstrCond.NO_CODE, args)); 

      break;
    }

    return i;
=======
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


>>>>>>> so much stuff
  }

  @Override
  public int weight() {
    return lhs.weight() + rhs.weight();
  }

}
