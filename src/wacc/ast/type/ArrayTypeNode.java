package wacc.ast.type;

public class ArrayTypeNode extends TypeNode {

  private final TypeNode arrayType;

  public ArrayTypeNode(TypeNode arrayType) {
    super(TypeEnum.ARR);
    this.arrayType = arrayType;
    arrayType.setParent(this);
  }

  @Override
  public boolean equals(Object o) {
    if (super.equals(o)) {
      ArrayTypeNode that = (ArrayTypeNode) o;
      return that.arrayType.equals(arrayType);
    } else {
      return false;
    }
  }
}
