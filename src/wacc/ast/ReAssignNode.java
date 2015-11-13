package wacc.ast;

public class ReAssignNode implements StatNode {

    private AssignLHSNode lhs;
    private AssignRHSNode rhs;

    public ReAssignNode(AssignLHSNode lhs, AssignRHSNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public AssignLHSNode getLHS() {
        return lhs;
    }

    public AssignRHSNode getRHS() {
        return rhs;
    }

    @Override
    public boolean isSemanticallyValid() {
    //TODO: check that the lhs isnt already being used in same scope
    return lhs.isSemanticallyValid() 
        && rhs.isSemanticallyValid()
        && lhs.type() == rhs.type();
    }

}
