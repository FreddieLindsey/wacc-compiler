package wacc.ast.type;

public class CharNode extends LiteralNode<Character> {;

  public CharNode(char value) {
    super();
    this.value = value;
    this.type = new TypeNode(TypeEnum.CHAR);
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = true;
    return semanticallyValid;
  }
}
