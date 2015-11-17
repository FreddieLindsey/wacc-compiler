package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class TypeNode extends StatNode {

  private final TypeEnum t;

  public TypeNode(TypeEnum type) {
    super();
    this.t = type;
  }

  @Override
  public boolean isSemanticallyValid() {
    // TODO
    return true;
  }

}
