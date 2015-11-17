package wacc.ast;

public class NewPairNode<F extends ExprNode, S extends ExprNode> extends PairNode<F, S> {

  public NewPairNode(F fst, S snd) {
    super(fst, snd);
  }

}
