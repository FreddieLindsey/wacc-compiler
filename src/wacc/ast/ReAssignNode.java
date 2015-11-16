package wacc.ast;

public class ReAssignNode extends StatNode {

    private AssignNode lhs;
    private AssignNode rhs;

    public ReAssignNode(AssignNode lhs, AssignNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public AssignNode getLHS() {
        return lhs;
    }

    public AssignNode getRHS() {
        return rhs;
    }

    @Override
    public boolean isSemanticallyValid() {
    //TODO: check that the lhs isnt already being used in same scope
    return lhs.validLeft()
        && rhs.validRight()
        && lhs.isSemanticallyValid()
        && rhs.isSemanticallyValid()
        && lhs.type() == rhs.type();
    }

}
