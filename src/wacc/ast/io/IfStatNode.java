package wacc.ast.io;

import wacc.ast.ExprNode;
import wacc.ast.type.TypeEnum;
import wacc.ast.type.TypeNode;
import wacc.backend.instruction.*;

import java.util.ArrayList;

public class IfStatNode extends StatNode {

  private ExprNode expr;
  private StatNode thenStat;
  private StatNode elseStat;

  public IfStatNode(ExprNode expr, StatNode thenStat, StatNode elseStat) {
    super();
    this.expr = expr;
    this.thenStat = thenStat;
    this.elseStat = elseStat;
    expr.setParent(this);
    thenStat.setParent(this);
    elseStat.setParent(this);
  }

  public ExprNode getExpr() {
    return this.expr;
  }

  public StatNode getTrueBranch() {
    return this.thenStat;
  }

  public StatNode getFalseBranch() {
    return this.elseStat;
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = expr.isSemanticallyValid()
      && thenStat.isSemanticallyValid() && elseStat.isSemanticallyValid()
      && expr.type().getType().equals(TypeEnum.BOOL);
    return semanticallyValid;
  }

  @Override
  public TypeNode returnType() {
    TypeNode true_ = thenStat.returnType();
    TypeNode false_ = elseStat.returnType();
    return (true_ != null) ? true_ : false_;
  }

  @Override
  public boolean returns() {
    return thenStat.returns() && elseStat.returns();
  }

  @Override
  public InstructionBlock generateCode() {
    InstructionBlock i = new InstructionBlock();
    ArrayList<Arg> args;

    // Should evaluate the expression and leave result in r4
    // true -> #1 and false -> #0
    i.add(expr.generateCode());

    // CMP r4, #0
    // Checks if expr evaluates to false
    args = new ArrayList<>();
    args.add(new Register(RegEnum.R4));
    args.add(new Const(0, true));
    i.add(new AssemblyInstr(AssemblyInstrEnum.CMP,
      AssemblyInstrCond.NO_CODE, args));

    // TODO: think about how we can make unique labels?
    // BEQ L#
    // If expr is false, branches to else case, in a new label
    args = new ArrayList<>();
    args.add(new Label("else")); // TODO: placeholder string
    i.add(new AssemblyInstr(AssemblyInstrEnum.B, AssemblyInstrCond.EQ,
      args));

    i.add(thenStat.generateCode());
    i.add(new Label("else")); // TODO: placeholder string
    i.add(elseStat.generateCode());


    return i;
    }

  }
