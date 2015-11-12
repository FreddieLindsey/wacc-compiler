package wacc.ast;

public class NewAssignNode implements StatNode {

	private TypeNode t;
	private IdentNode i;
  private AssignRHSNode rhs;

	public NewAssignNode(TypeNode t, IdentNode i, AssignRHSNode rhs) {
    this.t = t;
    this.i = i;
    this.rhs = rhs;
  }


	@Override
	public boolean isSemanticallyValid() {
    //TODO: check that the Ident isnt already being used in same scope
    return rhs.isSemanticallyValid()
        && t.isSemanticallyValid()
        && rhs.isSemanticallyValid()
        && t.type() == rhs.type();
	}

}
