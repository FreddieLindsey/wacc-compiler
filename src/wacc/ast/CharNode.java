package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class CharNode extends LiteralNode<Character> {

  private static final TypeEnum type = TypeEnum.CHAR;
  protected static char[] invalid = { '\\', '\'', '\"' };

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
