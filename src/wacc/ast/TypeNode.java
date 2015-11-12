package wacc.ast;

public enum Type {
  INT,
  BOOL,
  STRING,
  CHAR,
  ARR,
  PAIR
}

public class TypeNode implements StatNode {

  private final Type t;

  public TypeNode(TypeNode.Type type) {
    this.t = type;
  }

  @Override
  public boolean isSemanticallyValid() {
    // TODO
    return false;
  }

}
