package wacc.ast;

import wacc.ast.function.CallNode;
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
    TypeNode returnType;
    if (rhs instanceof CallNode) {
      returnType = (
        (FuncTypeNode) symbolTable.lookUp(
          ((CallNode) rhs).getIdent().getIdent())
        ).getReturnType();
    } else {
      returnType = rhs.type();
    }

    addToScope(i.getIdent(), t);
    return i.isSemanticallyValid()
      && rhs.validRight()
      && rhs.isSemanticallyValid()
      && t.equals(returnType);
  }

}
