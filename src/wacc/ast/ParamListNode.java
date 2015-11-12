package wacc.ast;

public class ParamListNode implements ASTNode {

	private ParamNode ps;

	public ParamListNode(ParamNode ps) {
   	this.ps = ps;
	}

	@Override
	public boolean isSemanticallyValid() {
    return ps.isSemanticallyValid();
  }


}
