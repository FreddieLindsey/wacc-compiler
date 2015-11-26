package wacc.ast.io;

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
    // Check if the stat is valid
    if (!stat.isSemanticallyValid()) return false;

    semanticallyValid = true;
    return semanticallyValid;
  }

  @Override
  public TypeNode returnType() {
    return stat.returnType();
  }

  @Override
  public boolean returns() {
    return stat.returns();
  }

}
