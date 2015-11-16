package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class TypeNode extends StatNode {

  private final TypeEnum t;
  private TypeEnum type;

  public TypeNode(TypeEnum type) {
    super();
    this.t = type;
  }

  @Override
  public boolean isSemanticallyValid() {
    // TODO
    return true;
  }

  public TypeEnum getType() {
    return type;
  }
}
