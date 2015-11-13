package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class NewAssignNode implements StatNode {

	private TypeEnum t;
	private IdentNode i;
    private AssignRHSNode rhs;

	public NewAssignNode(TypeEnum t, IdentNode i, AssignRHSNode rhs) {
    this.t = t;
    this.i = i;
    this.rhs = rhs;
  }


	@Override
	public boolean isSemanticallyValid() {
    //TODO: check that the Ident isnt already being used in same scope
    return rhs.isSemanticallyValid()
        && rhs.isSemanticallyValid()
        && t == rhs.type();
	}

}
