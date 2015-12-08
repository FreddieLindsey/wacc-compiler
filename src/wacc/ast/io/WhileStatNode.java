package wacc.ast.io;

import wacc.ast.ExprNode;
import wacc.ast.IdentNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;
import wacc.backend.instruction.*;

import java.util.ArrayList;

public class WhileStatNode extends StatNode {

  private ExprNode expr;
  private StatNode stat;

  public WhileStatNode(ExprNode expr, StatNode stat) {
    super();
    this.expr = expr;
    this.stat = stat;
    expr.setParent(this);
    stat.setParent(this);
  }

  public ExprNode getExpr() {
    return this.expr;
  }

  public StatNode getStat() {
    return this.stat;
  }

  @Override
  public boolean isSemanticallyValid() {
    if (expr instanceof IdentNode &&
      symbolTable.lookUpType(((IdentNode) expr).getIdent()) == null) return false;

    semanticallyValid = stat.isSemanticallyValid()
      && expr.isSemanticallyValid()
      && expr.type().getType().equals(TypeEnum.BOOL);
    return semanticallyValid;
  }

  @Override
  public TypeNode returnType() {
    return stat.returnType();
  }

  @Override
  public InstructionBlock generateCode() {
    InstructionBlock i = new InstructionBlock();

    ArrayList<Register> regs = new ArrayList<Register>();
    regs.add(new Register(RegEnum.R4));
    regs.add(new Register(RegEnum.R5));
    regs.add(new Register(RegEnum.R6));
    regs.add(new Register(RegEnum.R7));
    //messy again, sorry

    ArrayList<Arg> args;

    args = new ArrayList<>();
    args.add(new Label("L0"));
    i.add(new AssemblyInstr(AssemblyInstrEnum.B,
      AssemblyInstrCond.NO_CODE, args));

    i.add(new Label("L1:"));

    i.addAll(stat.generateCode());

    i.add(new Label("L0:"));

    i.addAll(expr.generateCode(regs));

    args = new ArrayList<>();
    args.add(new Label("L1"));
    i.add(new AssemblyInstr(AssemblyInstrEnum.B,
      AssemblyInstrCond.EQ, args));


    return i;
  }

}
