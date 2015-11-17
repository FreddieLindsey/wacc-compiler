package wacc.ast.type;

import wacc.ast.StatNode;
import wacc.symbolTable.TypeEnum;

import static wacc.symbolTable.TypeEnum.*;

public class TypeNode extends StatNode {

  private final TypeEnum t;
  private TypeNode lhs;
  private TypeNode rhs;
  private TypeNode type;

  public TypeNode(TypeEnum type) {
    super();
    this.t = type;
  }


  public TypeNode(TypeNode lhs, TypeNode rhs) {
    super();
    this.lhs = lhs;
    this.rhs = rhs;
    this.t = PAIR;
  }

  public TypeNode(TypeNode arrType) {
    super();
    this.type = arrType;
    this.t = ARR;
  }

  public TypeNode getLHS() {
    return this.lhs;
  }

  public TypeNode getRHS() {
    return this.rhs;
  }

  public TypeNode getArrType() {
    return this.type;
  }

  @Override
  public boolean isSemanticallyValid() {
    // TODO
    return true;
  }

  public TypeNode getType() {
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof TypeNode) {
      TypeNode tn = (TypeNode) o;
      boolean valid = tn.getType().t == getType().t;

      if (!valid) return false;

      if (getType() == new TypeNode(PAIR)) {
        valid &= tn.getLHS().equals(lhs)
          && tn.getRHS().equals(rhs);
      } else if (getType() == new TypeNode(ARR)) {
        valid &= tn.getArrType().equals(type);
      }

      return valid;
    } else {
      return false;
    }
  }

}
