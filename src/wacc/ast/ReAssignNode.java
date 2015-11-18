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
    TypeNode returnType;
    if (rhs instanceof CallNode) {
      returnType = (
        (FuncTypeNode) symbolTable.lookUp(
          ((CallNode) rhs).getIdent().getIdent())
      ).getReturnType();
    } else {
      returnType = rhs.type();
    }

    return lhs.validLeft()
      && rhs.validRight()
      && lhs.isSemanticallyValid()
      && rhs.isSemanticallyValid()
      && lhs.type().equals(returnType);
  }

}
