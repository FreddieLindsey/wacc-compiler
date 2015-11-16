package wacc.ast;

public class CompStatNode extends StatNode {

  // for stat composition

  private StatNode stat1;
  private StatNode stat2;

  public CompStatNode(ASTNode parent, StatNode stat1, StatNode stat2) {
    super(parent);
    this.stat1 = stat1;
    this.stat2 = stat2;
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
