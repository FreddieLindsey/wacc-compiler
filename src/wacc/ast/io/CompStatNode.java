package wacc.ast.io;

import wacc.ast.type.TypeNode;
import wacc.backend.*;

import java.util.ArrayList;

public class CompStatNode extends StatNode {

  // for stat composition

  private StatNode stat1;
  private StatNode stat2;

  public CompStatNode(StatNode stat1, StatNode stat2) {
    super();
    this.stat1 = stat1;
    this.stat2 = stat2;
    stat1.setParent(this);
    stat2.setParent(this);
  }

  public StatNode getFstStat() {
    return stat1;
  }

  public StatNode getSndStat() {
    return stat2;
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = stat1.isSemanticallyValid() && stat2.isSemanticallyValid();
    return semanticallyValid;
  }

  @Override
  public TypeNode returnType() {
    TypeNode s1 = stat1.returnType();
    return (s1 == null) ? stat2.returnType() : s1;
  }

  @Override
  public boolean returns() {
    boolean s1 = stat1.returns();
    return (!s1) ? stat2.returns() : s1;
  }

  @Override
  public ArrayList<Instruction> generateCode() {
    ArrayList<Instruction> instrs = new ArrayList<Instruction>();

    instrs.addAll(stat1.generateCode());
    instrs.addAll(stat2.generateCode());

    return instrs;
  }

}
