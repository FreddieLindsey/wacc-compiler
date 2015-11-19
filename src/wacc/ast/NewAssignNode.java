package wacc.ast;

import wacc.ast.function.CallNode;
import wacc.ast.type.FuncTypeNode;
import wacc.ast.type.TypeNode;

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
    if (parent.getSymbolTable().lookUpHere(i.getIdent()) != null) return false;

    TypeNode returnType;
    if (rhs instanceof CallNode) {
      returnType = (
        (FuncTypeNode) symbolTable.lookUp(
          ((CallNode) rhs).getIdent().getIdent())
      ).getReturnType();
    } else {
      returnType = rhs.type();
    }

    boolean valid = i.isSemanticallyValid()
      && rhs.validRight()
      && rhs.isSemanticallyValid()
      && t.equals(returnType);

    if (!valid) return false;


    addToScope(i.getIdent(), t);

    semanticallyValid = true;
    return semanticallyValid;
  }

}
