package wacc.ast.type;

public class AnyTypeNode extends TypeNode {

  public AnyTypeNode() {
    super(TypeEnum.ANY);
  }

  @Override
  public boolean equals(Object o) {
    return (o instanceof TypeNode);
  }
}
