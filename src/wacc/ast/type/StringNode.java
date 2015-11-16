package wacc.ast.type;

public class StringNode extends LiteralNode<String> {

  private static final TypeNode type = new TypeNode(TypeEnum.STRING);

  public StringNode(String value) {
    super();
    this.value = value;
  }

  @Override
  public boolean isSemanticallyValid() {
    for (int i = 0; i < value.length(); ++i) {
      for (char c : CharNode.invalid) {
        if (value.charAt(i) == c) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public TypeNode type() {
    return type;
  }
}
