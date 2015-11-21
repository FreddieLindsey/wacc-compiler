package wacc.ast.type;

import wacc.ast.ASTNode;

public class TypeNode extends ASTNode {

  protected TypeEnum type;

  public TypeNode(TypeEnum type) {
    super();
    this.type = type;
    this.semanticallyValid = true;
  }

  public TypeEnum getType() {
    return type;
  }

  @Override
  public boolean isSemanticallyValid() {
    return semanticallyValid;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof TypeNode) {
      TypeNode tn = (TypeNode) o;
      return tn.type == type;
    } else {
      return false;
    }
  }

  public TypeNode copy() {
    return new TypeNode(type);
  }
}
