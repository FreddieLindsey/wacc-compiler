package wacc.ast;

import wacc.ast.function.CallNode;
import wacc.ast.type.FuncTypeNode;
import wacc.ast.type.TypeNode;

public class ReAssignNode extends StatNode {

  private AssignNode lhs;
  private AssignNode rhs;

  public ReAssignNode(AssignNode lhs, AssignNode rhs) {
    super();
    this.lhs = lhs;
    this.rhs = rhs;
    lhs.setParent(this);
    rhs.setParent(this);
  }

  @Override
  public boolean isSemanticallyValid() {
    // Check lhs is valid on the left
    if (!lhs.validLeft()) return false;

    // Check rhs is valid on the right
    if (!rhs.validRight()) return false;

    // Check lhs is semantically valid
    if (!lhs.isSemanticallyValid()) return false;

    // Check rhs is semantically valid
    if (!rhs.isSemanticallyValid()) return false;

    TypeNode assignType, returnType;

    // Check that the lhs has already been declared in scope
    if (lhs instanceof IdentNode &&
      symbolTable.lookUp(((IdentNode) lhs).getIdent()) == null) return false;

    // Check the type of what we are assigning
    assignType = lhs.type();

    // Check the type of what we are returning
    if (rhs instanceof CallNode) {
      returnType = (
        (FuncTypeNode) symbolTable.lookUp(
          ((CallNode) rhs).getIdent().getIdent())
      ).getReturnType();
    } else {
      returnType = rhs.type();
    }

    semanticallyValid = (returnType.equals(assignType) || assignType.equals(returnType));
    return semanticallyValid;
  }

}
