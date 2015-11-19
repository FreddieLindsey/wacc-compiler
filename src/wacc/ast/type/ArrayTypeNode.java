package wacc.ast.type;

public class ArrayTypeNode extends TypeNode {

  private TypeNode arrayType;

  public ArrayTypeNode() {
    super(TypeEnum.ARR);
  }

  public void setArrayType(TypeNode arrayType) {
    this.arrayType = arrayType;
    arrayType.setParent(this);
  }

  public TypeNode type() {
    return arrayType;
  }

  @Override
  public boolean equals(Object o) {
    if (super.equals(o) && o instanceof ArrayTypeNode) {
      ArrayTypeNode that = (ArrayTypeNode) o;
      if (that.arrayType == null || arrayType == null) return true;
      return that.arrayType.equals(arrayType);
    } else {
      return false;
    }
  }

  @Override
  public TypeNode copy() {
    ArrayTypeNode a = new ArrayTypeNode();
    a.setArrayType(arrayType.copy());
    return a;
  }
}
