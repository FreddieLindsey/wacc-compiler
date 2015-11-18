package wacc.ast.type;

import java.util.ArrayList;
import java.util.Collection;

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

  public ArrayList<TypeNode> getParamsTypes() {
    return paramsType;
  }
}
