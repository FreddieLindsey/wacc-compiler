package wacc.ast;

import wacc.ast.type.*;

public class NewAssignNode extends StatNode {

  private TypeNode t;
  private IdentNode i;
  private AssignNode rhs;

  public NewAssignNode(TypeNode t, IdentNode i, AssignNode rhs) {
    super();
    this.t = t;
    this.i = i;
    this.rhs = rhs;
    i.setParent(this);
    rhs.setParent(this);
    // Scope checking will be handled by the initialisation of the IdentNode

    // ERROR if already exists in the CURRENT symbol table (variable shadowing)
    checkSymbolHere(i.getIdent());
  }

  public TypeNode getType() {
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
      && t.equals(rhs.type());
  }

}
