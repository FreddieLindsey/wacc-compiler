package wacc.ast;

public class BeginStatNode extends StatNode {

	private StatNode stat;

	public BeginStatNode(StatNode stat) {
    super(null);
		this.stat = stat;
	}

	@Override
	public boolean isSemanticallyValid() {
		return stat.isSemanticallyValid();
	}


}
