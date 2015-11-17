package wacc.ast.io;

import wacc.ast.ASTNode;
import wacc.ast.StatNode;

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
    return stat1.isSemanticallyValid() && stat2.isSemanticallyValid();
  }

}