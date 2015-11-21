package wacc.ast.type;

import wacc.ast.pair.PairLiteralNode;

public class PairTypeNode extends TypeNode {

  private final TypeNode fst;
  private final TypeNode snd;

  public PairTypeNode(TypeNode fst, TypeNode snd) {
    super(TypeEnum.PAIR);
    this.fst = fst;
    this.snd = snd;
    fst.setParent(this);
    snd.setParent(this);
  }

  public PairTypeNode() {
    super(TypeEnum.PAIR);
    this.fst = new AnyTypeNode();
    this.snd = new AnyTypeNode();
    fst.setParent(this);
    snd.setParent(this);
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
