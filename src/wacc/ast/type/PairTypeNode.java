package wacc.ast.type;

public class PairTypeNode extends TypeNode {

  private final TypeNode fst;
  private final TypeNode snd;

  public PairTypeNode(TypeNode fst, TypeNode snd) {
    super(TypeEnum.PAIR);
    this.type = TypeEnum.PAIR;
    this.fst = fst;
    this.snd = snd;
    fst.setParent(this);
    snd.setParent(this);
  }

  @Override
  public boolean equals(Object o) {
    if (super.equals(o)) {
      PairTypeNode that = (PairTypeNode) o;
      return that.fst.equals(fst) && that.snd.equals(snd);
    } else {
      return false;
    }
  }

  @Override
  public TypeNode copy() {
    return new PairTypeNode(fst, snd);
  }

  public TypeNode getFst() {
    return fst;
  }

  public TypeNode getSnd() {
    return snd;
  }
}
