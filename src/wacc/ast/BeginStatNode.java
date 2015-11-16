package wacc.ast;

public class BeginStatNode extends StatNode {

	private StatNode stat;

	public BeginStatNode(ASTNode parent, StatNode stat) {
    super(parent);
		this.stat = stat;
	}

	@Override
	public boolean isSemanticallyValid() {
		return stat.isSemanticallyValid();
	}


}
