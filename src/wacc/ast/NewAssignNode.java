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

    public TypeEnum getType() {
        return t;
    }

    public IdentNode getIdent() {
        return i;
    }

    public AssignRHSNode getRHS() {
        return rhs;
    }

    @Override
    public boolean isSemanticallyValid() {
        // TODO: check that the Ident isnt already being used in same scope
        return i.isSemanticallyValid()
            && rhs.isSemanticallyValid()
            && t == rhs.type();
    }

}
