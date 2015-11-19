package wacc.ast.type;

public class BoolNode extends LiteralNode<Boolean> {

  public BoolNode(boolean value) {
    super();
    this.value = value;
    this.type = new TypeNode(TypeEnum.BOOL);
  }

  @Override
  public boolean isSemanticallyValid() {
    semanticallyValid = value != null;
    return semanticallyValid;
  }
}
