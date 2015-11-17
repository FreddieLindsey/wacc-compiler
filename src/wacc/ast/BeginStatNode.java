package wacc.ast;

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
		return stat.isSemanticallyValid();
	}

  @Override
  public boolean hasReturn() {
      return stat.hasReturn();
  }

}
