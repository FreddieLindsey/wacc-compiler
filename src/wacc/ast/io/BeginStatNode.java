package wacc.ast.io;

import wacc.ast.StatNode;
import wacc.ast.type.TypeNode;

public class BeginStatNode extends StatNode {

  private StatNode stat;

  public BeginStatNode() {
    super();
  }

  public void addStat(StatNode s) {
    this.stat = s;
    s.setParent(this);
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = stat.isSemanticallyValid();
    return semanticallyValid;
  }

  @Override
  public TypeNode hasReturn() {
    return stat.hasReturn();
  }

}
