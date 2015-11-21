package wacc.ast.type;

import java.util.ArrayList;

public class StringNode extends LiteralNode<String> {

  private static final TypeNode type = new ArrayTypeNode(TypeEnum.CHAR);
  private final ArrayList<CharNode> string;

  public StringNode(String value) {
    super();
    this.string = new ArrayList<>();
    for (char c : value.toCharArray()) {
      string.add(new CharNode(c));
    }
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
