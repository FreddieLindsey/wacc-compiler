package wacc.ast.operator;

import java.util.ArrayList;

import wacc.ast.ExprNode;
import wacc.ast.assign.AssignNode;
import wacc.ast.type.ArrayTypeNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;

import wacc.backend.instruction.*;

public class UnOpNode extends AssignNode {

  private UnaryOperator op;
  private ExprNode expr;

  public UnOpNode(UnaryOperator op) {
    super();
    this.op = op;
  }

  @Override
  public TypeNode type() {
    switch (op) {
      case NOT:
        return new TypeNode(TypeEnum.BOOL);
      case NEG:
        return new TypeNode(TypeEnum.INT);
      case LEN:
        return new TypeNode(TypeEnum.INT);
      case ORD:
        return new TypeNode(TypeEnum.INT);
      case CHR:
        return new TypeNode(TypeEnum.CHAR);
      default:
        return null;
    }
  }

  @Override
  public boolean isSemanticallyValid() {
    if (!expr.isSemanticallyValid()) return false;

    switch (op) {
      case NOT:
        semanticallyValid = expr.type().getType().equals(TypeEnum.BOOL);
        break;
      case NEG:
        semanticallyValid = expr.type().getType().equals(TypeEnum.INT);
        break;
      case LEN:
        semanticallyValid = expr.type() instanceof ArrayTypeNode;
        break;
      case ORD:
        semanticallyValid = expr.type().getType().equals(TypeEnum.CHAR);
        break;
      case CHR:
        semanticallyValid = expr.type().getType().equals(TypeEnum.INT);
    }
    return semanticallyValid;
  }

  public void setExpr(ExprNode expr) {
    this.expr = expr;
    expr.setParent(this);
  }

  @Override
  public boolean validLeft() {
    return false;
  }

  @Override
  public boolean validRight() {
    return true;
  }

  public UnaryOperator getOperator() {
    return op;
  }

  public ExprNode getExpr() {
    return expr;
  }

  
  public InstructionBlock generateCode(ArrayList<Register> regs) {
    InstructionBlock i = new InstructionBlock();

    ArrayList<Arg> args;
    ArrayList<Arg> args2;
    AssemblyInstr a;

    i.addAll(expr.generateCode(regs)); //leaves result of expr in r4

    switch(op) {
      case NOT:
//    EOR r4, r4, #1

        args = new ArrayList<Arg>();
        args.add(regs.get(0));
        args.add(regs.get(0));
        args.add(new Const(1, true));
        i.add(new AssemblyInstr(AssemblyInstrEnum.EOR,
          AssemblyInstrCond.NO_CODE, args));


        break;
      case NEG:
//    RSBS r4, r4, #0
//    BLVS p_throw_overflow_error

        args = new ArrayList<Arg>();
        args.add(regs.get(0));
        args.add(regs.get(0));
        args.add(new Const(0, true)); // any idea why this negates r4?
        i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
          AssemblyInstrCond.NO_CODE, args));

        args = new ArrayList<Arg>();
        args.add(new Label("p_throw_overflow_error"));
        i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
          AssemblyInstrCond.VS, args));

        break;
      case LEN:
//    LDR r4, [sp, #4]
//    LDR r4, [r4]
//    STR r4, [sp]

        args = new ArrayList<Arg>();
        args.add(regs.get(0));
        args2 = new ArrayList<Arg>();
        args2.add(new Register(RegEnum.SP));
        args2.add(new Const(4, true));
        args.add(new MemoryAccess(args2));
        i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
          AssemblyInstrCond.NO_CODE, args));

        args = new ArrayList<Arg>();
        args.add(regs.get(0));
        args.add(new MemoryAccess(regs.get(0)));
        i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
          AssemblyInstrCond.NO_CODE, args));

        args = new ArrayList<Arg>();
        args.add(regs.get(0));
        args.add(new MemoryAccess(new Register(RegEnum.SP)));
        i.add(new AssemblyInstr(AssemblyInstrEnum.STR,
          AssemblyInstrCond.NO_CODE, args));

        break;
      case ORD:
        //already done by expr.generateCode()
        break;
      case CHR:
        //already done by expr.generateCode()
    }

    return i;
  }
}
