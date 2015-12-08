package wacc.symbolTable;

import wacc.ast.type.TypeNode;

public class DataContainer {

  private TypeNode t;
  private AddressReference a;

  public DataContainer(TypeNode t) {
    this.t = t;
  }

  public DataContainer(TypeNode t, AddressReference a) {
    this(t);
    this.a = a;
  }

  public void setAddressReference(AddressReference a) {
    this.a = a;
  }

  public TypeNode getTypeNode() {
    return t;
  }

  public AddressReference getAddressReference() {
    return a;
  }

  public class AddressReference {

    private int a;

    public AddressReference(int a) {
      this.a = a;
    }
  }
}
