package wacc.ast.function;

import wacc.ast.ASTNode;
import wacc.ast.IdentNode;
import wacc.ast.type.TypeNode;

public class ParamNode extends ASTNode {

  private TypeNode type;
  private IdentNode ident;

  public ParamNode(TypeNode type, IdentNode ident) {
    super();
    this.type = type;
    this.ident = ident;
    ident.setParent(this);
  }

  public TypeNode getType() {
    return type;
  }

  public IdentNode getIdent() {
    return ident;
  }

  @Override
  public boolean isSemanticallyValid() {
    // Check the ident is valid
    if (!ident.isSemanticallyValid()) return false;

    // Check the type is valid
    if (!type.isSemanticallyValid()) return false;

    semanticallyValid = true;
    return semanticallyValid;
  }


}
