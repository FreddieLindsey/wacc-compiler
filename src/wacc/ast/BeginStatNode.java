package wacc.ast;

public class BeginStatNode implements StatNode {

	private StatNode stat;

	public BeginStatNode(StatNode stat) {
		this.stat = stat;
	}

	@Override
	public boolean isSemanticallyValid() {
		return stat.isSemanticallyValid();
	}


}
