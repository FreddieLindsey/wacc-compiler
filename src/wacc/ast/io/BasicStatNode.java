package wacc.ast.io;

import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.operator.UnOpNode;
import wacc.ast.operator.UnaryOperator;
import wacc.ast.type.IntNode;
import wacc.ast.type.PairTypeNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;
import wacc.backend.instruction.*;

import java.util.ArrayList;

import static wacc.ast.operator.UnaryOperator.*;

public class BasicStatNode extends StatNode {

  private ExprNode expr;
  private StatTypeEnum st;

  public BasicStatNode(StatTypeEnum st) {
    super();
    this.st = st;
  }

  public void addExpr(ExprNode e) {
    this.expr = e;
    e.setParent(this);
  }

  public StatTypeEnum getType() {
    return st;
  }

  public ExprNode getExpr() {
    return expr;
  }

  @Override
  public boolean isSemanticallyValid() {
    // TODO: Tidy up and comment
    switch (st) {
      case SKIP:
        semanticallyValid = true;
        return semanticallyValid;
      case EXIT:
        semanticallyValid = expr != null
          && expr.type() != null
          && expr.type().getType().equals(TypeEnum.INT);
        break;
      case FREE:
        semanticallyValid = expr != null
          && expr.type() != null
          && expr.type() instanceof PairTypeNode;
        break;
      case READ:
        semanticallyValid = expr != null
          && expr.type() != null
          && (expr.type().getType() == TypeEnum.INT
          || expr.type().getType() == TypeEnum.CHAR);
        break;
      default:
        semanticallyValid = expr != null && expr.type() != null;
    }
    if (!semanticallyValid) return false;

    semanticallyValid = expr.isSemanticallyValid();
    return semanticallyValid;
  }

  @Override
  public TypeNode returnType() {
    if (st == StatTypeEnum.RETURN) {
      if (expr instanceof IdentNode) {
        return symbolTable.lookUp(((IdentNode) expr).getIdent());
      } else {
        return expr.type();
      }
    } else {
      return null;
    }
  }

  @Override
  public boolean returns() {
    return st == StatTypeEnum.RETURN;
  }

  @Override
  public InstructionBlock generateCode() {
    InstructionBlock i = new InstructionBlock();

    ArrayList<Arg> args;
    AssemblyInstr a;

    switch (st) {
      case SKIP:
        break;
      case FREE:
        // 29    LDR r4, [sp]
        // 30    MOV r0, r4
        // 31    BL p_free_pair

        args = new ArrayList<>();
        args.add(new Register(RegEnum.R4));
        args.add(new MemoryAccess(new Register(RegEnum.SP)));
        i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
          AssemblyInstrCond.NO_CODE, args));

        args = new ArrayList<>();
        args.add(new Register(RegEnum.R0));
        args.add(new Register(RegEnum.R4));
        i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
          AssemblyInstrCond.NO_CODE, args));

        args = new ArrayList<>();
        args.add(new Label("b_free_pair"));
        i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
          AssemblyInstrCond.NO_CODE, args));

        break;
      case READ:
      case RETURN:

        // -----------------
        // LDR r4, =0
        // MOV r0, r4
        // POP {pc}
        // -----------------

        args = new ArrayList<>();
        args.add(new Register(RegEnum.R4));
        args.add(new Const(0, false)); // replace with eval of expr
        i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
          AssemblyInstrCond.NO_CODE, args));

        args = new ArrayList<>();
        args.add(new Register(RegEnum.R0));
        args.add(new Register(RegEnum.R4));
        i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
          AssemblyInstrCond.NO_CODE, args));

        args = new ArrayList<>();
        args.add(new Register(RegEnum.PC));
        i.add(new AssemblyInstr(AssemblyInstrEnum.POP, AssemblyInstrCond.NO_CODE, args));

        break;

      case EXIT:
        // --------------
        // LDR r4, =7
        // MOV r0, r4
        // BL exit
        // --------------

        if (expr.type().getType() != TypeEnum.INT) {
          throw new RuntimeException("Exit statement should have int return");
        }

        if (expr instanceof IntNode || expr instanceof UnOpNode) {
          long exitCode = 0;
          if (expr instanceof IntNode) {
            exitCode = ((IntNode) expr).getValue();
          }

          if (expr instanceof UnOpNode) {
            UnOpNode expr_ = (UnOpNode) expr;
            UnaryOperator unop = expr_.getOperator();
            switch(unop) {
              case NEG:
                ExprNode expr__ = expr_.getExpr();
                if (expr__ instanceof IntNode) {
                  exitCode = -1 * ((IntNode) expr__).getValue();
                  break;
                }
              default:
                throw new UnsupportedOperationException("Code generation hasn't been written for this operator");
            }

            while (exitCode < 0) {
              exitCode += 256;
            }
            exitCode %= 256;
          }

          args = new ArrayList<>();
          args.add(new Register(RegEnum.R4));
          args.add(new Const((int) exitCode, false));
          i.add(new AssemblyInstr(AssemblyInstrEnum.LDR,
            AssemblyInstrCond.NO_CODE, args));
        }

        args = new ArrayList<>();
        args.add(new Register(RegEnum.R0));
        args.add(new Register(RegEnum.R4));
        i.add(new AssemblyInstr(AssemblyInstrEnum.MOV,
          AssemblyInstrCond.NO_CODE, args));

        args = new ArrayList<>();
        args.add(new Label("exit"));
        i.add(new AssemblyInstr(AssemblyInstrEnum.BL,
          AssemblyInstrCond.NO_CODE, args));

        break;

      case PRINT:
        break;
      case PRINTLN:
        break;
    }

    return i;
  }

}
