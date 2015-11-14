package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class CharNode extends LiteralNode<Character> {

  protected static char[] invalid = { '\\', '\'', '\"' };

  public CharNode(char value) {
    this.value = value;
  }

  @Override
  public TypeEnum type() {
    return TypeEnum.CHAR;
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
