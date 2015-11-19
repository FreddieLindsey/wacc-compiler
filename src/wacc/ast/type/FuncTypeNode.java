package wacc.ast.type;

import java.util.ArrayList;

public class FuncTypeNode extends TypeNode {

  private final TypeNode returnType;
  private final ArrayList<TypeNode> paramsType;

  public FuncTypeNode(TypeNode returnType) {
    super(TypeEnum.FUNC);
    this.returnType = returnType;
    this.paramsType = new ArrayList<>();
    returnType.setParent(this);
  }

  public void addParamType(TypeNode paramType) {
    paramsType.add(paramType);
    paramType.setParent(this);
  }

  public TypeNode getReturnType() {
    return returnType;
  }

  public ArrayList<TypeNode> getParamsTypes() {
    return paramsType;
  }

  @Override
  public TypeNode copy() {
    FuncTypeNode t = new FuncTypeNode(returnType);
    for (TypeNode p : paramsType) {
      t.addParamType(p);
    }
    return t;
  }
}
