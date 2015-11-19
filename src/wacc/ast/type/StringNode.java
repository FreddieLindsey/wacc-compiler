package wacc.ast.type;

public class StringNode extends LiteralNode<String> {

  private static final TypeNode type = new TypeNode(TypeEnum.STRING);

  public StringNode(String value) {
    super();
    this.value = value;
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = true;
    return semanticallyValid;
  }

  @Override
  public TypeNode type() {
    return type;
  }
}
