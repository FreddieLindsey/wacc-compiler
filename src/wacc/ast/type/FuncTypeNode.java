package wacc.ast.type;

import java.util.ArrayList;

public class FuncTypeNode extends TypeNode {

  private final TypeNode returnType;
  private final ArrayList<TypeNode> paramsType;

  public FuncTypeNode(TypeNode returnType, ArrayList<TypeNode> paramsTypes) {
    super(TypeEnum.FUNC);
    this.returnType = returnType;
    this.paramsType = paramsTypes;
    returnType.setParent(this);
    for (TypeNode p : paramsTypes) {
      p.setParent(this);
    }
  }
}
