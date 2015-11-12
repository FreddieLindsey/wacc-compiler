package wacc.ast;

public class CharNode extends LiteralNode<Character> {

  protected static char[] invalid = { '\\', '\'', '\"', '\0', '\b', '\t', '\n',
      '\f', '\r', '\"', '\'', '\\' };

  public CharNode(char value) {
    this.value = value;
  }

  @Override
  public boolean isSemanticallyValid() {
    for (char c : invalid) {
      if (value == c) {
        return false;
      }
    }
    return true;
  }

}