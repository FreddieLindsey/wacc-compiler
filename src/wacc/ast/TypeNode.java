package wacc.ast;

import wacc.symbolTable.TypeEnum;

public class TypeNode extends StatNode {

  private final TypeEnum t;

  public TypeNode(ASTNode parent, TypeEnum type) {
    super(parent);
    this.t = type;
  }

  @Override
  public boolean isSemanticallyValid() {
    // TODO
    return true;
  }

}
