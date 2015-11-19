package wacc.ast.type;

public class PairTypeNode extends TypeNode {

  private final TypeNode fst;
  private final TypeNode snd;

  public PairTypeNode(TypeNode fst, TypeNode snd) {
    super(TypeEnum.PAIR);
    this.type = TypeEnum.PAIR;
    this.fst = fst;
    this.snd = snd;
    if (fst != null) fst.setParent(this);
    if (snd != null) snd.setParent(this);
  }

  @Override
  public boolean equals(Object o) {
    if (super.equals(o)) {
      PairTypeNode that = (PairTypeNode) o;
      try {
        return that.fst.equals(fst) && that.snd.equals(snd);
      } catch (NullPointerException e) {
        return that.fst == null && fst == null
          && that.snd == null && snd == null;
      }
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
