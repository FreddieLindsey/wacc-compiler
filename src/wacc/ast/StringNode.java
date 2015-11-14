package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class StringNode extends LiteralNode<String> {
  
  public StringNode(String value) {
    this.value = value;
  }

  @Override
  public TypeEnum type() {
    return TypeEnum.STRING;
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

}
