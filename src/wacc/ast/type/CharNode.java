package wacc.ast.type;

public class CharNode extends LiteralNode<Character> {

  protected static char[] invalid = { '\\', '\'', '\"' };

  public CharNode(char value) {
    super();
    this.value = value;
    this.type = new TypeNode(TypeEnum.CHAR);
  }

  @Override
  public boolean isSemanticallyValid() {
    for (char c : invalid) {
      if (value == c) {
        return false;
      }
    }
    semanticallyValid = true;
    return semanticallyValid;
  }
}
