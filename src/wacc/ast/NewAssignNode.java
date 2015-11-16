package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class NewAssignNode extends StatNode {

  private TypeEnum t;
  private IdentNode i;
  private AssignNode rhs;

  public NewAssignNode(TypeEnum t, IdentNode i, AssignNode rhs) {
    super(null);
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

  public AssignNode getRHS() {
    return rhs;
  }

  @Override
  public boolean isSemanticallyValid() {
    // TODO: check that the Ident isnt already being used in same scope
    return i.isSemanticallyValid()
      && rhs.validRight()
      && rhs.isSemanticallyValid()
      && t == rhs.type();
  }

}
