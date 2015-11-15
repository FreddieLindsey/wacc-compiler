package wacc.ast;

public class PairNode<F extends TypeNode, S extends TypeNode> implements TypeNode {

  private F fst;
  private S snd;		
  
  public PairNode(F fst, S snd) {
    this.fst = fst;
    this.snd = snd;
  }

  public getFst() {
    return this.fst;
  }

  public getFst() {
    return this.snd;
  }

  @Override
  public boolean isSemanticallyValid() {
    return fst.isSemanticallyValid() && snd.isSemanticallyValid();
  }

}
