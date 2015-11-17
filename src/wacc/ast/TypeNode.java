package wacc.ast.type;

import wacc.ast.ASTNode;
import wacc.ast.StatNode;

public class TypeNode extends StatNode {

  private final TypeEnum t;
  private TypeEnum type;

  private TypeNode lhs;
  private TypeNode rhs;

  private TypeNode arrType;

  public TypeNode(TypeEnum type) {
    super();
    this.t = type;
  }

  public TypeNode(ASTNode parent, TypeNode lhs, TypeNode rhs) {
    super(parent);
    this.lhs = lhs;
    this.rhs = rhs;
    this.type = TypeEnum.PAIR;
  }

  public TypeNode(ASTNode parent, TypeNode arrType) {
    super(parent);
    this.arrType = arrType;
    this.type = TypeEnum.ARR;
  }

  public TypeNode getLHS() {
    return this.lhs;
  }

  public TypeNode getRHS() {
    return this.rhs;
  }

  public TypeNode getArrType() {
    return this.arrType;
  }

  @Override
  public boolean isSemanticallyValid() {
    // TODO
    return true;
  }

  public TypeEnum getType() {
    return type;
  }

  @Override
  public boolean equals(TypeNode tn) {
    boolean valid = tn.type() == type;

    if (type == TypeEnum.PAIR) {
      valid &= tn.getLHS().equals(lhs)
            && tn.getRHS().equals(rhs);
    } else if (type == TypeEnum.ARR) {
      valid &= tn.getArrType().equals(arrType);
    }

    return valid;
  }

}
