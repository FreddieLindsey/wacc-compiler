package wacc.symbolTable;

import wacc.ast.type.TypeNode;

public class DataContainer {

  private TypeNode t;
  private int a;

  public DataContainer(TypeNode t) {
    this.t = t;
  }

  public DataContainer(TypeNode t, int a) {
    this(t);
    this.a = a;
  }

  public void setAddressReference(int a) {
    this.a = a;
  }

  public TypeNode getTypeNode() {
    return t;
  }

  public int getAddressReference() {
    return a;
  }

}
