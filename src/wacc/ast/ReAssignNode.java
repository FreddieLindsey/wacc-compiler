package wacc.ast;

public class ReAssignNode implements StatNode {

	private AssignLHSNode lhs;
	private AssignRHSNode rhs;

	public ReAssignNode(AssignLHSNode lhs, AssignRHSNode rhs) {
    	this.lhs = lhs;
    	this.rhs = rhs;
  	}


	@Override
	public boolean isSemanticallyValid() {
    //TODO: check that the lhs isnt already being used in same scope
	// TODO: check types match
    return lhs.isSemanticallyValid() && rhs.isSemanticallyValid();
	}

}
