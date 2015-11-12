package wacc.ast;

public class PairNode<F extends ASTNode, S extends ASTNode> implements ASTNode {

  private F fst;
  private S snd;		
  
  public PairNode(F fst, S snd) {
    this.fst = fst;
    this.snd = snd;
  }

  @Override
  public boolean isSemanticallyValid() {
    return fst.isSemanticallyValid() && snd.isSemanticallyValid();
  }

}
